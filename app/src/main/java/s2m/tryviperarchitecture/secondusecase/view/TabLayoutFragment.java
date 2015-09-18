package s2m.tryviperarchitecture.secondusecase.view;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;
import s2m.tryviperarchitecture.R;
import s2m.tryviperarchitecture.FragmentWithTitle;

/**
 * Created by cta on 17/09/15.
 */
public class TabLayoutFragment extends FragmentWithTitle
{
    private static final int NUM_PAGES = 3;

    @Bind(R.id.tabLayout)
    TabLayout tabLayout;

    @Bind(R.id.pager)
    ViewPager viewPager;

    @Override
    public int getTitle()
    {
        return R.string.navigation_tablayout;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_tablayout, container, false);
        ButterKnife.bind(this, rootView);

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        PagerAdapter mPagerAdapter = new ScreenSlidePagerAdapter(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(mPagerAdapter);

        tabLayout.setupWithViewPager(viewPager);

        return rootView;
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter
    {
        public ScreenSlidePagerAdapter(FragmentManager fm)
        {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position)
        {
            return "Tab " + position;
        }

        @Override
        public Fragment getItem(int position)
        {
            return new ScreenSlidePageFragment();
        }

        @Override
        public int getCount()
        {
            return NUM_PAGES;
        }
    }

}

