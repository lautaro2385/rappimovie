package co.com.gustavorealpe.rappimovie.view.movie;

import java.util.List;

import co.com.gustavorealpe.rappimovie.common.Movie;

public interface MovieListView {
    void visibleProgressBar(Boolean en);

    void setData(List<Movie> movies);

    void showMessage(String message);

}
