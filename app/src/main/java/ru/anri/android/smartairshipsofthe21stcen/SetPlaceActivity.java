package ru.anri.android.smartairshipsofthe21stcen;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

public class SetPlaceActivity extends AppCompatActivity implements View.OnClickListener {
    private Button buttonDelivery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_place);

        buttonDelivery = findViewById(R.id.buttonDelivery);
        buttonDelivery.setOnClickListener(this);

        WebView webview = findViewById(R.id.webView);
        webview.getSettings().setJavaScriptEnabled(true);

        WebViewClient webViewClient = new WebViewClient() {
            @SuppressWarnings("deprecation") @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @TargetApi(Build.VERSION_CODES.N) @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.getUrl().toString());
                return true;
            }

            @Override public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
//                Toast.makeText(getApplicationContext(), getString(R.string.mapTextAfter), Toast.LENGTH_SHORT).show();
            }

            @Override public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
//                Toast.makeText(getApplicationContext(), getString(R.string.mapTextBefore), Toast.LENGTH_SHORT).show();
            }
        };
        webview.setWebViewClient(webViewClient);
        webview.loadUrl(getString(R.string.mapurl));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonDelivery:
                Intent intent = new Intent(this, DeliveryActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
