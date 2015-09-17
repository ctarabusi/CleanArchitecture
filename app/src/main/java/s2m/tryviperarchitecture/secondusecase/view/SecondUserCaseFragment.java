package s2m.tryviperarchitecture.secondusecase.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import s2m.tryviperarchitecture.R;
import s2m.tryviperarchitecture.TitleFragment;

/**
 * Created by cta on 17/09/15.
 */
public class SecondUserCaseFragment extends TitleFragment
{
    @Override
    public String getTitle()
    {
        return "Second Use Case";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_secondusecase, container, false);
        ButterKnife.bind(this, rootView);

        return rootView;
    }
}

