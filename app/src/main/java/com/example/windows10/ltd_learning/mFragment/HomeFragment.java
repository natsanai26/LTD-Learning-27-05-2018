package com.example.windows10.ltd_learning.mFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.windows10.ltd_learning.R;
import com.example.windows10.ltd_learning.mRecycler.MyAdapter;

/**
 * Created by Windows10 on 10/10/2017.
 */

public class HomeFragment extends Fragment {
    String[] home = {"Video1","Video2","Video3","Video4","Video5","Video7","Video8","Video9","Video10"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.home_fragment,container,false);

        RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.home_RV);
        rv.setLayoutManager(new LinearLayoutManager(this.getActivity(),LinearLayoutManager.HORIZONTAL,false));

        MyAdapter adapter = new MyAdapter(this.getActivity(),home);
        rv.setAdapter(adapter);

        return rootView;
    }
}
