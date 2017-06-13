package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.udacity.gradle.StageActivity;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements JokesAsyncTask.CallBack {

    private ProgressBar mLoading;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        mLoading = (ProgressBar)root.findViewById(R.id.progressBar);
        root.findViewById(R.id.tellJoke).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoading.setVisibility(View.VISIBLE);
                new JokesAsyncTask()
                        .execute(new Pair<JokesAsyncTask.CallBack, Boolean>(MainActivityFragment.this, true));
            }
        });
        return root;
    }

    @Override
    public void onResult(String result) {
        mLoading.setVisibility(View.GONE);
        StageActivity.launch(result, getActivity());
    }
}
