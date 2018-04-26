package app.sunshine.com.example.android.movieapp2;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */


public class MyFavouritFragment extends Fragment {

    GridView gridView;
    myadapter adapter;
    NameListener nameListener;

    public MyFavouritFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_my_favourit, container, false);


//        VivsdatabaseAdapter vivzhelper =new VivsdatabaseAdapter(getContext());
//
//
//        ArrayList<Movie> data= vivzhelper.getAllData();

        Uri uri = (VivzProvider.CONTENT_URI);
        ArrayList list = VivzProvider.getAllData(getContext(), uri);


        gridView = (GridView) root.findViewById(R.id.Favourits_GridView);
        adapter = new myadapter(getContext());
        gridView.setAdapter(adapter);
        adapter.Getview(list);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                myadapter adapter = (myadapter) parent.getAdapter();
                Movie movie = (Movie) adapter.getItem(position);
                nameListener.setSelectedItem(movie);
            }
        });


        return root;
    }

    public void setNameListener(NameListener nameListener) {
        this.nameListener = nameListener;
    }

}
