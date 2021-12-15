package com.example.winkel;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

import static com.example.winkel.HomeActivity.redirectActivity;

public class NetCheck extends AppCompatActivity {
    //initialize variable
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_check);

        //Assign variable
        webView = findViewById(R.id.web_view);

        //intialize websetting
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        //Initializeconectivity

        ConnectivityManager connectivityManager = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        // get active network info
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        //check network status
        if (networkInfo == null  || !networkInfo.isConnected() || !networkInfo.isAvailable()){
            // when net is inactive

            //initialize dialog
            Dialog dialog = new Dialog(this);
            //set content view
            dialog.setContentView(R.layout.alert_dialog);
            //set outside touch
            dialog.setCanceledOnTouchOutside(false);
            //set dialog width and height
            dialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT,WindowManager.LayoutParams.WRAP_CONTENT);
            //set transparent background
            dialog.getWindow().getAttributes().windowAnimations= android.R.style.Animation_Dialog;

            //initialize dialog variable
            Button btTryAgain = dialog.findViewById(R.id.bt_try_again);
            //perform on click listener
            btTryAgain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // call recreate method
                    recreate();
                }
            });

            //show dialog
            dialog.show();

        }else {
            //redirect to homepage
            redirectActivity(this, HomeActivity.class);
        }
    }
}