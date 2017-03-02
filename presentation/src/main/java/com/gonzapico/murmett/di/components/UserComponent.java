package com.gonzapico.murmett.di.components;

import dagger.Component;
import xyz.gonzapico.a24itt.ShowPopularMoviesFragment;
import xyz.gonzapico.a24itt.WatchTrailerFragment;
import xyz.gonzapico.a24itt.di.PerActivity;
import xyz.gonzapico.a24itt.di.modules.ActivityModule;
import xyz.gonzapico.a24itt.di.modules.MovieModule;

/**
 * Created by gfernandez on 31/10/16.
 */
@PerActivity @Component(dependencies = ApplicationComponent.class, modules = {
    ActivityModule.class, MovieModule.class
}) public interface UserComponent extends ActivityComponent {
  void inject(ShowPopularMoviesFragment showPopularMoviesFragment);
  void inject(WatchTrailerFragment watchTrailerFragment);
}