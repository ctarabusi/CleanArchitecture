package s2m.tryviperarchitecture.firstusecase.entity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cta on 15/09/15.
 */
public class FirstEntity
{

    // Database fields
    private SQLiteDatabase database;
    private FirstSQLLiteHelper dbHelper;
    private String[] allColumns = {FirstSQLLiteHelper.COLUMN_ID, FirstSQLLiteHelper.COLUMN_COMMENT};

    public FirstEntity(Context context)
    {
        dbHelper = new FirstSQLLiteHelper(context);
    }

    public void open() throws SQLException
    {
        database = dbHelper.getWritableDatabase();
    }

    public void close()
    {
        dbHelper.close();
    }

    public DBFirstItem createComment(String comment)
    {
        ContentValues values = new ContentValues();
        values.put(FirstSQLLiteHelper.COLUMN_COMMENT, comment);
        long insertId = database.insert(FirstSQLLiteHelper.TABLE_COMMENTS, null, values);
        Cursor cursor = database.query(FirstSQLLiteHelper.TABLE_COMMENTS, allColumns, FirstSQLLiteHelper.COLUMN_ID + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();
        DBFirstItem newComment = cursorToComment(cursor);
        cursor.close();
        return newComment;
    }

    public void deleteComment(DBFirstItem comment)
    {
        long id = comment.getId();
        System.out.println("Comment deleted with id: " + id);
        database.delete(FirstSQLLiteHelper.TABLE_COMMENTS, FirstSQLLiteHelper.COLUMN_ID + " = " + id, null);
    }

    public List<DBFirstItem> getAllComments()
    {
        List<DBFirstItem> comments = new ArrayList<>();

        Cursor cursor = database.query(FirstSQLLiteHelper.TABLE_COMMENTS, allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            DBFirstItem comment = cursorToComment(cursor);
            comments.add(comment);
            cursor.moveToNext();
        }

        cursor.close();
        return comments;
    }

    private DBFirstItem cursorToComment(Cursor cursor)
    {
        DBFirstItem comment = new DBFirstItem();
        comment.setId(cursor.getLong(0));
        comment.setComment(cursor.getString(1));
        return comment;
    }
}

