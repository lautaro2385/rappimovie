package co.com.gustavorealpe.rappimovie.view;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.SearchView;

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
            case R.id.navigation_popular:
                this.getSupportActionBar().setTitle("Popular");
                val = POPULAR;
                break;
            case R.id.navigation_top_rated:
                this.getSupportActionBar().setTitle("Top Rated");
                val = TOP_RATED;
                break;
            case R.id.navigation_upcoming:
                this.getSupportActionBar().setTitle("Upcoming");
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
        transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        return true;
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

        this.getSupportActionBar().setTitle("Popular");
    }
}
