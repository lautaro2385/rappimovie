package co.com.gustavorealpe.rappimovie.view;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import butterknife.ButterKnife;
import co.com.gustavorealpe.rappimovie.R;
import co.com.gustavorealpe.rappimovie.view.movie.MovieListFragment;

import static co.com.gustavorealpe.rappimovie.view.movie.MovieListFragment.POPULAR;
import static co.com.gustavorealpe.rappimovie.view.movie.MovieListFragment.TOP_RATED;
import static co.com.gustavorealpe.rappimovie.view.movie.MovieListFragment.UPCOMING;
import static co.com.gustavorealpe.rappimovie.view.movie.MovieListFragment.newInstance;


public class MainActivity extends AppCompatActivity {

    private Integer currentFragment = null;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        Integer val = POPULAR;
        switch (item.getItemId()) {
            case R.id.navigation_home:
                val = POPULAR;
                break;
            case R.id.navigation_dashboard:
                val = TOP_RATED;
                break;
            case R.id.navigation_notifications:
                val = UPCOMING;
                break;
        }
        if (!currentFragment.equals(val)) {
            MovieListFragment pop = newInstance(val);
            currentFragment = val;
            openFragment(pop);
        }
        return true;
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

        MovieListFragment pop = newInstance(POPULAR);
        currentFragment = POPULAR;
        openFragment(pop);
    }
}
