package s2m.tryviperarchitecture.firstusecase.view;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import s2m.tryviperarchitecture.R;
import s2m.tryviperarchitecture.TitleFragment;
import s2m.tryviperarchitecture.firstusecase.di.CommentsPresenterComponent;
import s2m.tryviperarchitecture.firstusecase.di.DaggerCommentsPresenterComponent;
import s2m.tryviperarchitecture.firstusecase.interactor.Comment;

/**
 * Created by cta on 17/09/15.
 */
public class CommentsFragment extends TitleFragment implements View.OnClickListener, UpdateViewInterface
{
    private CommentsArrayAdapter adapter;

    private ViewEventListener eventListener;

    @Bind(R.id.mainListView)
    ListView mainListView;

    @Override
    public String getTitle()
    {
        return "Comments";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_comments, container, false);
        ButterKnife.bind(this, rootView);

        adapter = new CommentsArrayAdapter(this.getActivity(), new ArrayList<Comment>());
        mainListView.setAdapter(adapter);

        CommentsPresenterComponent component = DaggerCommentsPresenterComponent.create();
        eventListener = component.provideCommentsPresenter();
        eventListener.setOutput(this);

        return rootView;
    }

    @Override
    public void onResume()
    {
        super.onResume();
        eventListener.viewVisible();
    }

    @Override
    public void onPause()
    {
        eventListener.viewGone();
        super.onPause();
    }

    @OnClick(R.id.createEntryButton)
    public void onClick(View v)
    {
        eventListener.createRequested();
    }

    @OnItemClick(R.id.mainListView)
    public void onItemClick(int position)
    {
        Comment itemClicked = adapter.getItem(position);
        eventListener.deleteRequested(itemClicked);
    }

    public void setComments(List<Comment> commentList)
    {
        adapter.clear();
        adapter.addAll(commentList);
    }

    public void showDeletedCommentSnackbar(String snackBarContent)
    {
        Snackbar.make(mainListView, snackBarContent, Snackbar.LENGTH_SHORT).show();
    }

}