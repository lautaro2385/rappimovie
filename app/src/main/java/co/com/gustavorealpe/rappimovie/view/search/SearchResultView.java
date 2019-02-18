package co.com.gustavorealpe.rappimovie.view.search;

import java.util.List;

import co.com.gustavorealpe.rappimovie.common.Movie;

public interface SearchResultView {
    void visibleProgressBar(boolean b);

    void setData(List<Movie> movies);

    void showMessage(String message);
}
