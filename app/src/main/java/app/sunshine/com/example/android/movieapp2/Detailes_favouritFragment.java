package app.sunshine.com.example.android.movieapp2;

import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

/**
 * A placeholder fragment containing a simple view.
 */
public class Detailes_favouritFragment extends Fragment {

    boolean checker;
    public Detailes_favouritFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root= inflater.inflate(R.layout.fragment_detailes_favourit, container, false);

        final Movie movie = new Movie();

        movie.poster_path = getArguments().getString("poster_path");
        movie.id = getArguments().getString("id");
        movie.overview = getArguments().getString("overview");
        movie.original_language = getArguments().getString("original_language");
        movie.title = getArguments().getString("title");
        movie.backdrop_path = getArguments().getString("backdrop_path");
        movie.popularity = getArguments().getString("popularity");
        movie.vote_count = getArguments().getString("vote_count");
        movie.vote_average = getArguments().getString("vote_average");
        movie.release_date = getArguments().getString("release_date");


        ImageView imageView = (ImageView) root.findViewById(R.id.detail_image);
        String poster = "http://image.tmdb.org/t/p/w500/" + movie.poster_path;
        Picasso.with(getContext()).load(poster).into(imageView);


        TextView textView1 = (TextView) root.findViewById(R.id.movietitle);
        textView1.setText(movie.title);


        TextView textView3 = (TextView) root.findViewById(R.id.movie_overview);
        textView3.setText(movie.overview);

        TextView textView4 = (TextView) root.findViewById(R.id.detaile_text4);
        textView4.setText(movie.original_language);

        ImageView imageView2 = (ImageView) root.findViewById(R.id.movie_backdrop);
        String backdroppath = "http://image.tmdb.org/t/p/w500/" + movie.backdrop_path;
        Picasso.with(getContext()).load(backdroppath).into(imageView2);

        TextView textView6 = (TextView) root.findViewById(R.id.detaile_text6);
        textView6.setText(movie.popularity);

        TextView textView7 = (TextView) root.findViewById(R.id.movie_vote_count);
        textView7.setText(movie.vote_count);

        TextView textView8 = (TextView) root.findViewById(R.id.movie_vote_average);
        textView8.setText(movie.vote_average + "/10");

        TextView textView9 = (TextView) root.findViewById(R.id.movie_Release_data);
        textView9.setText(movie.release_date);




        final Button button = (Button) root.findViewById(R.id.markbutton);
        button.setEnabled(false);

        final Uri uri = (VivzProvider.CONTENT_URI);


        checker = VivzProvider.find_movie(getContext(), movie.id, uri);    // ba4ofhom f el database(favourites) mawgood walla la2

        if (checker == true) {
            button.setText("Remove From Favourites");
            button.setEnabled(true);
        } else

        {
            button.setText("Mark as Favourites");
            button.setEnabled(true);
        }


        final ContentValues contentValues = new ContentValues();
        contentValues.put(VivzHelper.POSTER, movie.poster_path);
        contentValues.put(VivzHelper.MOVIE_ID, movie.id);
        contentValues.put(VivzHelper.OVERVIEW, movie.overview);
        contentValues.put(VivzHelper.ORIGINAL_LANGUAGE, movie.original_language);
        contentValues.put(VivzHelper.TITLE, movie.title);
        contentValues.put(VivzHelper.BACK_DROPPATH, movie.backdrop_path);
        contentValues.put(VivzHelper.POPULARITY, movie.popularity);
        contentValues.put(VivzHelper.VOTE_COUNT, movie.vote_count);
        contentValues.put(VivzHelper.VOTE_AVERAGE, movie.vote_average);
        contentValues.put(VivzHelper.RELEASE_DATE, movie.release_date);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(checker==true)
                {
                    String Uid= VivzProvider.getUID(getContext(), movie.id , uri);
                    String RealURI=uri.toString()+"/"+Uid;
                    Uri Realuri2=Uri.parse(RealURI);
                    int count=getContext().getContentResolver().delete(Realuri2,null,null);
                    if (count>0) {
                        button.setText("mark as favourites");
                        checker=false;
                        Toast.makeText(getContext(), "Removed from Favourites", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Message.message(getContext(), " un Successful");
                    }
                }
                else {
                    int id = Integer.parseInt(String.valueOf(getContext().getContentResolver().insert(VivzProvider.CONTENT_URI, contentValues)));
                    if (id < 0)
                        Message.message(getContext(), " un Successful");
                    else {
                        Message.message(getContext(), " Marked as Favourites");
                        button.setText("Remove from Favourites");
                        checker=true;
                    }
                }
            }
        });



        return root;
    }
}
