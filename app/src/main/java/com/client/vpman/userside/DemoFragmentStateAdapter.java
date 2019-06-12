package com.client.vpman.userside;



import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class DemoFragmentStateAdapter extends FragmentStatePagerAdapter {
    public DemoFragmentStateAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {

            case 0:
                return Tournament.newInstance("Tournament, Instance 1");

            case 1:
                return Home.newInstance("Home, Instance 1");
            case 2:
                return User.newInstance("User, Instance 1");
            /*case 3:
                return PFFirst.newInstance("PFFirst, Instance 4");*/
            default:
                return Tournament.newInstance("Tournament, Instance 1");
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
