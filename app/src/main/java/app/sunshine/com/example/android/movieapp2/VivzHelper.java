package app.sunshine.com.example.android.movieapp2;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ahmed Hassan on 3/19/2017.
 */

public class VivzHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "vivzdatabase";
    public static final String TABLE_NAME = "VIVZTABLE";
    public static final String UID = "id";
    public static final String POSTER = "Poster";
    public static final String MOVIE_ID = "MOVIE_ID";
    public static final String TITLE = "TITLE";
    public static final String VOTE_AVERAGE = "VOTE_AVERAGE";
    public static final String VOTE_COUNT = "VOTE_COUNT";
    public static final String POPULARITY = "POPULARITY";
    public static final String RELEASE_DATE = "RELEASE_DATE";
    public static final String OVERVIEW = "OVERVIEW";
    public static final String BACK_DROPPATH = "BACK_DROPPATH";
    public static final String ORIGINAL_LANGUAGE = "ORIGINAL_LANGUAGE";


    private static final int DATABASE_VERSION = 4;


    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + POSTER + " VARCHAR(255), " + MOVIE_ID + " VARCHAR(255), " + OVERVIEW + " VARCHAR(255), " + ORIGINAL_LANGUAGE +
            " VARCHAR(255), " + TITLE + " VARCHAR(255), " + BACK_DROPPATH + " VARCHAR(255), " + POPULARITY +
            " VARCHAR(255), " + VOTE_COUNT + " VARCHAR(255), " + VOTE_AVERAGE + " VARCHAR(255), " + RELEASE_DATE + " VARCHAR(255) );";

    private Context context;

    public VivzHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
//            Message.message(context, "this is constructor");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_TABLE);
            Message.message(context,"ana ahoooooooo");
        } catch (SQLException e) {
            Message.message(context, "" + e);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        try {
            // onCreate(db);
        } catch (SQLException e) {
            //  Message.message(context,""+e);
        }

    }
}

