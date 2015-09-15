package s2m.tryviperarchitecture.firstusecase.view;

import java.util.List;

/**
 * Created by cta on 15/09/15.
 */
public interface CommentsViewInterface
{
     void setComments(List<Comment> commentList);

     void showDeletedCommentSnackbar(String snackbarText);
}
