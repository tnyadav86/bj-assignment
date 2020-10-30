package com.android.bjapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.bjapplication.R
import com.android.bjapplication.network.DataResult
import com.android.bjapplication.viewmodel.SourceFragmentViewModel
import kotlinx.android.synthetic.main.source_fragment.*
import javax.inject.Inject

class SourceFragment : BaseDaggerFragment() {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProvider.Factory
    private val viewModel: SourceFragmentViewModel by lazy {
        ViewModelProvider(this, viewModelProviderFactory).get(SourceFragmentViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.source_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.sourceListLivedata.observe(viewLifecycleOwner, Observer {
            val result = it ?: return@Observer
            text.setText(result.toString())
        })

        viewModel.errorStatusLiveData.observe(viewLifecycleOwner, Observer {
            val result = it ?: return@Observer
            Toast.makeText(appCompatActivity,result,Toast.LENGTH_LONG).show()
        })
        viewModel.fetchSource()
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(appCompatActivity,"onDestroy",Toast.LENGTH_LONG).show()
    }
}