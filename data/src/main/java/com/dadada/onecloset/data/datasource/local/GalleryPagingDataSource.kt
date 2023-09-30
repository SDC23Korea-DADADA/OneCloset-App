package com.dadada.onecloset.data.datasource.local

import android.content.ContentResolver
import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.core.os.bundleOf
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.dadada.onecloset.domain.model.Photo
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class GalleryPagingDataSource @Inject constructor(
    @ApplicationContext context: Context
) : PagingSource<Int, Photo>() {
    private val uriExternal: Uri by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            MediaStore.Images.Media.getContentUri(
                MediaStore.VOLUME_EXTERNAL,
            )
        } else {
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        }
    }

    private val projection = arrayOf(
        MediaStore.Images.ImageColumns.DATA,
        MediaStore.Images.ImageColumns.DISPLAY_NAME, // 이름
//        MediaStore.Images.ImageColumns.SIZE, // 크기
        MediaStore.Images.ImageColumns.DATE_TAKEN,
//        MediaStore.Images.ImageColumns.DATE_ADDED, // 추가된 날짜
        MediaStore.Images.ImageColumns._ID,
    )
    private val sortedOrder = MediaStore.Images.ImageColumns.DATE_TAKEN

    private val contentResolver by lazy {
        context.contentResolver
    }

    override fun getRefreshKey(state: PagingState<Int, Photo>): Int? {
        return state.anchorPosition?.let { achorPosition ->
            state.closestPageToPosition(achorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(achorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
        return try {
            val position = params.key ?: STARTING_PAGE_INDEX
            val data = getAllPhotos(
                page = position,
                loadSize = params.loadSize,
                currentLocation = null,
            )
            val endOfPaginationReached = data.isEmpty()
            val prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1
            val nextKey =
                if (endOfPaginationReached) null else position + (params.loadSize / PAGING_SIZE)
            LoadResult.Page(data, prevKey, nextKey)
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }


    fun getAllPhotos(
        page: Int,
        loadSize: Int,
        currentLocation: String?,
    ): MutableList<Photo> {
        val galleryImageList = mutableListOf<Photo>()
        var selection: String? = null
        var selectionArgs: Array<String>? = null
        if (currentLocation != null) {
            selection = "${MediaStore.Images.Media.DATA} LIKE ?"
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.Q) "${MediaStore.MediaColumns.RELATIVE_PATH} LIKE ?" else "${MediaStore.Images.Media.DATA} LIKE ?"
            selectionArgs = arrayOf("%$currentLocation%")
        }
        val limit = loadSize
        val offset = (page - 1) * loadSize
        val query = getQuery(offset, limit, selection, selectionArgs)
        query?.use { cursor ->
            while (cursor.moveToNext()) {
                val id =
                    cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns._ID))
                val name =
                    cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.DISPLAY_NAME))
                val filepath =
                    cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.DATA))
                val date =
                    cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.DATE_TAKEN))
                val contentUri = ContentUris.withAppendedId(uriExternal, id)
                val image = Photo(
                    id = id,
                    filepath = filepath,
                    uri = contentUri,
                    name = name,
                    date = date ?: "",
                    size = 0,
                )
                galleryImageList.add(image)
            }
        }
        return galleryImageList
    }

    private fun getQuery(
        offset: Int,
        limit: Int,
        selection: String?,
        selectionArgs: Array<String>?,
    ) = if (Build.VERSION.SDK_INT > Build.VERSION_CODES.Q) {
        val bundle = bundleOf(
            ContentResolver.QUERY_ARG_OFFSET to offset,
            ContentResolver.QUERY_ARG_LIMIT to limit,
            ContentResolver.QUERY_ARG_SORT_COLUMNS to arrayOf(MediaStore.Files.FileColumns.DATE_MODIFIED),
            ContentResolver.QUERY_ARG_SORT_DIRECTION to ContentResolver.QUERY_SORT_DIRECTION_DESCENDING,
            ContentResolver.QUERY_ARG_SQL_SELECTION to selection,
            ContentResolver.QUERY_ARG_SQL_SELECTION_ARGS to selectionArgs,
        )
        contentResolver.query(uriExternal, projection, bundle, null)
    } else {
        contentResolver.query(
            uriExternal,
            projection,
            selection,
            selectionArgs,
            "$sortedOrder DESC LIMIT $limit OFFSET $offset",
        )
    }


    companion object {
        const val STARTING_PAGE_INDEX = 1
        const val PAGING_SIZE = 28
    }
}