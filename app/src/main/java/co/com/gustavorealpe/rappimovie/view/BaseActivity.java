package co.com.gustavorealpe.rappimovie.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import co.com.gustavorealpe.rappimovie.App;
import co.com.gustavorealpe.rappimovie.di.component.AppComponent;

public class BaseActivity extends AppCompatActivity {
    protected AppComponent getAppComponent() {
        return ((App) getApplication()).getApplicationComponent();
    }

}
