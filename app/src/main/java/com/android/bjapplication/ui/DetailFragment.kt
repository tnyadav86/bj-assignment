package com.android.bjapplication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.navigation.fragment.navArgs
import com.android.bjapplication.R
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment() {
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageUrl = args.url
        val settings = webview?.settings
        settings?.javaScriptEnabled = true
        webview?.webChromeClient = MyWebChromeClient()
        webview.loadUrl(imageUrl)
    }
    internal inner class MyWebChromeClient : WebChromeClient() {


        override fun onProgressChanged(view: WebView, newProgress: Int) {
            super.onProgressChanged(view, newProgress)
            try {
                pb.setProgress(newProgress)
            } catch (e: Exception) {

            }
            try {
                if (newProgress > 70 && pb.visibility == View.VISIBLE) {
                    pb.visibility = View.GONE
                }
            } catch (e: Exception) {
            }

        }
    }
}