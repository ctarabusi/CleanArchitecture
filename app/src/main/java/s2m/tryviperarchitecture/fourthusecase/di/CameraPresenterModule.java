package s2m.tryviperarchitecture.fourthusecase.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import s2m.tryviperarchitecture.fourthusecase.view.CameraPresenter;

/**
 * Created by cta on 15/09/15.
 */
@Module
public class CameraPresenterModule
{

    @Provides
    @Singleton
    CameraPresenter provideCommentsPresenter()
    {
        return new CameraPresenter();
    }
}
