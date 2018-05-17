package com.example.jokedisplay;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class jokeActivityFragment extends Fragment {

    public jokeActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_joke, container, false);
        TextView jokeTextView = view.findViewById(R.id.fragment_joke_text_view);
        Intent intent = getActivity().getIntent();
        if ( intent != null && intent.hasExtra(jokeActivity.EXTRA_JOKE)) {
            String joke = intent.getStringExtra(jokeActivity.EXTRA_JOKE);
            if (joke != null) {
                jokeTextView.setText(joke);
            }
        }
        return view;
    }
}
