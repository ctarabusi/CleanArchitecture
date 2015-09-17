package s2m.tryviperarchitecture.thirdusecase.view;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import s2m.tryviperarchitecture.thirdusecase.interactor.DataChangeListener;
import s2m.tryviperarchitecture.thirdusecase.interactor.RecordInteractor;

/**
 * Created by cta on 17/09/15.
 */
public class RecordPresenter implements ViewEventListener, DataChangeListener
{
    private UpdateViewInterface output;

    private RecordInteractor interactor;

    private boolean isRecording;

    @Inject
    public RecordPresenter(RecordInteractor interactor)
    {
        this.interactor = interactor;

        this.interactor.setOutput(this);
    }

    @Override
    public void setOutput(@NonNull UpdateViewInterface output)
    {
        this.output = output;
    }

    @Override
    public void startRecordingButtonClicked()
    {
        if (!isRecording)
        {
            interactor.startRecording();

            isRecording = true;
            output.showRecordingSnackbar("Started Recording...");
        } else
        {
            interactor.stopRecording();

            isRecording = false;
            output.showRecordingSnackbar("Stopped Recording");
        }
    }

    @Override
    public void issueArised(String exceptionMessage)
    {
        output.showRecordingSnackbar("Ups something gone wrong!");
    }
}
