package s2m.tryviperarchitecture.firstusecase;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import s2m.tryviperarchitecture.firstusecase.entity.CommentEntity;
import s2m.tryviperarchitecture.firstusecase.entity.CommentsDataStore;
import s2m.tryviperarchitecture.firstusecase.view.DataSourceListener;
import s2m.tryviperarchitecture.firstusecase.view.Comment;

/**
 * Created by cta on 14/09/15.
 */
public class CommentsInteractor
{
    private static final String TAG = CommentsInteractor.class.getSimpleName();

    private DataSourceListener dataSourceListener;
    private CommentsDataStore commentsDataStore;

    public CommentsInteractor()
    {
        commentsDataStore = new CommentsDataStore();
    }

    public void openConnection()
    {
        commentsDataStore.open();
        dataChanged();
    }

    public void closeConnection()
    {
        commentsDataStore.close();
    }

    public void createDBEntry()
    {
        CommentEntity commentEntity = commentsDataStore.createComment("Created at" + System.currentTimeMillis());
        Log.i(TAG, " createDBEntry " + commentEntity.getId());
        dataChanged();
    }

    public void dataChanged()
    {
        if (dataSourceListener != null)
        {
            // Convert from Entities POJOS to Presenter POJOS
            List<Comment> commentList = new ArrayList<>();
            for (CommentEntity commentEntity : commentsDataStore.getAllComments())
            {
                commentList.add(new Comment(commentEntity.getId(), commentEntity.getComment()));
            }
            dataSourceListener.dataChanged(commentList);
        }
    }

    public void deleteItem(Comment commentToDelete)
    {
        CommentEntity commentEntityToDelete = new CommentEntity();
        commentEntityToDelete.setId(commentToDelete.getCommentId());
        commentsDataStore.deleteComment(commentEntityToDelete);
        dataChanged();
    }

    public void setDataSourceListener(DataSourceListener dataSourceListener)
    {
        this.dataSourceListener = dataSourceListener;
        dataChanged();
    }
}
