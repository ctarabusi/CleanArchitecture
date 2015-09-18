package s2m.tryviperarchitecture.fourthusecase.view;

import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import s2m.tryviperarchitecture.FragmentWithTitle;
import s2m.tryviperarchitecture.R;
import s2m.tryviperarchitecture.fourthusecase.di.CameraPresenterComponent;
import s2m.tryviperarchitecture.fourthusecase.di.DaggerCameraPresenterComponent;

/**
 * Created by cta on 18/09/15.
 */
public class CameraFragment extends FragmentWithTitle implements UpdateViewInterface
{
    private static final String TAG = CameraFragment.class.getSimpleName();

    private ViewEventListener eventListener;

    @Bind(R.id.take_snapshot)
    ImageView takeSnapshotButton;

    @Bind(R.id.camera_preview)
    FrameLayout previewLayout;

    @Override
    public int getTitle()
    {
        return R.string.navigation_camera;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_camera, container, false);
        ButterKnife.bind(this, rootView);

        CameraPresenterComponent component = DaggerCameraPresenterComponent.create();
        eventListener = component.provideCameraPresenter();
        eventListener.setOutput(this);
        eventListener.setPreviewLayout(previewLayout);

        return rootView;
    }

    @Override
    public void onResume()
    {
        eventListener.viewVisible(this.getActivity());
        super.onResume();
    }

    @Override
    public void onPause()
    {
        eventListener.viewGone();
        super.onPause();
    }

    @OnClick(R.id.take_snapshot)
    public void takeSnapshotClicked()
    {
        eventListener.takeSnapshotClicked();
    }

    @Override
    public void showCameraSnackbar(@StringRes int snackBarContentId)
    {
        String snackBarContent = getResources().getString(snackBarContentId);
        Snackbar.make(takeSnapshotButton, snackBarContent, Snackbar.LENGTH_SHORT).show();
    }

}
