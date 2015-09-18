package s2m.tryviperarchitecture;

import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;

/**
 * Created by cta on 17/09/15.
 */
public abstract class FragmentWithTitle extends Fragment
{
    @StringRes
    public abstract int getTitle();
}
