package s2m.tryviperarchitecture.secondusecase.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import s2m.tryviperarchitecture.R;

/**
 * Created by cta on 17/09/15.
 */
public class ScreenSlidePageFragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_slide, container, false);
    }
}
