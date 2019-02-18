package co.com.gustavorealpe.rappimovie.view.search;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import co.com.gustavorealpe.rappimovie.App;
import co.com.gustavorealpe.rappimovie.R;
import co.com.gustavorealpe.rappimovie.common.Movie;
import co.com.gustavorealpe.rappimovie.di.component.DaggerSearchResultComponent;
import co.com.gustavorealpe.rappimovie.di.module.SearchResultModule;
import co.com.gustavorealpe.rappimovie.presenter.search.SearchResultPresenter;
import co.com.gustavorealpe.rappimovie.view.BaseActivity;
import co.com.gustavorealpe.rappimovie.view.movie.MovieDetailActivity;
import co.com.gustavorealpe.rappimovie.view.movie.MovieListFragment;
import co.com.gustavorealpe.rappimovie.view.movie.MovieRecyclerViewAdapter;

/**
 * Es la encargada de mostrar los resultado de la busqueda hecha por el usuario
 */
public class SearchResultsActivity extends Activity implements SearchResultView {

    @Inject
    SearchResultPresenter presenter;

    @BindView(R.id.list)
    RecyclerView recyclerView;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.tvTitle)
    TextView tvTitle;

    private MovieRecyclerViewAdapter adapter;

    private MovieListFragment.OnListFragmentInteractionListener mListener = new MovieListFragment.OnListFragmentInteractionListener() {
        @Override
        public void onListFragmentInteraction(Movie item) {
            Intent i = new Intent(SearchResultsActivity.this, MovieDetailActivity.class);
            i.putExtra(MovieDetailActivity.MOVIE_ID, item.getId());
            startActivity(i);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        ButterKnife.bind(this);
        handleIntent(getIntent());
        initRecyclerView();
        presenter.onInit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
        presenter = null;
    }

    private void initDagger(String search) {
        DaggerSearchResultComponent.builder()
                .appComponent(((App) getApplication()).getApplicationComponent())
                .searchResultModule(new SearchResultModule(this, search))
                .build()
                .inject(this);
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MovieRecyclerViewAdapter(mListener);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            //use the query to search your data somehow
            initDagger(query);
        }
    }

    @Override
    public void visibleProgressBar(boolean en) {
        progressBar.setVisibility(en ? View.VISIBLE : View.GONE);
        progressBar.animate();
    }

    @Override
    public void setData(List<Movie> movies) {
        adapter.setData(movies);
        tvTitle.setText("Resultados de la búsqueda("+movies.size()+"):");
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

}
