package s2m.tryviperarchitecture.fourthusecase.view;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.view.ViewGroup;

import javax.inject.Inject;

import s2m.tryviperarchitecture.R;
import s2m.tryviperarchitecture.fourthusecase.interactor.CameraInteractor;

/**
 * Created by cta on 18/09/15.
 */
public class CameraPresenter implements ViewEventListener
{
    private UpdateViewInterface output;

    private ViewGroup previewLayout;

    private CameraPreview cameraPreview;

    private CameraInteractor interactor;

    @Inject
    public CameraPresenter(CameraInteractor interactor)
    {
        this.interactor = interactor;
        this.interactor.setOutput(this);
    }

    @Override
    public void takeSnapshotClicked()
    {
        interactor.takePicture();
    }

    private boolean checkCameraHardware(Context context)
    {
        return context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }

    @Override
    public void setOutput(@NonNull UpdateViewInterface output)
    {
        this.output = output;
    }

    @Override
    public void setPreviewLayout(@NonNull ViewGroup previewLayout)
    {
        this.previewLayout = previewLayout;
    }

    @Override
    public void viewVisible(Activity activity)
    {
        if (!checkCameraHardware(activity))
        {
            output.showNoCameraMessage();
            return;
        }

        interactor.initCamera();
        cameraPreview = interactor.getCameraPreview(activity);
        previewLayout.addView(cameraPreview);
    }

    @Override
    public void viewGone()
    {
        interactor.releaseCamera();
        cameraPreview.getHolder().removeCallback(cameraPreview);
    }

    public void storingPictureDone()
    {
        output.showCameraSnackbar(R.string.snapshot_saved);
    }
}
