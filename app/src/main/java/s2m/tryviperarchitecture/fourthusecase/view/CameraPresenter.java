package s2m.tryviperarchitecture.fourthusecase.view;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.support.annotation.NonNull;
import android.util.Log;

/**
 * Created by cta on 18/09/15.
 */
public class CameraPresenter implements ViewEventListener
{
    private static final String TAG = CameraPresenter.class.getSimpleName();

    private UpdateViewInterface output;

    @Override
    public void takeSnapshotClicked()
    {


    }

    /** Check if this device has a camera */
    private boolean checkCameraHardware(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            // this device has a camera
            return true;
        } else {
            // no camera on this device
            return false;
        }
    }


    @Override
    public void setOutput(@NonNull UpdateViewInterface output)
    {
        this.output = output;
    }
}
