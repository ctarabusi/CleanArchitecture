package s2m.tryviperarchitecture.fourthusecase.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import s2m.tryviperarchitecture.fourthusecase.interactor.CameraInteractor;
import s2m.tryviperarchitecture.fourthusecase.view.CameraPresenter;

/**
 * Created by cta on 15/09/15.
 */
@Module
public class CameraPresenterModule
{
    @Provides
    @Singleton
    CameraInteractor provideCameraInteractor()
    {
        return new CameraInteractor();
    }

    @Provides
    @Singleton
    CameraPresenter provideCommentsPresenter(CameraInteractor interactor)
    {
        return new CameraPresenter(interactor);
    }
}
