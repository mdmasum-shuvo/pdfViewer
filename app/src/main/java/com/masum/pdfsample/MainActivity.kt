package com.masum.pdfsample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.masum.pdf_view.AppinionPdfActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.btn).setOnClickListener {
            var intent = Intent(this, AppinionPdfActivity::class.java).putExtra("pdf", "http://www.pdf995.com/samples/pdf.pdf")
            startActivity(intent)
        }

    }
}