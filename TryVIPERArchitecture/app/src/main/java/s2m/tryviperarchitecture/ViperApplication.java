package s2m.tryviperarchitecture;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by cta on 14/09/15.
 */
public class ViperApplication extends Application
{
    @Override public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
    }
}
