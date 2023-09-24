package com.dadada.onecloset.presentation.viewmodel.codi

import androidx.lifecycle.ViewModel
import com.dadada.onecloset.domain.usecase.codi.DeleteCodiUseCase
import com.dadada.onecloset.domain.usecase.codi.PutCodiUseCase
import com.dadada.onecloset.domain.usecase.codi.UpdateCodiDateUseCase
import com.dadada.onecloset.domain.usecase.codi.UpdateCodiUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CodiViewModel @Inject constructor(
    private val putCodiUseCase: PutCodiUseCase,
    private val updateCodiDateUseCase: UpdateCodiDateUseCase,
    private val updateCodiUseCase: UpdateCodiUseCase,
    private val deleteCodiUseCase: DeleteCodiUseCase
) : ViewModel() {

}