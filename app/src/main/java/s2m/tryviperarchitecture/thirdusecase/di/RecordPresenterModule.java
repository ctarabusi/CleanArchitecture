package s2m.tryviperarchitecture.thirdusecase.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import s2m.tryviperarchitecture.thirdusecase.view.RecordPresenter;

@Module
public class RecordPresenterModule
{
    @Provides
    @Singleton
    RecordPresenter provideRecordPresenter()
    {
        return new RecordPresenter();
    }
}