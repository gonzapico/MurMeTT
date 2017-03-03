package com.gonzapico.murmett;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.gonzapico.murmett.di.HasComponent;
import com.gonzapico.murmett.di.components.ApplicationComponent;
import com.gonzapico.murmett.di.components.DaggerUserComponent;
import com.gonzapico.murmett.di.components.UserComponent;
import com.gonzapico.murmett.di.modules.ActivityModule;
import xyz.gonzapico.murmett.R;

/**
 * Created by gfernandez on 26/02/17.
 */

public abstract class BaseMMActivity extends AppCompatActivity
    implements HasComponent<UserComponent> {
  //@Inject Navigator navigator;
  UserComponent mUserComponent;
  @BindView(R.id.toolbar) Toolbar toolbar;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(getLayoutResource());
    ButterKnife.bind(this);

    if (toolbar != null) {
      setSupportActionBar(toolbar);

      getSupportActionBar().setTitle("");
    }

    this.initializeInjector();
  }

  /***
   * Method to init the DI and has all the classes availables
   */
  private void initializeInjector() {
    this.mUserComponent = DaggerUserComponent.builder()
        .applicationComponent(getApplicationComponent())
        .activityModule(getActivityModule())
        .build();
  }

  protected abstract int getLayoutResource();

  /**
   * Adds a {@link Fragment} to this activity's layout.
   *
   * @param containerViewId The container view to where add the fragment.
   * @param fragment The fragment to be added.
   */
  protected void addFragment(int containerViewId, Fragment fragment) {
    FragmentTransaction fragmentTransaction = this.getFragmentManager().beginTransaction();
    fragmentTransaction.add(containerViewId, fragment);
    fragmentTransaction.addToBackStack(null);
    fragmentTransaction.commit();
  }

  /***
   * Get out a {@link Fragment} from the back stack.
   */
  protected void popFragment() {
    if (getFragmentManager().getBackStackEntryCount() > 1) this.getFragmentManager().popBackStack();
  }

  /**
   * Get the Main Application component for dependency injection.
   *
   * @return {@link ApplicationComponent}
   */
  protected ApplicationComponent getApplicationComponent() {
    return ((BaseMMApplication) getApplication()).getApplicationComponent();
  }

  /**
   * Get an Activity module for dependency injection.
   *
   * @return {@link ActivityModule}
   */
  protected ActivityModule getActivityModule() {
    return new ActivityModule(this);
  }

  @Override public UserComponent getComponent() {
    return mUserComponent;
  }
}
