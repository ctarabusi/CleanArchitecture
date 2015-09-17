package s2m.tryviperarchitecture.thirdusecase.view;

import android.support.annotation.NonNull;

/**
 * Created by cta on 15/09/15.
 */
public interface ViewEventListener
{
    void setOutput(@NonNull final UpdateViewInterface output);

    void startRecordingButtonClicked();
}
