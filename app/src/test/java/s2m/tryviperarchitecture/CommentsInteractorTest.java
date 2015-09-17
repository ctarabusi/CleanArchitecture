package s2m.tryviperarchitecture;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import s2m.tryviperarchitecture.firstusecase.entity.CommentEntity;
import s2m.tryviperarchitecture.firstusecase.entity.CommentsDataStore;
import s2m.tryviperarchitecture.firstusecase.interactor.CommentsInteractor;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by cta on 16/09/15.
 */

@RunWith(RobolectricTestRunner.class)
public class CommentsInteractorTest
{
    @Test
    public void testInteractorCreateEntry()
    {
        CommentEntity mockCommentEntity = mock(CommentEntity.class);

        CommentsDataStore mockCommentsDataStore = mock(CommentsDataStore.class);
        when(mockCommentsDataStore.createComment(any(String.class))).thenReturn(mockCommentEntity);

        CommentsInteractor interactor = new CommentsInteractor(mockCommentsDataStore);

        interactor.createDBEntry();
        verify(mockCommentsDataStore).createComment(anyString());

    }
}
