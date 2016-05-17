/*
 * Copyright (c) 2016. Benjamin Schulz (github.com/bysy)
 */

package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.jokebackend.myApi.MyApi;

import java.io.IOException;

/**
 * Retrieve a Joke from our backend service.
 * based on
 *   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/blob/master/HelloEndpoints/README.md
 */
public class JokeTask extends AsyncTask<JokeTask.PostJokeTask, Void, String> {
    private static MyApi theApiService;
    private PostJokeTask mCont;

    interface PostJokeTask {
        void runPostJokeTask(String joke);
    }

    @Override
    protected String doInBackground(PostJokeTask... params) {
        mCont = params[0];
        initApiService();
        try {
            return theApiService.getJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        if (mCont!=null) {
            mCont.runPostJokeTask(result);
        }
    }

    private void initApiService() {
        if(theApiService==null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver

                    // 10.0.2.2 for the build-in emulators
                    //.setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    // for genymotion use 10.0.3.2 or the IP of vboxnet0 (check via ifconfig or ipconfig)
                    .setRootUrl("http://10.0.3.2:8080/_ah/api/")

                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver
            theApiService = builder.build();
        }
    }
}
