package app.sunshine.com.example.android.movieapp2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Ahmed Hassan on 11/2/2016.
 */

public class myadapter extends BaseAdapter {
    Context context ;
    ArrayList<Movie> list;
    public myadapter(Context context)
    {
        this.context=context;
        list=new ArrayList<>();

    }



    public void Getview (ArrayList<Movie> list)
    {
        this.list.clear();
        this.list.addAll(list);
        this.notifyDataSetChanged(); // call getview and update new data from list to gridview
    }

   
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.single_item,null);

        }

        Movie movie = (Movie) getItem(position);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageview);
        TextView textView = (TextView) convertView.findViewById(R.id.text);

        String poster="http://image.tmdb.org/t/p/w342/"+movie.poster_path;
        Picasso.with(context).load(poster).into(imageView);
      //  Log.e("ffff",poster);

        textView.setText(movie.title);



        return convertView;
    }


}
