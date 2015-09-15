package s2m.tryviperarchitecture.firstusecase.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import s2m.tryviperarchitecture.R;

/**
 * Created by cta on 14/09/15.
 */
public class FirstArrayAdapter extends ArrayAdapter<FirstItem>
{
    public FirstArrayAdapter(Context context, List<FirstItem> items)
    {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        FirstItem firstItem = getItem(position);

        if (convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.main_row_item, parent, false);
        }

        // TODO not yet a view holder here
        TextView commentIdTextView = (TextView) convertView.findViewById(R.id.commentId);
        TextView commentValueTextView = (TextView) convertView.findViewById(R.id.commentValue);

        commentIdTextView.setText(String.valueOf(firstItem.getCommentId()));
        commentValueTextView.setText(firstItem.getCommentValue());

        return convertView;
    }
}
