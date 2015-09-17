package s2m.tryviperarchitecture;

import org.junit.Test;

import s2m.tryviperarchitecture.firstusecase.interactor.CommentsInteractor;
import s2m.tryviperarchitecture.firstusecase.view.CommentsPresenter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by cta on 16/09/15.
 */
public class CommentsPresenterTest
{
    @Test
    public void testPresenterOnCreate()
    {

        CommentsInteractor mockInteractor = mock(CommentsInteractor.class);
        CommentsPresenter presenter = new CommentsPresenter(mockInteractor);

        presenter.createRequested();

        verify(mockInteractor).createDBEntry();
    }

    @Test
    public void testPresenterOnViewChange()
    {
        CommentsInteractor mockInteractor = mock(CommentsInteractor.class);
        CommentsPresenter presenter = new CommentsPresenter(mockInteractor);

        presenter.viewGone();
        verify(mockInteractor).closeConnection();

        presenter.viewVisible();
        verify(mockInteractor).openConnection();
    }
}
