package co.com.gustavorealpe.rappimovie.view.popular;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.List;

import javax.inject.Inject;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import co.com.gustavorealpe.rappimovie.R;
import co.com.gustavorealpe.rappimovie.common.Movie;
import co.com.gustavorealpe.rappimovie.di.component.DaggerPopularListComponent;
import co.com.gustavorealpe.rappimovie.di.module.PopularListModule;
import co.com.gustavorealpe.rappimovie.presenter.popular.PopularListPresenter;
import co.com.gustavorealpe.rappimovie.view.BaseFragment;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class PopularListListFragment extends BaseFragment implements PopularListView {
    private Unbinder unbinder;
    @Inject
    PopularListPresenter presenter;

    @BindView(R.id.list)
    RecyclerView recyclerView;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private OnListFragmentInteractionListener mListener;
    private PopularRecyclerViewAdapter adapter;

    public PopularListListFragment() {
    }

    public static PopularListListFragment newInstance() {
        PopularListListFragment fragment = new PopularListListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_popular_item_list, container, false);
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
        adapter = new PopularRecyclerViewAdapter(mListener);
        recyclerView.setAdapter(adapter);
    }

    /**
     * Inicia el inyector de dependencias
     */
    private void initDagger() {
        DaggerPopularListComponent.builder()
                .appComponent(getAppComponent())
                .popularListModule(new PopularListModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        presenter.onDestroy();
        mListener = null;
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
