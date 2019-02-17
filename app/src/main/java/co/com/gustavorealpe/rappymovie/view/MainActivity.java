package co.com.gustavorealpe.rappymovie.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;
import butterknife.ButterKnife;
import co.com.gustavorealpe.rappymovie.R;
import co.com.gustavorealpe.rappymovie.view.popular.PopularListFragment;
import co.com.gustavorealpe.rappymovie.view.popular.dummy.DummyContent;
import co.com.gustavorealpe.rappymovie.view.topRated.TopRatedFragment;
import co.com.gustavorealpe.rappymovie.view.upcoming.UpcomingFragment;

import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity implements PopularListFragment.OnListFragmentInteractionListener {

    @BindView(R.id.message)
    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    PopularListFragment pop = PopularListFragment.newInstance(1);
                    openFragment(pop);
                    return true;
                case R.id.navigation_dashboard:
                    TopRatedFragment topRatedFragment = TopRatedFragment.newInstance(1);
                    openFragment(topRatedFragment);
                    return true;
                case R.id.navigation_notifications:
                    UpcomingFragment upcomingFragment = UpcomingFragment.newInstance(1);
                    openFragment(upcomingFragment);
                    return true;
            }
            return false;
        }
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

        PopularListFragment pop = PopularListFragment.newInstance(1);
        openFragment(pop);
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }
}
