package s2m.tryviperarchitecture;

import android.support.annotation.AnyRes;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import s2m.tryviperarchitecture.firstusecase.view.CommentsFragment;
import s2m.tryviperarchitecture.secondusecase.view.SecondUserCaseFragment;

/**
 * Created by cta on 17/09/15.
 */
public class Router
{
    public enum Navigations
    {
        COMMENTS, SECOND_USE_CASE
    }

    private static Router instance = null;

    public static Router getInstance()
    {
        if (instance == null)
        {
            instance = new Router();
        }
        return instance;
    }

    public void navigateFromDrawer(@NonNull MainActivity activity, Router.Navigations navigation)
    {
        TitleFragment fragment;
        if (navigation == Navigations.COMMENTS)
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
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.content_frame, fragment);
        ft.commit();

        activity.setTitle(fragment.getTitle());
        activity.closeDrawer();
    }
}
