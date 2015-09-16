package s2m.tryviperarchitecture.firstusecase.interactor;

import java.util.List;

import s2m.tryviperarchitecture.firstusecase.view.Comment;

/**
 * Created by cta on 15/09/15.
 */
public interface DataSourceListener
{
    void dataChanged(final List<Comment> comments);
}
