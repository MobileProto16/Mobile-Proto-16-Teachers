package com.david.lab1;

import android.util.Log;

import com.amazonaws.mobileconnectors.s3.transferutility.TransferListener;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferState;

/**
 * Created by david on 9/24/16.
 */
class MyListener implements TransferListener {
    private static final String tag = MyListener.class.getName();
    MyListener() {
    }

    @Override
    public void onStateChanged(int id, TransferState state) {
        Log.d(tag, "State Change");
        Log.d(tag, state.toString());
    }

    @Override
    public void onProgressChanged(int id, long bytesCurrent, long bytesTotal) {
        Log.d(tag, "Progress Change");

    }

    @Override
    public void onError(int id, Exception ex) {

        Log.d(tag, "Error Change");
        Log.d(tag, ex.toString());

    }
}
