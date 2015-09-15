package s2m.tryviperarchitecture.firstusecase.view;

import android.support.annotation.NonNull;

/**
 * Created by cta on 15/09/15.
 */
public interface CommentsViewEventListener
{
    void setOutput(@NonNull final CommentsViewInterface output);

    void viewVisible();

    void viewGone();

    void createRequested();

    void deleteRequested(Comment commentToDelete);
}
