package com.app.restrauntspinner.results;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.app.restrauntspinner.model.Item;
import java.util.List;

/**
 * Created by Home on 4/26/17.
 */

public class ResultViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<Item> venuesList;

    public ResultViewPagerAdapter(FragmentManager fm, List<Item> venuesList) {
        super(fm);
        this.venuesList = venuesList;
    }

    @Override
    public Fragment getItem(int position) {
        return ResultsFragment.newInstance(venuesList.get(position));
    }

    @Override
    public int getCount() {
        return venuesList.size();
    }
}
