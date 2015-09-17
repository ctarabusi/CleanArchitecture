package s2m.tryviperarchitecture.firstusecase.interactor;

import java.util.List;

/**
 * Created by cta on 15/09/15.
 */
public interface DataChangeListener
{
    void dataChanged(final List<Comment> comments);
}
