package com.example.natifetest.ui.first_screen

import com.example.natifetest.data.repository.MainRepository
import com.example.natifetest.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FirstScreenViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : BaseViewModel() {


}