package s2m.tryviperarchitecture.firstusecase.entity;

/**
 * Created by cta on 15/09/15.
 */
public class CommentEntity
{
    private long id;
    private String comment;

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getComment()
    {
        return comment;
    }

    public void setComment(String comment)
    {
        this.comment = comment;
    }
}
