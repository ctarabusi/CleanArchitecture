package s2m.tryviperarchitecture.firstusecase.view;

import android.support.annotation.NonNull;

import s2m.tryviperarchitecture.firstusecase.interactor.Comment;

/**
 * Created by cta on 15/09/15.
 */
public interface ViewEventListener
{
    void setOutput(@NonNull final UpdateViewInterface output);

    void viewVisible();

    void viewGone();

    void createRequested();

    void deleteRequested(Comment commentToDelete);
}
