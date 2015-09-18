package s2m.tryviperarchitecture.fourthusecase.view;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.ViewGroup;

/**
 * Created by cta on 18/09/15.
 */
public interface ViewEventListener
{

    void takeSnapshotClicked();

    void setOutput(@NonNull final UpdateViewInterface output);

    void setPreviewLayout(@NonNull ViewGroup previewLayout);

    void viewVisible(@NonNull Activity activity);

    void viewGone();
}
