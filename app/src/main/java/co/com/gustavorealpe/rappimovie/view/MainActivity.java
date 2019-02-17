package co.com.gustavorealpe.rappimovie.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import butterknife.ButterKnife;
import co.com.gustavorealpe.rappimovie.R;
import co.com.gustavorealpe.rappimovie.view.popular.PopularListListFragment;
import co.com.gustavorealpe.rappimovie.view.topRated.TopRatedFragment;
import co.com.gustavorealpe.rappimovie.view.upcoming.UpcomingFragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity{

    private Fragment currentFragment = null;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        if(!(currentFragment instanceof  PopularListListFragment)) {
                            PopularListListFragment pop = PopularListListFragment.newInstance();
                            currentFragment = pop;
                            openFragment(pop);
                        }
                        return true;
                    case R.id.navigation_dashboard:
                        if(!(currentFragment instanceof  TopRatedFragment)) {
                            TopRatedFragment topRatedFragment = TopRatedFragment.newInstance(1);
                            currentFragment = topRatedFragment;
                            openFragment(topRatedFragment);
                        }
                        return true;
                    case R.id.navigation_notifications:
                        if(!(currentFragment instanceof  UpcomingFragment)) {
                            UpcomingFragment upcomingFragment = UpcomingFragment.newInstance(1);
                            currentFragment = upcomingFragment;
                            openFragment(upcomingFragment);
                        }
                        return true;
                }
                return false;
            };

    private void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        PopularListListFragment pop = PopularListListFragment.newInstance();
        currentFragment = pop;
        openFragment(pop);
    }
}
