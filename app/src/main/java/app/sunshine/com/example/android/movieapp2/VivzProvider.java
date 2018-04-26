package app.sunshine.com.example.android.movieapp2;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

import java.util.ArrayList;

/**
 * Created by Ahmed Hassan on 3/19/2017.
 */

public class VivzProvider extends ContentProvider {

    static VivzHelper database;
    static final String AUTHORITY = "app.sunshine.com.example.android.movieapp2";
    static final String PATH = VivzHelper.TABLE_NAME;
    static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);
    static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH).build();
    static final int FAVOURITES = 100;
    static final int FAVOURITES_WITH_ID = 101;
    static final UriMatcher matcher = buildurimatcher();

    public static UriMatcher buildurimatcher() {
        UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI(AUTHORITY, PATH, FAVOURITES);
        matcher.addURI(AUTHORITY, PATH + "/#", FAVOURITES_WITH_ID);
        return matcher;
    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        database = new VivzHelper(context);
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        final SQLiteDatabase db = database.getReadableDatabase();
        int match = matcher.match(uri);
        Cursor cursor = null;

        switch (match) {
            case FAVOURITES:
                cursor = db.query(VivzHelper.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;

        }

        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }


    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        SQLiteDatabase db = database.getWritableDatabase();
        int match = matcher.match(uri);
        Uri uri1 = Uri.parse("-1");

        switch (match) {
            case FAVOURITES:
                Long id = db.insert(VivzHelper.TABLE_NAME, null, contentValues);
                if (id > 0) {
                    uri1 = Uri.parse(Long.toString(id));
                }

        }
        getContext().getContentResolver().notifyChange(uri, null);
        return uri1;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {


        final SQLiteDatabase db = database.getReadableDatabase();
        int match = matcher.match(uri);
        int cursor = -1;

        switch (match) {
            case FAVOURITES_WITH_ID:
                String movie_id = uri.getPathSegments().get(1);
                cursor = db.delete(VivzHelper.TABLE_NAME, "id=?", new String[]{movie_id});
                break;

        }

       getContext().getContentResolver().notifyChange(uri,null);
        return  cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }


    public static ArrayList<Movie>

    getAllData(Context context, Uri uri) {
        SQLiteDatabase db = database.getWritableDatabase();

        Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
        ArrayList<Movie> list = new ArrayList<Movie>();
        while (cursor.moveToNext()) {
            Movie movie = new Movie();
            movie.UID = cursor.getString(0);
            movie.poster_path = cursor.getString(1);
            movie.id = cursor.getString(2);
            movie.overview = cursor.getString(3);
            movie.original_language = cursor.getString(4);
            movie.title = cursor.getString(5);
            movie.backdrop_path = cursor.getString(6);
            movie.popularity = cursor.getString(7);
            movie.vote_count = cursor.getString(8);
            movie.vote_average = cursor.getString(9);
            movie.release_date = cursor.getString(10);

            list.add(movie);
        }
        return list;
    }


    public static boolean find_movie(Context context, String id, Uri uri) {
        ArrayList<Movie> data = getAllData(context, uri);

        for (int i = 0; i < data.size(); i++) {
            if (id.equals(data.get(i).id))
                return true;


        }
        return false;
    }

    public static String getUID(Context context, String movie_id, Uri uri) {
        String UID = null;
        ArrayList<Movie> list = getAllData(context, uri);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).id.equals(movie_id)) {
                UID = list.get(i).UID;
            }

        }
        return UID;
    }
}
