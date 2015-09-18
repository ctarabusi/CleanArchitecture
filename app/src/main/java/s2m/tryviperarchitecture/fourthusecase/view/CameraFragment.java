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

    private Camera mCamera;
    private Camera.PictureCallback mPicture;

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

        // Create an instance of Camera
        mCamera = getCameraInstance();

        // Create our Preview view and set it as the content of our activity.
        CameraPreview mPreview = new CameraPreview(this.getActivity(), mCamera);

        previewLayout.addView(mPreview);

        CameraPresenterComponent component = DaggerCameraPresenterComponent.create();
        eventListener = component.provideCameraPresenter();
        eventListener.setOutput(this);

        mPicture = new Camera.PictureCallback()
        {

            @Override
            public void onPictureTaken(byte[] data, Camera camera)
            {
                File pictureFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "Photo.jpg");

                try
                {
                    FileOutputStream fos = new FileOutputStream(pictureFile);
                    fos.write(data);
                    fos.close();
                } catch (FileNotFoundException e)
                {
                    Log.d(TAG, "File not found: " + e.getMessage());
                } catch (IOException e)
                {
                    Log.d(TAG, "Error accessing file: " + e.getMessage());
                }
            }
        };

        return rootView;
    }

    @OnClick(R.id.take_snapshot)
    public void takeSnapshotClicked()
    {
        mCamera.takePicture(null, null, mPicture);
    }

    @Override
    public void showCameraSnackbar(@StringRes int snackBarContentId)
    {
        String snackBarContent = getResources().getString(snackBarContentId);
        Snackbar.make(takeSnapshotButton, snackBarContent, Snackbar.LENGTH_SHORT).show();
    }


    /**
     * A safe way to get an instance of the Camera object.
     */
    public static Camera getCameraInstance()
    {
        Camera c = null;
        try
        {
            c = Camera.open(0); // attempt to get a Camera instance
        } catch (Exception e)
        {
            Log.e("To Replace", "failed to open Camera");
        }
        return c; // returns null if camera is unavailable
    }
}
