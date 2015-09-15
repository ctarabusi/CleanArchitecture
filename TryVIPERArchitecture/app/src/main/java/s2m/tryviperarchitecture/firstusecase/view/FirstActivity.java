package s2m.tryviperarchitecture.firstusecase.view;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import s2m.tryviperarchitecture.R;

public class FirstActivity extends AppCompatActivity implements View.OnClickListener
{
    private FirstPresenter presenter;

    @Bind(R.id.createEntryButton)
    Button createEntryButton;

    @Bind(R.id.mainListView)
    ListView mainListView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        createEntryButton.setOnClickListener(this);

        FirstArrayAdapter adapter = new FirstArrayAdapter(this, new ArrayList<FirstItem>());
        mainListView.setAdapter(adapter);

        presenter = new FirstPresenter(getApplicationContext(), adapter);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        presenter.onActivityResumed();
    }

    @Override
    protected void onPause()
    {
        presenter.onActivityPaused();
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

    @Override
    public void onClick(View v)
    {
        presenter.onCreateEntryButtonClicked();
    }

    @OnItemClick(R.id.mainListView)
    public void onItemClick(int position)
    {
        Snackbar.make(mainListView, "Deleted Entry " + position + "!", Snackbar.LENGTH_SHORT).show();
        presenter.onItemClicked(position);
    }

}
