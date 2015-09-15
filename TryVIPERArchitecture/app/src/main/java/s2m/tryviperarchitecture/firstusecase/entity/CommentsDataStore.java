package s2m.tryviperarchitecture.firstusecase.entity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import s2m.tryviperarchitecture.ViperApplication;

/**
 * Created by cta on 15/09/15.
 */
public class CommentsDataStore
{

    // Database fields
    private SQLiteDatabase database;
    private CommentsSQLLiteHelper dbHelper;
    private String[] allColumns = {CommentsSQLLiteHelper.COLUMN_ID, CommentsSQLLiteHelper.COLUMN_COMMENT};

    public CommentsDataStore()
    {
        dbHelper = CommentsSQLLiteHelper.getInstance(ViperApplication.getContext());
    }

    public void open() throws SQLException
    {
        database = dbHelper.getWritableDatabase();
    }

    public void close()
    {
        dbHelper.close();
    }

    public CommentEntity createComment(String comment)
    {
        ContentValues values = new ContentValues();
        values.put(CommentsSQLLiteHelper.COLUMN_COMMENT, comment);
        long insertId = database.insert(CommentsSQLLiteHelper.TABLE_COMMENTS, null, values);
        Cursor cursor = database.query(CommentsSQLLiteHelper.TABLE_COMMENTS, allColumns, CommentsSQLLiteHelper.COLUMN_ID + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();
        CommentEntity newComment = cursorToComment(cursor);
        cursor.close();
        return newComment;
    }

    public void deleteComment(CommentEntity comment)
    {
        long id = comment.getId();
        System.out.println("Comment deleted with id: " + id);
        database.delete(CommentsSQLLiteHelper.TABLE_COMMENTS, CommentsSQLLiteHelper.COLUMN_ID + " = " + id, null);
    }

    public List<CommentEntity> getAllComments()
    {
        List<CommentEntity> comments = new ArrayList<>();

        Cursor cursor = database.query(CommentsSQLLiteHelper.TABLE_COMMENTS, allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            CommentEntity comment = cursorToComment(cursor);
            comments.add(comment);
            cursor.moveToNext();
        }

        cursor.close();
        return comments;
    }

    private CommentEntity cursorToComment(Cursor cursor)
    {
        CommentEntity comment = new CommentEntity();
        comment.setId(cursor.getLong(0));
        comment.setComment(cursor.getString(1));
        return comment;
    }
}

