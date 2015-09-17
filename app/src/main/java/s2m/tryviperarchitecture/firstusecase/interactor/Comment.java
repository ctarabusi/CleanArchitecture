package s2m.tryviperarchitecture.firstusecase.interactor;

/**
 * Created by cta on 14/09/15.
 */
public class Comment
{
    private long commentId;
    private String commentValue;

    public Comment(final long commentId, final String commentValue)
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
