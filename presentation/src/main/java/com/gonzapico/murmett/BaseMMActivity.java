package com.gonzapico.murmett;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.gonzapico.murmett.R;

/**
 * Created by gfernandez on 26/02/17.
 */

public abstract class BaseMMActivity extends AppCompatActivity {

  @BindView(R.id.toolbar) Toolbar mToolbar;

  @Override public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
    super.onCreate(savedInstanceState, persistentState);

    setContentView(getLayoutResource());

    ButterKnife.bind(this);
  }

  public abstract int getLayoutResource();
}
