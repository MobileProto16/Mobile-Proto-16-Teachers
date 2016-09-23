package com.david.lab1;

import android.content.Context;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.regions.Regions;

/**
 * Created by david on 9/23/16.
 */

public class S3Utils {


    public S3Utils(Context context, String identityPoolId) {
        CognitoCachingCredentialsProvider credentialsProvider =
                new CognitoCachingCredentialsProvider(
                context, identityPoolId, // Identity Pool ID
                Regions.US_WEST_2 // Region
        );

    }

}
