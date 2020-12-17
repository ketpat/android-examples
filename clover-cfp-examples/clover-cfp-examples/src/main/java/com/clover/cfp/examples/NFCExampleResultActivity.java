package com.clover.cfp.examples;

import android.os.Bundle;
import android.view.View;

import com.clover.cfp.activity.CloverCFPActivity;

public class NFCExampleResultActivity extends CloverCFPActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc_result);
    }

    @Override
    protected void onMessage(String s) {

    }

    public void finishClicked(View view) {
        finish();
    }
}