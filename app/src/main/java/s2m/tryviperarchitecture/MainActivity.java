package s2m.tryviperarchitecture;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends ActionBarActivity
{
    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @Bind(R.id.navigation_view)
    NavigationView navigationView;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener()
        {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem)
            {
                //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked()) menuItem.setChecked(false);
                else menuItem.setChecked(true);

                //Closing drawer on item click
                mDrawerLayout.closeDrawers();
                navigateTo(menuItem.getItemId());
                return true;
            }
        });

        if (savedInstanceState == null)
        {
            navigateTo(Router.NavigationPaths.COMMENTS);
        }
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

    public void navigateTo(Router.NavigationPaths navigation)
    {
        Router.getInstance().navigateFromDrawer(this, navigation);
    }

    public void navigateTo(int menuId)
    {
        if (menuId == R.id.navDrawerComments)
        {
            navigateTo(Router.NavigationPaths.COMMENTS);
        }
        else if (menuId == R.id.navDrawerTabLayout)
        {
            navigateTo(Router.NavigationPaths.TAB_LAYOUT);
        }
        else if (menuId == R.id.navDrawerRecord)
        {
            navigateTo(Router.NavigationPaths.RECORD);
        }
        else if (menuId == R.id.navDrawerCamera)
        {
            navigateTo(Router.NavigationPaths.CAMERA);
        }
    }

    public void closeDrawer()
    {
        mDrawerLayout.closeDrawer(Gravity.LEFT);
    }
}
