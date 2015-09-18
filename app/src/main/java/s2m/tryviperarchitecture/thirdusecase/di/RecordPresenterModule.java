package s2m.tryviperarchitecture.thirdusecase.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import s2m.tryviperarchitecture.ViperApplication;
import s2m.tryviperarchitecture.thirdusecase.interactor.RecordInteractor;
import s2m.tryviperarchitecture.thirdusecase.view.RecordPresenter;

@Module
public class RecordPresenterModule
{
    private final Context context;

    public RecordPresenterModule(Context context)
    {
        this.context = context;
    }

    @Provides
    @Singleton
    Context provideApplicationContext()
    {
        return context;
    }

    @Provides
    @Singleton
    RecordInteractor provideRecordInteractor(Context applicationContext)
    {
        return new RecordInteractor(applicationContext);
    }

    @Provides
    @Singleton
    RecordPresenter provideRecordPresenter(RecordInteractor interactor)
    {
        return new RecordPresenter(interactor);
    }
}