//package app.sunshine.com.example.android.movieapp2;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.SQLException;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//import java.util.ArrayList;
//
///**
// * Created by Ahmed Hassan on 11/27/2016.
// */
//
//public class VivsdatabaseAdapter {
//
//    VivsHelper helper;
//
//    public VivsdatabaseAdapter(Context context) {
//
//        helper = new VivsHelper(context);
//    }
//
//    public long insertdata(String movie_id, String poster, String overview, String language, String title,
//                           String backdrop, String popularity, String votecount, String voteaverage, String releasedate) {
//        SQLiteDatabase db = helper.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(VivsHelper.POSTER, poster);
//        contentValues.put(VivsHelper.MOVIE_ID, movie_id);
//        contentValues.put(VivsHelper.OVERVIEW, overview);
//        contentValues.put(VivsHelper.ORIGINAL_LANGUAGE, language);
//        contentValues.put(VivsHelper.TITLE, title);
//        contentValues.put(VivsHelper.BACK_DROPPATH, backdrop);
//        contentValues.put(VivsHelper.POPULARITY, popularity);
//        contentValues.put(VivsHelper.VOTE_COUNT, votecount);
//        contentValues.put(VivsHelper.VOTE_AVERAGE, voteaverage);
//        contentValues.put(VivsHelper.RELEASE_DATE, releasedate);
//
//        long id = db.insert(VivsHelper.TABLE_NAME, null, contentValues);
//        return id;
//    }
//
//    public ArrayList<Movie> getAllData() {
//        SQLiteDatabase db = helper.getWritableDatabase();
//        String[] Columns = {VivsHelper.UID, VivsHelper.POSTER, VivsHelper.MOVIE_ID , VivsHelper.OVERVIEW, VivsHelper.ORIGINAL_LANGUAGE,
//                VivsHelper.TITLE, VivsHelper.BACK_DROPPATH, VivsHelper.POPULARITY, VivsHelper.VOTE_COUNT,
//                VivsHelper.VOTE_AVERAGE, VivsHelper.RELEASE_DATE};
//
//
//        Cursor cursor = db.query(VivsHelper.TABLE_NAME, Columns, null, null, null, null, null);
//        ArrayList<Movie> list = new ArrayList<Movie>();
//        while (cursor.moveToNext()) {
//            Movie movie = new Movie();
//            movie.poster_path = cursor.getString(1);
//            movie.id = cursor.getString(2);
//            movie.overview = cursor.getString(3);
//            movie.original_language = cursor.getString(4);
//            movie.title = cursor.getString(5);
//            movie.backdrop_path = cursor.getString(6);
//            movie.popularity = cursor.getString(7);
//            movie.vote_count = cursor.getString(8);
//            movie.vote_average = cursor.getString(9);
//            movie.release_date = cursor.getString(10);
//
//            list.add(movie);
//        }
//        return list;
//    }
//
//    public boolean find_movie(String id) {
//        ArrayList<Movie> data = getAllData();
//
//        for (int i = 0; i < data.size(); i++) {
//            if (id.equals(data.get(i).id))
//                return true;
//
//
//        }
//        return false;
//    }
//
//
//
//    static class VivsHelper extends SQLiteOpenHelper {
//
//        private static final String DATABASE_NAME = "vivzdatabase";
//        private static final String TABLE_NAME = "VIVZTABLE";
//        private static final String UID = "id";
//        private static final String POSTER = "Poster";
//        private static final String MOVIE_ID = "MOVIE_ID";
//        private static final String TITLE = "TITLE";
//        private static final String VOTE_AVERAGE = "VOTE_AVERAGE";
//        private static final String VOTE_COUNT = "VOTE_COUNT";
//        private static final String POPULARITY = "POPULARITY";
//        private static final String RELEASE_DATE = "RELEASE_DATE";
//        private static final String OVERVIEW = "OVERVIEW";
//        private static final String BACK_DROPPATH = "BACK_DROPPATH";
//        private static final String ORIGINAL_LANGUAGE = "ORIGINAL_LANGUAGE";
//
//
//        private static final int DATABASE_VERSION = 4;
//
//
//        private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
//                + POSTER + " VARCHAR(255), " + MOVIE_ID + " VARCHAR(255), " + OVERVIEW + " VARCHAR(255), " + ORIGINAL_LANGUAGE +
//                " VARCHAR(255), " + TITLE + " VARCHAR(255), " + BACK_DROPPATH + " VARCHAR(255), " + POPULARITY +
//                " VARCHAR(255), "+ VOTE_COUNT + " VARCHAR(255), " + VOTE_AVERAGE + " VARCHAR(255), "  + RELEASE_DATE + " VARCHAR(255) );";
//
//        private Context context;
//
//        public VivsHelper(Context context) {
//            super(context, DATABASE_NAME, null, DATABASE_VERSION);
//            this.context = context;
////            Message.message(context, "this is constructor");
//        }
//
//        @Override
//        public void onCreate(SQLiteDatabase db) {
//            try {
//                db.execSQL(CREATE_TABLE);
//            } catch (SQLException e) {
//                Message.message(context, "" + e);
//            }
//
//        }
//
//        @Override
//        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//
//            try {
//                // onCreate(db);
//            } catch (SQLException e) {
//                //  Message.message(context,""+e);
//            }
//
//        }
//    }
//
//}
