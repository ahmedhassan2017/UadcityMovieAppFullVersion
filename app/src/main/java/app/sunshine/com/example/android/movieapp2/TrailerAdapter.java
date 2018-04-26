package app.sunshine.com.example.android.movieapp2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Ahmed Hassan on 11/16/2016.
 */

public class TrailerAdapter extends BaseAdapter {

    Context context;
    ArrayList<Trailers> list;

    public TrailerAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
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

    public void Getview(ArrayList<Trailers> list) {
        this.list.clear();
        this.list.addAll(list);
        this.notifyDataSetChanged(); // call getview and update new data
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.trailer_item, null);

        }
        Trailers trailers = (Trailers) getItem(position);

        TextView textView= (TextView) convertView.findViewById(R.id.trailer_Textview);
        textView.setText(trailers.name);


        return convertView;
    }
}
