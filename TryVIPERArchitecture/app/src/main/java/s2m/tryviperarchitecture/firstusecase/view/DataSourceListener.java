package s2m.tryviperarchitecture.firstusecase.view;

import java.util.List;

/**
 * Created by cta on 15/09/15.
 */
public interface DataSourceListener
{
    void dataChanged(final List<Comment> comments);
}
