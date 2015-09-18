package s2m.tryviperarchitecture.firstusecase.view;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import s2m.tryviperarchitecture.FragmentWithTitle;
import s2m.tryviperarchitecture.R;
import s2m.tryviperarchitecture.firstusecase.di.CommentsPresenterComponent;
import s2m.tryviperarchitecture.firstusecase.di.DaggerCommentsPresenterComponent;
import s2m.tryviperarchitecture.firstusecase.interactor.Comment;

/**
 * Created by cta on 17/09/15.
 */
public class CommentsFragment extends FragmentWithTitle implements View.OnClickListener, UpdateViewInterface
{
    private CommentsAdapter adapter;

    private ViewEventListener eventListener;

    @Bind(R.id.mainListView)
    RecyclerView mainListView;

    @Override
    public int getTitle()
    {
        return R.string.navigation_comments;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_comments, container, false);
        ButterKnife.bind(this, rootView);

        CommentsPresenterComponent component = DaggerCommentsPresenterComponent.create();
        eventListener = component.provideCommentsPresenter();
        eventListener.setOutput(this);

        adapter = new CommentsAdapter(new ArrayList<Comment>(), eventListener);
        mainListView.setAdapter(adapter);
        mainListView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

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

    public void setComments(List<Comment> commentList)
    {
        adapter.setData(commentList);
    }

    public void showDeletedCommentSnackbar(String snackBarContent)
    {
        Snackbar.make(mainListView, snackBarContent, Snackbar.LENGTH_SHORT).show();
    }

}