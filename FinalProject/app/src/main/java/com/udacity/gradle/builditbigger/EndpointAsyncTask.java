package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.util.Pair;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

/**
 * Created by Arkadiusz Wilczek on 15.05.18.
 */

class EndpointAsyncTask extends AsyncTask<Pair<EndpointAsyncTask.EndpointListener, String>, Void, String> {
    private static MyApi myApiService = null;
    private Context context;
    private EndpointListener mListener;

    public interface EndpointListener {
        void onReceiveEndpointResult(String result);
    }

    @Override
    protected String doInBackground(Pair<EndpointAsyncTask.EndpointListener, String>[] pairs) {
        if (myApiService == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> request) throws IOException {
                            request.setDisableGZipContent(true);
                        }
                    });
            myApiService = builder.build();
        }

        mListener = pairs[0].first;

        try {
            return myApiService.getJoke().execute().getJokeString();
        } catch(IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        mListener.onReceiveEndpointResult(s);
    }
}