package app.sunshine.com.example.android.movieapp2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class Detailes_favourit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailes_favourit);
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
        Bundle bundle = getIntent().getExtras();

        if (savedInstanceState == null) {
            Detailes_favouritFragment detailes_favouritFragment = new Detailes_favouritFragment();
            detailes_favouritFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.frame1, detailes_favouritFragment).commit();
        }
    }
}
