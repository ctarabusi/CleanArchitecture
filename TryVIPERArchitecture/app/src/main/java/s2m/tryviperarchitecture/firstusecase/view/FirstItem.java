package s2m.tryviperarchitecture.firstusecase.view;

/**
 * Created by cta on 14/09/15.
 */
public class FirstItem
{
    private long commentId;
    private String commentValue;

    public FirstItem(final long commentId, final String commentValue)
    {
        this.commentId = commentId;
        this.commentValue = commentValue;
    }

    public long getCommentId()
    {
        return commentId;
    }

    public String getCommentValue()
    {
        return commentValue;
    }
}
