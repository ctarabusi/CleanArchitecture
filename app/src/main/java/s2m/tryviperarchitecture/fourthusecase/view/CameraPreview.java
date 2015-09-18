package s2m.tryviperarchitecture.fourthusecase.view;

import android.app.Activity;
import android.hardware.Camera;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

/**
 * Created by cta on 18/09/15.
 */
public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback
{
    private static final String TAG = CameraPreview.class.getSimpleName();

    private SurfaceHolder mHolder;
    private Camera mCamera;

    private Activity activity;

    public CameraPreview(Activity activity, Camera camera)
    {
        super(activity);
        mCamera = camera;

        this.activity = activity;

        // Install a SurfaceHolder.Callback so we get notified when the
        // underlying surface is created and destroyed.
        mHolder = getHolder();
        mHolder.addCallback(this);
    }

    public void surfaceCreated(SurfaceHolder holder)
    {
        // The Surface has been created, now tell the camera where to draw the preview.
        try
        {
            mCamera.setPreviewDisplay(holder);
            mCamera.startPreview();
        } catch (IOException e)
        {
            Log.d(TAG, "Error setting camera preview: " + e.getMessage());
        }
    }

    public void surfaceDestroyed(SurfaceHolder holder)
    {
        // empty. Take care of releasing the Camera preview in your activity.
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h)
    {
        // If your preview can change or rotate, take care of those events here.
        // Make sure to stop the preview before resizing or reformatting it.

        if (mHolder.getSurface() == null)
        {
            // preview surface does not exist
            return;
        }

        // stop preview before making changes
        try
        {
            mCamera.stopPreview();
        } catch (Exception e)
        {
            // ignore: tried to stop a non-existent preview
        }

        setCameraDisplayOrientation(activity, 0, mCamera);

        // set preview size and make any resize, rotate or
        // reformatting changes here

        // start preview with new settings
        try
        {
            mCamera.setPreviewDisplay(mHolder);
            mCamera.startPreview();

        } catch (Exception e)
        {
            Log.d(TAG, "Error starting camera preview: " + e.getMessage());
        }
    }

    public static void setCameraDisplayOrientation(Activity activity,
                                                   int cameraId, android.hardware.Camera camera) {
        android.hardware.Camera.CameraInfo info =
                new android.hardware.Camera.CameraInfo();
        android.hardware.Camera.getCameraInfo(cameraId, info);
        int rotation = activity.getWindowManager().getDefaultDisplay()
                .getRotation();
        int degrees = 0;
        switch (rotation) {
            case Surface.ROTATION_0: degrees = 0; break;
            case Surface.ROTATION_90: degrees = 90; break;
            case Surface.ROTATION_180: degrees = 180; break;
            case Surface.ROTATION_270: degrees = 270; break;
        }

        int result;
        if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            result = (info.orientation + degrees) % 360;
            result = (360 - result) % 360;  // compensate the mirror
        } else {  // back-facing
            result = (info.orientation - degrees + 360) % 360;
        }
        camera.setDisplayOrientation(result);
    }
}