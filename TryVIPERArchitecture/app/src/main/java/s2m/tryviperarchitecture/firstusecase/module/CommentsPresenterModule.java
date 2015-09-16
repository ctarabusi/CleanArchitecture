package s2m.tryviperarchitecture.firstusecase.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import s2m.tryviperarchitecture.firstusecase.CommentsInteractor;
import s2m.tryviperarchitecture.firstusecase.view.CommentsPresenter;

/**
 * Created by cta on 15/09/15.
 */
@Module
public class CommentsPresenterModule
{

    @Provides
    @Singleton
    CommentsInteractor provideCommentsInteractor()
    {
        return new CommentsInteractor();
    }

    @Provides
    @Singleton
    CommentsPresenter provideCommentsPresenter()
    {
        return new CommentsPresenter(provideCommentsInteractor());
    }
}
