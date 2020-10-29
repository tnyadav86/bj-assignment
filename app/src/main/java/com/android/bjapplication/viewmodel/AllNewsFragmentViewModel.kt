package com.android.bjapplication.viewmodel

import androidx.lifecycle.ViewModel
import com.android.bjapplication.model.repository.AllNewsRepository
import javax.inject.Inject

class AllNewsFragmentViewModel @Inject constructor(private val allNewsRepository :AllNewsRepository): ViewModel() {
    // TODO: Implement the ViewModel
}