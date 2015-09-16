package s2m.tryviperarchitecture.firstusecase.view;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import s2m.tryviperarchitecture.R;
import s2m.tryviperarchitecture.firstusecase.module.CommentsPresenterComponent;
import s2m.tryviperarchitecture.firstusecase.module.CommentsPresenterModule;
import s2m.tryviperarchitecture.firstusecase.module.DaggerCommentsPresenterComponent;

public class CommentsActivity extends AppCompatActivity implements View.OnClickListener, CommentsViewInterface
{
    private CommentsArrayAdapter adapter;

    private CommentsViewEventListener eventListener;

    @Bind(R.id.mainListView)
    ListView mainListView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        adapter = new CommentsArrayAdapter(this, new ArrayList<Comment>());
        mainListView.setAdapter(adapter);

        CommentsPresenterComponent component = DaggerCommentsPresenterComponent.create();
        eventListener = component.provideCommentsPresenter();

       // eventListener = (CommentsViewEventListener) objectForClassName("s2m.tryviperarchitecture.firstusecase.view.CommentsPresenter");
        eventListener.setOutput(this);
    }

    public Object objectForClassName(String presenterClassName)
    {
        Object presenter = null;
        try
        {
            Class presenterClass = Class.forName(presenterClassName);
            Constructor constructor = presenterClass.getConstructor();
            presenter = constructor.newInstance();
        }
        catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e)
        {
            e.printStackTrace();
        }

        return presenter;
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        eventListener.viewVisible();
    }

    @Override
    protected void onPause()
    {
        eventListener.viewGone();
        super.onPause();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
