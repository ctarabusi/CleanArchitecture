package s2m.tryviperarchitecture;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import s2m.tryviperarchitecture.firstusecase.view.CommentsFragment;
import s2m.tryviperarchitecture.secondusecase.view.TabLayoutFragment;
import s2m.tryviperarchitecture.thirdusecase.view.RecordFragment;

/**
 * Created by cta on 17/09/15.
 */
public class Router
{
    public enum NavigationPaths
    {
        COMMENTS, TAB_LAYOUT, RECORD
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

    public void navigateFromDrawer(@NonNull MainActivity activity, NavigationPaths navigation)
    {
        TitleFragment fragment;
        switch (navigation)
        {
            case COMMENTS:
                fragment = new CommentsFragment();
                break;

            case TAB_LAYOUT:
                fragment = new TabLayoutFragment();
                break;

            default:
                fragment = new RecordFragment();
                break;

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
