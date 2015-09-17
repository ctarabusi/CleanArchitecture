package s2m.tryviperarchitecture.firstusecase.view;

import java.util.List;

import s2m.tryviperarchitecture.firstusecase.interactor.Comment;

/**
 * Created by cta on 15/09/15.
 */
public interface UpdateViewInterface
{
     void setComments(List<Comment> commentList);

     void showDeletedCommentSnackbar(String snackbarText);
}
