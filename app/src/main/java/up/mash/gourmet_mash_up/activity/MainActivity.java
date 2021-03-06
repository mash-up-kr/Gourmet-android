package up.mash.gourmet_mash_up.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.Stack;

import butterknife.ButterKnife;
import timber.log.Timber;
import up.mash.gourmet_mash_up.R;

import up.mash.gourmet_mash_up.adapter.ViewPageAdapter;
import up.mash.gourmet_mash_up.fragment.AddFragment;
import up.mash.gourmet_mash_up.util.BottomNavigationViewHelper;
import up.mash.gourmet_mash_up.util.FragmentUtil;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;

    Intent it;

    BottomNavigationView bnView;
    BottomNavigationMenuView bnViewMenu;
    private ViewPageAdapter viewPageAdapter;
    AddFragment addFragment = new AddFragment();

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        it = new Intent(this.getIntent());
        bnView = findViewById(R.id.bottom_navigation);

        BottomNavigationViewHelper.disableShiftMode(bnView);

        viewPager = findViewById(R.id.view_pager);
        viewPageAdapter = new ViewPageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPageAdapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                Timber.tag("TAG").d("position : %s", position);
                switch (position) {
                    case 0:
                        bnView.setSelectedItemId(R.id.action_home);
                        break;
                    case 1:
                        bnView.setSelectedItemId(R.id.action_rank);
                        break;
                    case 2:
                        bnView.setSelectedItemId(R.id.action_profile);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        bnView.setOnNavigationItemSelectedListener(
                item -> {
                    switch (item.getItemId()) {
                        case R.id.action_home:
                            viewPager.setCurrentItem(0, true);
                            break;
                        case R.id.action_rank:
                            viewPager.setCurrentItem(1, true);
                            break;
                        case R.id.action_profile:
                            viewPager.setCurrentItem(2, true);
                            break;
                        case R.id.action_add:
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.entry_view, addFragment)
                                    .addToBackStack(null)
                                    .commit();
                            break;
                    }
                    return true;
                }
        );
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void setSelectedItemId(int position) {
        switch (position) {
            case 0:
                bnView.setSelectedItemId(R.id.action_home);
                break;
            case 1:
                bnView.setSelectedItemId(R.id.action_rank);
                break;
            case 2:
                bnView.setSelectedItemId(R.id.action_profile);
                break;
        }
    }

}
    