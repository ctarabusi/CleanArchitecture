package s2m.tryviperarchitecture.firstusecase.entity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by cta on 15/09/15.
 */
public class CommentsSQLLiteHelper extends SQLiteOpenHelper
{
    private static CommentsSQLLiteHelper sInstance;

    public static final String TABLE_COMMENTS = "comments";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_COMMENT = "comment";

    private static final String DATABASE_NAME = "commments.db";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE = "create table " + TABLE_COMMENTS + "(" + COLUMN_ID + " integer primary key autoincrement, " + COLUMN_COMMENT + " text not null);";

    public CommentsSQLLiteHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized CommentsSQLLiteHelper getInstance(Context context)
    {
        if (sInstance == null)
        {
            sInstance = new CommentsSQLLiteHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase database)
    {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        Log.w(CommentsSQLLiteHelper.class.getName(), "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMMENTS);
        onCreate(db);
    }

}
