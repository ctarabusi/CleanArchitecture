package s2m.tryviperarchitecture.firstusecase.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import s2m.tryviperarchitecture.R;
import s2m.tryviperarchitecture.firstusecase.interactor.Comment;

/**
 * Created by cta on 14/09/15.
 */
public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.ViewHolder>
{
    private List<Comment> items;

    private ViewEventListener eventListener;

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView commentIdTextView;
        public TextView commentValueTextView;
        public ImageView commentRemoveButton;

        public ViewHolder(View rowView)
        {
            super(rowView);
            this.commentIdTextView = (TextView) rowView.findViewById(R.id.commentId);
            this.commentValueTextView = (TextView) rowView.findViewById(R.id.commentValue);
            this.commentRemoveButton = (ImageView) rowView.findViewById(R.id.commentRemove);
        }
    }

    public CommentsAdapter(List<Comment> items, ViewEventListener eventListener)
    {
        this.items = items;
        this.eventListener = eventListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
    {
        View rowView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.comment_row_item, viewGroup, false);

        return new ViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i)
    {
        final Comment comment = items.get(i);
        viewHolder.commentIdTextView.setText(String.valueOf(comment.getCommentId()));
        viewHolder.commentValueTextView.setText(comment.getCommentValue());
        viewHolder.commentRemoveButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                eventListener.deleteRequested(comment);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return items.size();
    }

    public void setData(@NonNull final List<Comment> items)
    {
        this.items = items;
        notifyDataSetChanged();
    }
}
