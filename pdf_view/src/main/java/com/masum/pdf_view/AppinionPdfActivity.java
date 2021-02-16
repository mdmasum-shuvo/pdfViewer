package com.masum.pdf_view;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mypdfviewlibrary.Constant;

public class AppinionPdfActivity extends AppCompatActivity {

    private WebView pdfView;
    private ProgressBar progress;
    private String removePdfTopIcon = "javascript:(function() {" + "document.querySelector('[role=\"toolbar\"]').remove();})()";
    private int i = 0;
    private ImageView imageViewLeft, imageViewRight;
    private String[] strArray = {
            "http://www.pdf995.com/samples/pdf.pdf"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appinion_pdf_view);

        pdfView = findViewById(R.id.pdfView);
        progress = findViewById(R.id.progress);
        String url=getIntent().getStringExtra("pdf");
        imageViewLeft = findViewById(R.id.iv_previous);
        imageViewRight = findViewById(R.id.iv_next);
        showPdfFile(url);



        imageViewLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (i != 0) {
                    showPdfFile(strArray[i--]);
                } else {
                    Toast.makeText(getApplicationContext(), "You're on first PDF", Toast.LENGTH_SHORT).show();
                }
            }
        });

        imageViewRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (i != strArray.length) {
                    showPdfFile(strArray[i++]);
                } else {
                    Toast.makeText(getApplicationContext(), "Going to first PDF", Toast.LENGTH_SHORT).show();
                    showPdfFile(strArray[0]);
                }
            }
        });

    }

    private void showPdfFile(final String pdfUrl) {
        showProgress();
        pdfView.invalidate();
        pdfView.getSettings().setJavaScriptEnabled(true);
        pdfView.getSettings().setSupportZoom(true);
        pdfView.loadUrl(Constant.VIEW_URL+pdfUrl);
        pdfView.setWebViewClient(new WebViewClient() {
            boolean checkOnPageStartedCalled = false;

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                checkOnPageStartedCalled = true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                if (checkOnPageStartedCalled) {
                    pdfView.loadUrl(removePdfTopIcon);
                    hideProgress();
                } else {
                    showPdfFile(pdfUrl);
                }
            }
        });
    }

    public void showProgress() {
        progress.setVisibility(View.VISIBLE);
    }

    public void hideProgress() {
        progress.setVisibility(View.GONE);
    }

}

