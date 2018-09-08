package com.udacity.gradle.builditbigger;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Pair;
import android.widget.Toast;

import com.example.android.javajokes.Joker;
import com.example.android.myapplibrary.AppLibraryActivity;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

import static android.content.ContentValues.TAG;
import static android.support.v4.content.ContextCompat.startActivity;


class EndpointsAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {
    private static MyApi myApiService = null;
    private Context context;
    private Activity activity;


    @Override
    protected String doInBackground(Pair<Context, String>... params) {
        Log.d(TAG, "Endpoint..doInBackground: Starting");
        if(myApiService == null) {  // Only do this once
            Log.d(TAG, "Endpoint..doInBackground: myApiService is null, so starting");
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

            myApiService = builder.build();
        }

        context = params[0].first;
        String name = params[0].second;

        try {
           ///Below changed to get a joke
            Log.d(TAG, "Endpoint..doInBackground: about to call sayHi");

            return myApiService.sayHi(name).execute().getData();

        } catch (IOException e) {
            Log.d(TAG, "Endpoint..doInBackground: sayHi call failed message:" + e.getMessage());
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        //Instead of Toast message, launch activity
      //  Toast.makeText(context, result, Toast.LENGTH_LONG).show();

        //Joker myJoker = new Joker();
        //String joke = myJoker.getJoke();
        // Toast.makeText(this, joke, Toast.LENGTH_SHORT).show();
        Log.d(TAG, "EndpoitnsAsyncTask.onPostExecute: launching activity AppLibraryActivity ");
        //launch activity, pass joke
        Intent myIntent = new Intent(context, AppLibraryActivity.class);
        //add joke
        myIntent.putExtra("joke",result );
        context.startActivity( myIntent);
    }
}