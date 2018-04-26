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
 * Created by Ahmed Hassan on 11/16/2016.
 */

public class ReviewsAsyncTask extends AsyncTask<String, Void ,ArrayList<Reviews>> {
    ReviewAdapter adapter ;

  public   ReviewsAsyncTask (ReviewAdapter adapter)
  {
      this.adapter=adapter;
  }



    @Override
    protected ArrayList<Reviews> doInBackground(String... params)  {

        ArrayList<Reviews> list = new ArrayList<>();

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
                Reviews objReviews =new Reviews();
                JSONObject jsonObjIndex = arrayjson.getJSONObject(i);
                objReviews.id=jsonObjIndex.getString("id");
                objReviews.author=jsonObjIndex.getString("author");
                objReviews.content=jsonObjIndex.getString("content");

                list.add(objReviews);
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
    protected void onPostExecute(ArrayList<Reviews> Reviews) {
        if(Reviews!=null)
            adapter.Getview(Reviews);
    }
    }

