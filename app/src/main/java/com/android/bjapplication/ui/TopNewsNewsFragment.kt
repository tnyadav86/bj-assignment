package com.android.bjapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.android.bjapplication.R
import com.android.bjapplication.model.Article
import com.android.bjapplication.network.Constants
import com.android.bjapplication.network.TaskStatusResult
import com.android.bjapplication.util.RecyclerViewItemClickListener
import com.android.bjapplication.util.gone
import com.android.bjapplication.util.isNetworkConnected
import com.android.bjapplication.util.visible
import com.android.bjapplication.viewmodel.AllNewsFragmentViewModel
import com.android.bjapplication.viewmodel.SourceFragmentViewModel
import com.android.bjapplication.viewmodel.TopNewsFragmentViewModel
import kotlinx.android.synthetic.main.all_news_fragment.*
import javax.inject.Inject

class TopNewsNewsFragment : BaseDaggerFragment(), RecyclerViewItemClickListener {
    private val args: TopNewsNewsFragmentArgs by navArgs()
    @Inject
    lateinit var viewModelProviderFactory: ViewModelProvider.Factory

    private val viewModel: TopNewsFragmentViewModel by lazy {
        ViewModelProvider(this, viewModelProviderFactory).get(TopNewsFragmentViewModel::class.java)
    }
    private val taskStatusDataObserver = Observer<TaskStatusResult> {
        val result = it ?: return@Observer
        when (result) {
            is TaskStatusResult.Loading -> {
                progressBar.visible()
            }

            is TaskStatusResult.Success -> {
                progressBar.gone()
            }

            is TaskStatusResult.Error -> {
                result.errorMessage?.let { it1 ->  Toast.makeText(appCompatActivity,it1, Toast.LENGTH_LONG).show() }
                progressBar.gone()
            }
        }

    }

    private val refreshTaskStatusDataObserver = Observer<TaskStatusResult> {
        when (it) {

            is TaskStatusResult.Success -> {
                swipeRefreshLayout.isRefreshing=false

            }

            is TaskStatusResult.Error -> {
                swipeRefreshLayout.isRefreshing=false
                Toast.makeText(appCompatActivity,it.errorMessage, Toast.LENGTH_LONG).show()
            }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.all_news_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val articleRecyclerViewAdapter = ArticleRecyclerViewAdapter(this)
        recyclerView.adapter = articleRecyclerViewAdapter
        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing =true
            viewModel.refreshArticle(args.source).observe(viewLifecycleOwner,refreshTaskStatusDataObserver)
        }

        viewModel.articleListLivedata.observe(viewLifecycleOwner, Observer {
            val result = it ?: return@Observer
            progressBar.gone()
            articleRecyclerViewAdapter.submitList(result)


        })
        viewModel.taskStatusLiveData.observe(viewLifecycleOwner, taskStatusDataObserver)

        viewModel.getArticle(args.source)

    }

    override fun onItemClick(data: Any?) {
         when(data){
             is Article ->{
                 val action = data.url?.let {
                     TopNewsNewsFragmentDirections.actionTopNewsNewsFragmentToDetailFragment3(it)
                 }
                 if (action != null) {
                     findNavController().navigate(action)
                 }
             }
         }
    }

}