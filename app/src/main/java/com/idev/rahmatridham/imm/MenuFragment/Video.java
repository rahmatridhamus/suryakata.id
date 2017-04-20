package com.idev.rahmatridham.imm.MenuFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.idev.rahmatridham.imm.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Video extends Fragment {

    WebView webView;
    public Video() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_video, container, false);
        webView = (WebView) v.findViewById(R.id.youtubeweb);
        webView.getSettings().setJavaScriptEnabled(true);
//        webView.setWebViewClient(new MyWebViewClient());
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url)
            {
                webView.loadUrl("javascript:(function() { " +
                        "document.getElementsByTagName('header')[0].style.display="+"none"+"; " +
                        "})()");
            }
        });
        String url = getString(R.string.urlYoutube);
        webView.loadUrl(url);
        return v;
    }

//    private class MyWebViewClient extends WebViewClient {
//        @Override
//        public boolean shouldOverrideUrlLoading(WebView view, String url) {
//            view.loadUrl(url);
//            return true;
//        }
//    }

    @Override
    public void onResume()
    {
        super.onResume();
        webView.onResume();
    }

    @Override
    public void onPause()
    {
        super.onPause();
        webView.onPause();
    }

}
