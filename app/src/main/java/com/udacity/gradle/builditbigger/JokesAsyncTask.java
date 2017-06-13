package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;
import android.support.v4.util.Pair;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.jokes.myApi.MyApi;

import java.io.IOException;

/**
 * Created by t-xu on 2017/06/12.
 */

public class JokesAsyncTask extends AsyncTask<Pair<JokesAsyncTask.CallBack, Boolean>, Void, String>  {

    private static MyApi sMyApiService = null;
    private CallBack mListener;

    @Override
    protected String doInBackground(Pair<CallBack, Boolean>... params) {
        if (sMyApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver
            sMyApiService = builder.build();
        }

        mListener = params[0].first;
        boolean isPaid = params[0].second;

        try {
            return sMyApiService.getJoke(isPaid).execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        mListener.onResult(result);
    }

    public interface CallBack {

        void onResult(String result);
    }
}
