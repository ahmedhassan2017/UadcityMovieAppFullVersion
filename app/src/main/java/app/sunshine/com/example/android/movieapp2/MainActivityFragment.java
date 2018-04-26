package app.sunshine.com.example.android.movieapp2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;


public class MainActivityFragment extends Fragment {

    GridView gridview;
    myadapter adapter;
    NameListener nameListener;


    public MainActivityFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        setHasOptionsMenu(true);
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        Log.e("MainActivityFragment", "hello");
        gridview = (GridView) root.findViewById(R.id.gridview);
        adapter = new myadapter(getContext());
        MyAsyncTask task = new MyAsyncTask(adapter);
        gridview.setAdapter(adapter);

        task.execute("http://api.themoviedb.org/3/movie/popular?api_key=600908f259c6e4fe3bbba5ce046ee937");

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                myadapter adapter = (myadapter) parent.getAdapter();
                Movie movieposition = (Movie) adapter.getItem(position);

                nameListener.setSelectedItem(movieposition);
            }
        });

        return root;
    }




    public void setNameListener(NameListener nameListener) {
        this.nameListener = nameListener;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.TopRated) {
            MyAsyncTask task2 = new MyAsyncTask(adapter);
            task2.execute("http://api.themoviedb.org/3/movie/top_rated?api_key=600908f259c6e4fe3bbba5ce046ee937");

        } else if (id == R.id.Popular) {
            MyAsyncTask task2 = new MyAsyncTask(adapter);
            task2.execute("http://api.themoviedb.org/3/movie/popular?api_key=600908f259c6e4fe3bbba5ce046ee937");
        } else if (id == R.id.Favourit) {
            Intent intent = new Intent(getActivity(), MyFavourit.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }


}
