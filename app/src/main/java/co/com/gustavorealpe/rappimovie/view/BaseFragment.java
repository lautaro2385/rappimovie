package co.com.gustavorealpe.rappimovie.view;

import androidx.fragment.app.Fragment;
import co.com.gustavorealpe.rappimovie.App;
import co.com.gustavorealpe.rappimovie.di.component.AppComponent;

public class BaseFragment extends Fragment {
    protected AppComponent getAppComponent() {
        return ((App) getActivity().getApplication()).getApplicationComponent();
    }

}
