package s2m.tryviperarchitecture.firstusecase.di;

import javax.inject.Singleton;

import dagger.Component;
import s2m.tryviperarchitecture.firstusecase.view.CommentsPresenter;

/**
 * Created by cta on 15/09/15.
 */
@Singleton
@Component(modules = {CommentsPresenterModule.class})
public interface CommentsPresenterComponent {

    CommentsPresenter provideCommentsPresenter();
}