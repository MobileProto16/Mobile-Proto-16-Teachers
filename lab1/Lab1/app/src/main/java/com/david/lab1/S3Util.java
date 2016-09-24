package com.david.lab1;

import android.content.Context;
import android.util.Log;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferListener;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferState;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import static java.io.File.createTempFile;

/**
 * Created by david on 9/23/16.
 */

class S3Util {

    private TransferUtility transferUtility;
    private static final String BUCKET_NAME = "mobpro-lab1-bucket";
    public static final String MENU_KEY = "bill-david-menu";
    public static final String CUSTOMER_KEY = "bill-david-uigiugui";

    private static final String tag = S3Util.class.getName();


    S3Util(Context context, String identityPoolId) {
        // Sample usage:
        // S3Util(getContext(), S3Credentials.COGNITO_POOL_ID)
        CognitoCachingCredentialsProvider credentialsProvider =
                new CognitoCachingCredentialsProvider(
                context, identityPoolId, // Identity Pool ID
                Regions.US_WEST_2 // Region
        );
        // Create an S3 client
        AmazonS3 s3 = new AmazonS3Client(credentialsProvider);
        transferUtility = new TransferUtility(s3, context);

    }

    public boolean saveObjectToS3(Object items, String key) {
        // Sample usage: saveObjectToS3(menu_items_array_list, S3Util.MENU_KEY)

        try {
            Log.d(tag, "Saving to s3");
            File f = createTempFile("menu", null); // suffix is null
            FileOutputStream fout = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(items);
            TransferObserver observer = transferUtility.upload(
                    BUCKET_NAME,     /* The bucket to upload to */
                    key,    /* The key for the uploaded object */
                    f        /* The file where the data to upload exists */
            );
            observer.setTransferListener(new MyObserver());
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public void loadObjectFromS3(String key, s3LoadCustomersCallback slc) {
        // Sample usage: saveObjectToS3(menu_items_array_list, S3Util.MENU_KEY)

        Log.d(tag, "Loading from s3");
        try {
            File f = createTempFile("menu", null); // suffix is null
            TransferObserver observer = transferUtility.download(
                    BUCKET_NAME,     /* The bucket to download from */
                    key,    /* The key for the object to download */
                    f        /* The file to download the object to */
            );
            observer.setTransferListener(new MyLoadObserver(f, slc));
        } catch (IOException e) {
        }
    }

}

class MyObserver implements TransferListener {
    private static final String tag = MyObserver.class.getName();
    MyObserver() {
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

class MyLoadObserver extends MyObserver {
    private File f;
    private s3LoadCustomersCallback slc;
    private static final String tag = MyLoadObserver.class.getName();

    MyLoadObserver(File f, s3LoadCustomersCallback slc) {
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

