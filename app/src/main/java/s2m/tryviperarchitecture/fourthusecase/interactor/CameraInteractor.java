package s2m.tryviperarchitecture.fourthusecase.interactor;

import android.app.Activity;
import android.hardware.Camera;
import android.os.Environment;
import android.support.annotation.UiThread;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import s2m.tryviperarchitecture.fourthusecase.view.CameraPresenter;
import s2m.tryviperarchitecture.fourthusecase.view.CameraPreview;

/**
 * Created by cta on 18/09/15.
 */
public class CameraInteractor
{
    private static final String TAG = CameraInteractor.class.getSimpleName();

    private Camera mCamera;

    private CameraPresenter output;

    public void initCamera()
    {
        mCamera = getCameraInstance();
    }

    public void setOutput(CameraPresenter output)
    {
        this.output = output;
    }

    public void takePicture()
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

                    // We saved the file, start new preview and show snackbar
                    camera.startPreview();
                    output.storingPictureDone();
                }
                catch (FileNotFoundException e)
                {
                    Log.d(TAG, "File not found: " + e.getMessage());
                }
                catch (IOException e)
                {
                    Log.d(TAG, "Error accessing file: " + e.getMessage());
                }
            }
        };

        mCamera.takePicture(null, null, mPicture);
    }

    public CameraPreview getCameraPreview(Activity activity)
    {
        return new CameraPreview(activity, mCamera);
    }

    public void releaseCamera()
    {
        if (mCamera != null)
        {
            mCamera.release();
            mCamera = null;
        }
    }

    @UiThread
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
