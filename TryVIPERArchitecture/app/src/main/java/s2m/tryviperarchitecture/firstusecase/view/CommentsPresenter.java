package s2m.tryviperarchitecture.firstusecase.view;

import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;

import s2m.tryviperarchitecture.firstusecase.CommentsInteractor;

/**
 * Created by cta on 14/09/15.
 */
public class CommentsPresenter implements DataSourceListener, CommentsViewEventListener
{
    private CommentsViewInterface output;

    private CommentsInteractor interactor;

    @Inject
    public CommentsPresenter(CommentsInteractor interactor)
    {
        this.interactor = interactor;
    }

    public void setOutput(@NonNull final CommentsViewInterface output)
    {
        this.output = output;
    }

    public void viewVisible()
    {
        interactor.openConnection();
        interactor.setDataSourceListener(this);
    }

    public void viewGone()
    {
        interactor.closeConnection();
        interactor.setDataSourceListener(null);
    }

    public void dataChanged(final List<Comment> comments)
    {
        output.setComments(comments);
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
        output.showDeletedCommentSnackbar("Deleted comment with id: " + commentToDelete.getCommentId());
    }

}
