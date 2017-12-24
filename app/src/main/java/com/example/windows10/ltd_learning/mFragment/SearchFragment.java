package com.example.windows10.ltd_learning.mFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.windows10.ltd_learning.Course;
import com.example.windows10.ltd_learning.MainActivity;
import com.example.windows10.ltd_learning.R;
import com.example.windows10.ltd_learning.mRecycler.Adapter;
import com.miguelcatalan.materialsearchview.MaterialSearchView;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Windows10 on 10/10/2017.
 */

public class SearchFragment extends Fragment implements MaterialSearchView.OnQueryTextListener, LoaderManager.LoaderCallbacks<Object> {
    private MaterialSearchView searchView;
    private ListView listView;
    private RecyclerView rv;
    private String[] course = {"Course ONE","Course TWO","Course TREE","Course FOUR","Course FIVE","Course SIX","Course SEVEN","Course EIGHT"};
    private ArrayAdapter<String> adapter;
    String mCurFilter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.search_fragment,container,false);
        listView = (ListView)rootView.findViewById(R.id.listView_id);
        searchView = (MaterialSearchView)rootView.findViewById(R.id.search_view_search);

        setHasOptionsMenu(true);


        return rootView;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_item, menu);
        MenuItem item = menu.findItem(R.id.search);
        MaterialSearchView sv = new MaterialSearchView(((MainActivity) getActivity()).getSupportActionBar().getThemedContext());
        MenuItemCompat.setShowAsAction(item, MenuItemCompat.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | MenuItemCompat.SHOW_AS_ACTION_IF_ROOM);
        MenuItemCompat.setActionView(item, sv);
        adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,course);
        listView.setAdapter(adapter);
        sv.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                System.out.println("search query submit");
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText != null && !newText.isEmpty()){
                    List<String> lstFound = new ArrayList<String>();
                    for(String item:course){
                        if(item.contains(newText))
                            lstFound.add(item);
                    }
                    ArrayAdapter adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,lstFound);
                    listView.setAdapter(adapter);
                }
                return true;
            }
        });
    }

    @Override
    public boolean onQueryTextSubmit(String newText) {
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        mCurFilter = !TextUtils.isEmpty(newText) ? newText : null;
        getLoaderManager().restartLoader(0, null, this);
        return true;
    }

    @Override
    public Loader<Object> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Object> loader, Object data) {

    }

    @Override
    public void onLoaderReset(Loader<Object> loader) {

    }
}
