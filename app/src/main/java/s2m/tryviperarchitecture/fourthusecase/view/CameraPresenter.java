package s2m.tryviperarchitecture.fourthusecase.view;

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

    private Camera camera;

    @Override
    public void takeSnapshotClicked()
    {

        boolean qOpened = false;

        try
        {
            releaseCameraAndPreview();
            camera = Camera.open(3);
            qOpened = (camera != null);
        } catch (Exception e)
        {
            Log.e(TAG, "failed to open Camera");
            e.printStackTrace();
        }

    }

    private void releaseCameraAndPreview()
    {
       // mPreview.setCamera(null);
        if (camera != null)
        {
            camera.release();
            camera = null;
        }
    }


    @Override
    public void setOutput(@NonNull UpdateViewInterface output)
    {
        this.output = output;
    }
}
