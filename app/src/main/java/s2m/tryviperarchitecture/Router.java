package s2m.tryviperarchitecture;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.annotation.AnyRes;
import android.support.annotation.NonNull;

import s2m.tryviperarchitecture.firstusecase.view.CommentsFragment;
import s2m.tryviperarchitecture.secondusecase.view.SecondUserCaseFragment;

/**
 * Created by cta on 17/09/15.
 */
public class Router
{
    private static Router instance = null;

    public static Router getInstance()
    {
        if (instance == null)
        {
            instance = new Router();
        }
        return instance;
    }

    public void navigateFromDrawer(@NonNull MainActivity activity, @AnyRes int id)
    {
        TitleFragment fragment;
        if (id == R.id.navDrawerComments)
        {
             fragment = new CommentsFragment();
        }
        else
        {
             fragment = new SecondUserCaseFragment();
        }
        replaceFragment(activity, fragment);
    }

    private void replaceFragment(@NonNull MainActivity activity, @NonNull TitleFragment fragment)
    {
        FragmentManager fragmentManager = activity.getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.content_frame, fragment);
        ft.commit();

        activity.setTitle(fragment.getTitle());
        activity.closeDrawer();
    }
}
