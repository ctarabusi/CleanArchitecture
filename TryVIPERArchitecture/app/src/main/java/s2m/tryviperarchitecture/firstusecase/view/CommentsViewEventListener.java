package s2m.tryviperarchitecture.firstusecase.view;

/**
 * Created by cta on 15/09/15.
 */
public interface CommentsViewEventListener
{
    void createRequested();

    void deleteRequested(Comment commentToDelete);
}
