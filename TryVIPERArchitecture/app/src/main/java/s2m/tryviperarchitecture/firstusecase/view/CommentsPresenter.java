package s2m.tryviperarchitecture.firstusecase.view;

import android.support.annotation.NonNull;

import java.util.List;

import s2m.tryviperarchitecture.firstusecase.CommentsInteractor;

/**
 * Created by cta on 14/09/15.
 */
public class CommentsPresenter implements DataSourceListener, CommentsViewEventListener
{
    private CommentsViewInterface commentsViewInterface;

    private CommentsInteractor interactor;

    public CommentsPresenter(@NonNull final CommentsViewInterface commentsViewInterface)
    {
        this.commentsViewInterface = commentsViewInterface;
        interactor = new CommentsInteractor();
    }

    public void onActivityResumed()
    {
        interactor.openConnection();
        interactor.setDataSourceListener(this);
    }

    public void onActivityPaused()
    {
        interactor.closeConnection();
        interactor.setDataSourceListener(null);
    }

    public void dataChanged(final List<Comment> comments)
    {
        commentsViewInterface.setComments(comments);
    }

    @Override
    public void createRequested()
    {
        interactor.createDBEntry();
    }

    @Override
    public void deleteRequested(Comment commentToDelete)
    {
        interactor.deleteItem(commentToDelete);
        commentsViewInterface.showDeletedCommentSnackbar("Deleted comment with id: " + commentToDelete.getCommentId());
    }
}
