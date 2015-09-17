package s2m.tryviperarchitecture.thirdusecase.view;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import s2m.tryviperarchitecture.R;
import s2m.tryviperarchitecture.TitleFragment;
import s2m.tryviperarchitecture.thirdusecase.di.DaggerRecordPresenterComponent;
import s2m.tryviperarchitecture.thirdusecase.di.RecordPresenterComponent;

/**
 * Created by cta on 17/09/15.
 */
public class RecordFragment extends TitleFragment implements UpdateViewInterface
{
    private ViewEventListener eventListener;

    @Bind(R.id.start_record_button)
    ImageView recordButton;

    @Override
    public String getTitle()
    {
        return "Record Voice";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_record, container, false);
        ButterKnife.bind(this, rootView);

        RecordPresenterComponent component = DaggerRecordPresenterComponent.create();
        eventListener = component.provideRecordPresenter();
        eventListener.setOutput(this);

        return rootView;
    }

    @OnClick(R.id.start_record_button)
    public void startRecordingButtonClicked()
    {
        eventListener.startRecordingButtonClicked();
    }

    @Override
    public void showRecordingSnackbar(String snackBarContent)
    {
        Snackbar.make(recordButton, snackBarContent, Snackbar.LENGTH_SHORT).show();
    }
}
