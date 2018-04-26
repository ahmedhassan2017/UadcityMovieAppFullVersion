package app.sunshine.com.example.android.movieapp2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity implements NameListener {

    boolean twoPane = false;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        FrameLayout frameLayout2 = (FrameLayout) findViewById(R.id.frame2);
        if (frameLayout2 == null)
            twoPane = false;
        else
            twoPane = true;

        MainActivityFragment mainActivityFragment = new MainActivityFragment();
        mainActivityFragment.setNameListener(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame1 , mainActivityFragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public void setSelectedItem(Movie movieposition) {

        if (twoPane){

            DetailsActivityFragment detailsActivityFragment = new DetailsActivityFragment();

            Bundle bundle =new Bundle();
            bundle.putString("poster_path", movieposition.poster_path);
            bundle.putString("id", movieposition.id);
            bundle.putString("release_date", movieposition.release_date);
            bundle.putString("overview", movieposition.overview);
            bundle.putString("release_date", movieposition.release_date);
            bundle.putString("original_language", movieposition.original_language);
            bundle.putString("title", movieposition.title);
            bundle.putString("backdrop_path", movieposition.backdrop_path);
            bundle.putString("popularity", movieposition.popularity);
            bundle.putString("vote_count", movieposition.vote_count);
            bundle.putString("vote_average", movieposition.vote_average);
            detailsActivityFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.frame2 , detailsActivityFragment).commit();
        }
        else{
            Intent intent = new Intent(this, DetailsActivity.class);

            intent.putExtra("poster_path", movieposition.poster_path);
            intent.putExtra("id", movieposition.id);
            intent.putExtra("release_date", movieposition.release_date);
            intent.putExtra("overview", movieposition.overview);
            intent.putExtra("release_date", movieposition.release_date);
            intent.putExtra("original_language", movieposition.original_language);
            intent.putExtra("title", movieposition.title);
            intent.putExtra("backdrop_path", movieposition.backdrop_path);
            intent.putExtra("popularity", movieposition.popularity);
            intent.putExtra("vote_count", movieposition.vote_count);
            intent.putExtra("vote_average", movieposition.vote_average);

            startActivity(intent);
        }
    }
}
