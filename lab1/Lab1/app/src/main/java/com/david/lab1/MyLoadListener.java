package com.david.lab1;

import com.amazonaws.mobileconnectors.s3.transferutility.TransferState;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

/**
 * Created by david on 9/24/16.
 */
class MyLoadListener extends MyListener {
    private File f;
    private s3LoadCustomersCallback slc;
    private static final String tag = MyLoadListener.class.getName();

    MyLoadListener(File f, s3LoadCustomersCallback slc) {
        this.f = f;
        this.slc = slc;
    }

    @Override
    public void onStateChanged(int id, TransferState state) {
        super.onStateChanged(id, state);
        try {
            if (state.toString().equalsIgnoreCase("completed")) {
                FileInputStream fin = new FileInputStream(f);
                ObjectInputStream ois = new ObjectInputStream(fin);
                Object o = ois.readObject();
                slc.receiveCustomers((ArrayList) o);
            }
        } catch (IOException e) {} catch (ClassNotFoundException e) {}
    }
}
