package s2m.tryviperarchitecture.fourthusecase.di;

import javax.inject.Singleton;

import dagger.Component;
import s2m.tryviperarchitecture.fourthusecase.view.CameraPresenter;

/**
 * Created by cta on 15/09/15.
 */
@Singleton
@Component(modules = {CameraPresenterModule.class})
public interface CameraPresenterComponent
{

    CameraPresenter provideCameraPresenter();
}