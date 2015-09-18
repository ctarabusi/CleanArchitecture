package s2m.tryviperarchitecture.fourthusecase.view;

import android.support.annotation.NonNull;

/**
 * Created by cta on 18/09/15.
 */
public interface ViewEventListener
{
    void takeSnapshotClicked();

    void setOutput(@NonNull final UpdateViewInterface output);

}
