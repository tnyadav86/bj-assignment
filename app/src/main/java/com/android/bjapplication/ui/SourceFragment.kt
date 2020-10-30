package com.android.bjapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.android.bjapplication.R
import com.android.bjapplication.model.Source
import com.android.bjapplication.network.Constants
import com.android.bjapplication.network.DataResult
import com.android.bjapplication.util.RecyclerViewItemClickListener
import com.android.bjapplication.util.gone
import com.android.bjapplication.util.isNetworkConnected
import com.android.bjapplication.util.visible
import com.android.bjapplication.viewmodel.SourceFragmentViewModel
import kotlinx.android.synthetic.main.all_news_fragment.*
import kotlinx.android.synthetic.main.source_fragment.*
import kotlinx.android.synthetic.main.source_fragment.errorInfo
import kotlinx.android.synthetic.main.source_fragment.progressBar
import kotlinx.android.synthetic.main.source_fragment.recyclerView
import kotlinx.android.synthetic.main.source_fragment.swipeRefreshLayout
import javax.inject.Inject

class SourceFragment : BaseDaggerFragment(), RecyclerViewItemClickListener {

    private lateinit var viewAdapter: SourceRecyclerViewAdapter

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
        swipeRefreshLayout.apply {
            setOnRefreshListener {

                isRefreshing=true
                viewModel.fetchSource()

            }
        }
        recyclerView.apply {
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            viewAdapter = SourceRecyclerViewAdapter(this@SourceFragment)
            adapter=viewAdapter
        }



        viewModel.sourceListLivedata.observe(viewLifecycleOwner, Observer {
            val result = it ?: return@Observer
            swipeRefreshLayout.isRefreshing=false
            progressBar.gone()
            errorInfo.gone()
            viewAdapter.updateItem(result)

        })

        viewModel.errorStatusLiveData.observe(viewLifecycleOwner, Observer {
            val result = it ?: return@Observer
            swipeRefreshLayout.isRefreshing=false
            if (viewAdapter.itemCount==0){
                errorInfo.text = result
                errorInfo.visible()
            }else{
                errorInfo.gone()
                Toast.makeText(appCompatActivity,result,Toast.LENGTH_LONG).show()
            }

        })
        progressBar.visible()
        viewModel.fetchSource()
    }

    override fun onItemClick(data: Any?) {
        when(data){
            is Source ->{
                val action =  SourceFragmentDirections.actionSourceFragmentToTopNewsNewsFragment(data.id)
                findNavController().navigate(action)
            }

        }

    }

}