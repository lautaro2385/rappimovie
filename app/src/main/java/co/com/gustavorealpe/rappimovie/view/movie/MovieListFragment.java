package co.com.gustavorealpe.rappimovie.view.movie;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import co.com.gustavorealpe.rappimovie.R;
import co.com.gustavorealpe.rappimovie.common.Movie;
import co.com.gustavorealpe.rappimovie.di.component.DaggerMovieListComponent;
import co.com.gustavorealpe.rappimovie.di.module.MovieListModule;
import co.com.gustavorealpe.rappimovie.presenter.movie.MovieListPresenter;
import co.com.gustavorealpe.rappimovie.view.BaseFragment;

/**
 * Lista las preliculas segun su categoría
 */
public class MovieListFragment extends BaseFragment implements MovieListView {
    private static final String CATEGORY_EXTRA = "TYPE";

    public static final Integer POPULAR = 0;
    public static final Integer TOP_RATED = 1;
    public static final Integer UPCOMING = 2;

    private Unbinder unbinder;
    @Inject
    MovieListPresenter presenter;

    @BindView(R.id.list)
    RecyclerView recyclerView;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private MovieRecyclerViewAdapter adapter;
    private  OnListFragmentInteractionListener mListener =new OnListFragmentInteractionListener() {
        @Override
        public void onListFragmentInteraction(Movie item) {
            Intent i = new Intent(getActivity(), MovieDetailActivity.class);
            i.putExtra(MovieDetailActivity.MOVIE_ID, item.getId());
            startActivity(i);
        }
    };

    public MovieListFragment() {
    }

    public static MovieListFragment newInstance(Integer category) {
        MovieListFragment fragment = new MovieListFragment();
        Bundle args = new Bundle();
        args.putInt(CATEGORY_EXTRA, category);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_item_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        // Set the adapter
        initRecyclerView(view);
        initDagger();
        presenter.onStart();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /**
     * Inicia el recycler
     * @param view
     */
    private void initRecyclerView(View view){
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        adapter = new MovieRecyclerViewAdapter(mListener);
        recyclerView.setAdapter(adapter);
    }

    /**
     * Inicia el inyector de dependencias
     */
    private void initDagger() {
        Integer mCategory = POPULAR;
        if (getArguments() != null) {
            mCategory = getArguments().getInt(CATEGORY_EXTRA);
        }
        DaggerMovieListComponent.builder()
                .appComponent(getAppComponent())
                .movieListModule(new MovieListModule(this, mCategory))
                .build()
                .inject(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        Animation animation = super.onCreateAnimation(transit, enter, nextAnim);

        // HW layer support only exists on API 11+
        if (Build.VERSION.SDK_INT >= 11) {
            if (animation == null && nextAnim != 0) {
                animation = AnimationUtils.loadAnimation(getActivity(), nextAnim);
            }

            if (animation != null) {
                getView().setLayerType(View.LAYER_TYPE_HARDWARE, null);

                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    public void onAnimationEnd(Animation animation) {
                        getView().setLayerType(View.LAYER_TYPE_NONE, null);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }

                    // ...other AnimationListener methods go here...
                });
            }
        }

        return animation;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        presenter.onDestroy();
    }

    @Override
    public void visibleProgressBar(Boolean en) {
        progressBar.setVisibility(en ? View.VISIBLE : View.GONE);
        progressBar.animate();
    }

    @Override
    public void setData(List<Movie> movies) {
        adapter.setData(movies);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Movie item);
    }
}
