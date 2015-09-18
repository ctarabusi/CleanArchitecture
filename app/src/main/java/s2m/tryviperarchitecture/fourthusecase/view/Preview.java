package s2m.tryviperarchitecture.fourthusecase.view;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup;

/**
 * Created by cta on 18/09/15.
 */
public class Preview extends ViewGroup implements SurfaceHolder.Callback
{

    SurfaceView mSurfaceView;
    SurfaceHolder mHolder;

    public Preview(Context context)
    {
        super(context);

        mSurfaceView = new SurfaceView(context);
        addView(mSurfaceView);

        // Install a SurfaceHolder.Callback so we get notified when the
        // underlying surface is created and destroyed.
        mHolder = mSurfaceView.getHolder();
        mHolder.addCallback(this);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b)
    {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder)
    {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
    {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder)
    {

    }
}