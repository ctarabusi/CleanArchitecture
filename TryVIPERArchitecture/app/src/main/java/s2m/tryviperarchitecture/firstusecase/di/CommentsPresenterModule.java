package s2m.tryviperarchitecture.firstusecase.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import s2m.tryviperarchitecture.firstusecase.interactor.CommentsInteractor;
import s2m.tryviperarchitecture.firstusecase.entity.CommentsDataStore;
import s2m.tryviperarchitecture.firstusecase.view.CommentsPresenter;

/**
 * Created by cta on 15/09/15.
 */
@Module
public class CommentsPresenterModule
{

    @Provides
    @Singleton
    CommentsDataStore provideCommentsDataStore()
    {
        return new CommentsDataStore();
    }

    @Provides
    @Singleton
    CommentsInteractor provideCommentsInteractor(CommentsDataStore commentsDataStore)
    {
        return new CommentsInteractor(commentsDataStore);
    }

    @Provides
    @Singleton
    CommentsPresenter provideCommentsPresenter(CommentsInteractor interactor)
    {
        return new CommentsPresenter(interactor);
    }
}
