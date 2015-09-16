package s2m.tryviperarchitecture.firstusecase.view;

import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;

import s2m.tryviperarchitecture.firstusecase.interactor.CommentsInteractor;
import s2m.tryviperarchitecture.firstusecase.interactor.DataSourceListener;

/**
 * Created by cta on 14/09/15.
 */
public class CommentsPresenter implements DataSourceListener, ViewEventListener
{
    private UpdateViewInterface output;

    private CommentsInteractor interactor;

    @Inject
    public CommentsPresenter(CommentsInteractor interactor)
    {
        this.interactor = interactor;
    }

    public void setOutput(@NonNull final UpdateViewInterface output)
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
