package s2m.tryviperarchitecture.fourthusecase.view;

import android.support.annotation.StringRes;

/**
 * Created by cta on 15/09/15.
 */
public interface UpdateViewInterface
{
     void showCameraSnackbar(@StringRes int snackbarTextId);

     void showNoCameraMessage();
}
