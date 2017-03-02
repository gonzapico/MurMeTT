package com.gonzapico.murmett;

import android.app.Application;
import com.gonzapico.murmett.di.components.ApplicationComponent;
import com.gonzapico.murmett.di.components.DaggerApplicationComponent;
import com.gonzapico.murmett.di.modules.ApplicationModule;

/**
 * Created by gfernandez on 1/03/17.
 */

public class BaseMMApplication extends Application {

  private ApplicationComponent applicationComponent;

  @Override public void onCreate() {
    super.onCreate();
    this.initializeInjector();
  }

  private void initializeInjector() {
    this.applicationComponent =
        DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
  }

  public ApplicationComponent getApplicationComponent() {
    return this.applicationComponent;
  }
}
