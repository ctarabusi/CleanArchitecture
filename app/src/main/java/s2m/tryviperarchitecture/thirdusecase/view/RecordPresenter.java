package s2m.tryviperarchitecture.thirdusecase.view;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import s2m.tryviperarchitecture.R;
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
    public void viewVisible()
    {
        interactor.initRecorder();
    }

    @Override
    public void viewGone()
    {
        interactor.releaseRecorder();
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

            output.startChronometer();
            output.showRecordingSnackbar(R.string.record_start);
        }
    }

    @Override
    public void stopRecordingButtonClicked()
    {
        if (isRecording)
        {
            interactor.stopRecording();

            isRecording = false;
            output.stopChronometer();
            output.showRecordingSnackbar(R.string.record_stop);
        }
    }

    @Override
    public void exceptionFromInteractor()
    {
        output.showRecordingSnackbar(R.string.record_error);
    }
}
