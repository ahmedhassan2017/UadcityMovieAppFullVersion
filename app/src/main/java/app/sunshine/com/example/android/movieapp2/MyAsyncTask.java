package app.sunshine.com.example.android.movieapp2;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Ahmed Hassan on 11/2/2016.
 */

public class MyAsyncTask extends AsyncTask<String, Void, ArrayList<Movie>> {
    myadapter adapter;


    public MyAsyncTask(myadapter adapter) {
        this.adapter = adapter;

    }

    @Override
    protected ArrayList<Movie> doInBackground(String... params) {

        ArrayList<Movie> list = new ArrayList<>();

        URL url = null;
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String jsonstring;

        // Create the request to OpenWeatherMap, and open the connection
        try {
            url = new URL(params[0]);
            urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a Str      ing
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                return null;
            }
            jsonstring = buffer.toString();
            Log.e("doInBackground: ", jsonstring); // logging

            JSONObject jsondata = new JSONObject(jsonstring);  // keda ana 3andy kol el data .. 3ayez aasmha ba2a
            JSONArray arrayjson = jsondata.getJSONArray("results");  // 3mlt jsonarray ha2asam feh el data

            for (int i = 0; i < arrayjson.length(); i++) {
                Movie objmovie =new Movie();
                JSONObject jsonObjIndex= arrayjson.getJSONObject(i);
                objmovie.poster_path=jsonObjIndex.getString("poster_path");
                objmovie.id=jsonObjIndex.getString("id");
                objmovie.overview=jsonObjIndex.getString("overview");
                objmovie.release_date=jsonObjIndex.getString("release_date");
                objmovie.original_language=jsonObjIndex.getString("original_language");
                objmovie.title=jsonObjIndex.getString("title");
                objmovie.backdrop_path=jsonObjIndex.getString("backdrop_path");
                objmovie.popularity=jsonObjIndex.getString("popularity");
                objmovie.vote_count=jsonObjIndex.getString("vote_count");
                objmovie.vote_average=jsonObjIndex.getString("vote_average");

                list.add(objmovie);
            }



        } catch (Throwable e1) {
            e1.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e("Error closing stream", e.toString());
                }
            }


            return list;
        }

    }

    @Override
    protected void onPostExecute(ArrayList<Movie> movies) {
       if(movies!=null)
        adapter.Getview(movies);
    }
}
