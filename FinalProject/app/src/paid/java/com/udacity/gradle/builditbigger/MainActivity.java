package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.jokedisplay.jokeActivity;
import com.udacity.gradle.builditbigger.test.SimpleIdlingResource;


public class MainActivity extends AppCompatActivity implements EndpointAsyncTask.EndpointListener{
    private IdlingResource mIdlingResource;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mProgressBar = findViewById(R.id.progress_bar);
    }

    private void progressBarShow() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    private void progressBarHide() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onJokeRequestClick(View view) {
        progressBarShow();
        Pair pair = Pair.create(this,"");
        ((SimpleIdlingResource)getIdlingResource()).setIdleState(false);
        new EndpointAsyncTask()
                .execute(pair);
    }

    @Override
    public void onReceiveEndpointResult(String result) {
        progressBarHide();
        ((SimpleIdlingResource)getIdlingResource()).setIdleState(true);
        Intent intent = new Intent(this, jokeActivity.class);
        intent.putExtra(jokeActivity.EXTRA_JOKE, result);
        startActivity(intent);
    }

    @VisibleForTesting
    public IdlingResource getIdlingResource() {
        if (mIdlingResource == null)
            mIdlingResource = new SimpleIdlingResource();
        return mIdlingResource;
    }
}
