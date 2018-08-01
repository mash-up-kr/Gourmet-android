package up.mash.gourmet_mash_up.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import up.mash.gourmet_mash_up.fragment.MemberFragment;
import up.mash.gourmet_mash_up.fragment.HomeFragment;

/**
 * Created by derba on 2018-06-30.
 */

public class ViewPageAdapter extends FragmentPagerAdapter {

    private final HomeFragment homeFragment;
    private final MemberFragment anotherFragment;


    public ViewPageAdapter(android.support.v4.app.FragmentManager fm) {
        super(fm);

        homeFragment = HomeFragment.newInstance();
        anotherFragment = MemberFragment.newInstance();
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return homeFragment;
            case 1:
                return anotherFragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
