package com.example.windows10.ltd_learning.mFragment;


import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.devbrackets.android.exomedia.listener.OnPreparedListener;
import com.devbrackets.android.exomedia.ui.widget.VideoView;
import com.example.windows10.ltd_learning.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopOnCourseDetailFragment extends Fragment implements OnPreparedListener {

    private VideoView videoView;
    public TopOnCourseDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.fragment_top_on_course_detail, container, false);
        videoView = (VideoView) view.findViewById(R.id.video_view);
        setupVideoView();
        return view;
    }
    private void setupVideoView() {
        // Make sure to use the correct VideoView import
        videoView.setOnPreparedListener(this);

        //For now we just picked an arbitrary item to play
        videoView.setVideoURI(Uri.parse("http://158.108.207.7:8080/api/ts/key999/25/30/index.m3u8"));
    }

    @Override
    public void onPrepared() {
        //Starts the video playback as soon as it is ready
        videoView.pause();
    }
}
