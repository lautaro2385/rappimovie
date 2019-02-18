package co.com.gustavorealpe.rappimovie.view.popular;

import java.util.List;

import co.com.gustavorealpe.rappimovie.common.Movie;

public interface PopularListView {
    void visibleProgressBar(Boolean en);

    void setData(List<Movie> movies);
}
