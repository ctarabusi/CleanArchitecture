package s2m.tryviperarchitecture.thirdusecase.view;

import android.support.annotation.StringRes;

import java.util.List;

import s2m.tryviperarchitecture.firstusecase.interactor.Comment;

/**
 * Created by cta on 15/09/15.
 */
public interface UpdateViewInterface
{
     void showRecordingSnackbar(@StringRes int snackbarTextId);

     void startChronometer();

     void stopChronometer();
}
