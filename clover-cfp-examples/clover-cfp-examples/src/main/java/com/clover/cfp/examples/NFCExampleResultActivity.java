package com.clover.cfp.examples;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.clover.cfp.activity.CloverCFPActivity;

public class NFCExampleResultActivity extends CloverCFPActivity {
    private String TAG = "NFCExampleResultActivity";
    private Button scanAgain;
    private BroadcastReceiver mStateChangeReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            if (action.equals(NfcAdapter.ACTION_ADAPTER_STATE_CHANGED)) {
                final int state = intent.getIntExtra(NfcAdapter.EXTRA_ADAPTER_STATE, NfcAdapter.STATE_OFF);

                switch (state) {
                    case NfcAdapter.STATE_ON:
                        Log.d(TAG, "NFC is now enabled");
                        break;
                    case NfcAdapter.STATE_TURNING_ON:
                        Log.d(TAG, "NFC is now disabled");
                        scanAgain.setBackgroundColor(Color.GREEN);
                        scanAgain.setClickable(true);
                        break;
                    default:
                        Log.e(TAG, "Unused NFC adapter state" + state);
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc_result);
        IntentFilter filter = new IntentFilter(NfcAdapter.ACTION_ADAPTER_STATE_CHANGED);
        this.registerReceiver(mStateChangeReceiver, filter);
        scanAgain = (Button) findViewById(R.id.scanAgain);
        scanAgain.setClickable(false);
        scanAgain.setBackgroundColor(Color.RED);
    }

    @Override
    protected void onMessage(String s) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.unregisterReceiver(mStateChangeReceiver);
    }

    public void finishClicked(View view) {
        finish();
    }
}