package com.gonzapico.murmett.di.components;

import com.gonzapico.murmett.ShowUsersFragment;
import com.gonzapico.murmett.di.PerActivity;
import com.gonzapico.murmett.di.modules.ActivityModule;
import com.gonzapico.murmett.di.modules.UserModule;
import dagger.Component;

/**
 * A scope {@link PerActivity}
 * component.
 * Injects user specific Fragments.
 */
@PerActivity @Component(dependencies = ApplicationComponent.class, modules = {
    ActivityModule.class, UserModule.class
}) public interface UserComponent extends ActivityComponent {
  void inject(ShowUsersFragment showUsersFragment);
}