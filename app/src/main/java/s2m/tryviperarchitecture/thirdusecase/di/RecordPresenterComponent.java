package s2m.tryviperarchitecture.thirdusecase.di;

import javax.inject.Singleton;

import dagger.Component;
import s2m.tryviperarchitecture.thirdusecase.view.RecordPresenter;

/**
 * Created by cta on 15/09/15.
 */
@Singleton
@Component(modules = {RecordPresenterModule.class})
public interface RecordPresenterComponent
{
    RecordPresenter provideRecordPresenter();
}