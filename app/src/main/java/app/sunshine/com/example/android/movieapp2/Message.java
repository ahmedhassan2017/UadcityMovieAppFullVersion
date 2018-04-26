package app.sunshine.com.example.android.movieapp2;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Ahmed Hassan on 11/27/2016.
 */

public class Message {
    public static void message (Context context ,String message)
    {

        Toast.makeText(context , message ,Toast.LENGTH_LONG).show();
    }
}
