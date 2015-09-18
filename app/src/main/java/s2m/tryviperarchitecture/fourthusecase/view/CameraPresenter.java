package s2m.tryviperarchitecture.fourthusecase.view;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.ViewGroup;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by cta on 18/09/15.
 */
public class CameraPresenter implements ViewEventListener
{
    private static final String TAG = CameraPresenter.class.getSimpleName();

    private UpdateViewInterface output;

    private ViewGroup previewLayout;

    private CameraPreview cameraPreview;

    private Camera mCamera;

    @Override
    public void takeSnapshotClicked()
    {
        Camera.PictureCallback mPicture = new Camera.PictureCallback()
        {
            @Override
            public void onPictureTaken(byte[] data, Camera camera)
            {

                String fileName = "Photo" + System.currentTimeMillis() + ".jpg";
                File pictureFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), fileName);

                try
                {
                    FileOutputStream fos = new FileOutputStream(pictureFile);
                    fos.write(data);
                    fos.close();

                    // We saved the file, start new preview
                    camera.startPreview();

                } catch (FileNotFoundException e)
                {
                    Log.d(TAG, "File not found: " + e.getMessage());
                } catch (IOException e)
                {
                    Log.d(TAG, "Error accessing file: " + e.getMessage());
                }
            }
        };

        mCamera.takePicture(null, null, mPicture);
    }

    /**
     * Check if this device has a camera
     */
    private boolean checkCameraHardware(Context context)
    {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA))
        {
            // this device has a camera
            return true;
        } else
        {
            // no camera on this device
            return false;
        }
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
        // Create an instance of Camera
        mCamera = getCameraInstance();

        // Create our Preview view and set it as the content of our activity.
        cameraPreview = new CameraPreview(activity, mCamera);

        previewLayout.addView(cameraPreview);
    }

    @Override
    public void viewGone()
    {
        mCamera.release();
        mCamera = null;
        cameraPreview = null;
    }

    /**
     * A safe way to get an instance of the Camera object.
     */
    public static Camera getCameraInstance()
    {
        Camera camera = null;
        try
        {
            camera = Camera.open(0);
        }
        catch (Exception e)
        {
            Log.e(TAG, "failed to open Camera");
        }
        return camera;
    }
}
