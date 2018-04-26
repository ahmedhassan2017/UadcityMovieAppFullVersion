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

public class ReviewAdapter extends BaseAdapter {

    Context context;
    ArrayList<Reviews> list;

    public ReviewAdapter(Context context) {
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

    public void Getview(ArrayList<Reviews> list) {
        this.list.clear();
        this.list.addAll(list);
        this.notifyDataSetChanged(); // call getview and update new data
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.reviewitem, null);

        }
        Reviews reviews = (Reviews) getItem(position);

        TextView textView1 = (TextView) convertView.findViewById(R.id.authorTextview);
        TextView textView2 = (TextView) convertView.findViewById(R.id.contentTextview);

        textView1.setText(reviews.author);
        textView2.setText(reviews.content);

        return convertView;
    }
}
