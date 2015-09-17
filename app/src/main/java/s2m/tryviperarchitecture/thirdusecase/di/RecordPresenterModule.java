package s2m.tryviperarchitecture.thirdusecase.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import s2m.tryviperarchitecture.thirdusecase.interactor.RecordInteractor;
import s2m.tryviperarchitecture.thirdusecase.view.RecordPresenter;

@Module
public class RecordPresenterModule
{
    @Provides
    @Singleton
    RecordInteractor provideRecordInteractor()
    {
        return new RecordInteractor();
    }

    @Provides
    @Singleton
    RecordPresenter provideRecordPresenter(RecordInteractor interactor)
    {
        return new RecordPresenter(interactor);
    }
}