package com.example.easylearn;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

public class medigk extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medigk);
        PDFView pdfView = findViewById(R.id.pdfview);
        pdfView.fromAsset("medigk.pdf").load();
    }
}