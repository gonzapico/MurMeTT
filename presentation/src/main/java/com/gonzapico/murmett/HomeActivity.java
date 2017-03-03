package com.gonzapico.murmett;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import butterknife.BindView;
import com.gonzapico.murmett.player.ExoPlayer;
import com.gonzapico.murmett.showUsers.UserModel;
import xyz.gonzapico.murmett.R;

public class HomeActivity extends BaseMMActivity implements ShowUsersFragment.UserListListener {

  @BindView(R.id.rvUsers) RecyclerView rvUsers;
  ExoPlayer player;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (savedInstanceState == null) {
      addFragment(R.id.fragmentContainer, ShowUsersFragment.newInstance());
    }
  }

  @Override public int getLayoutResource() {
    return R.layout.activity_home;
  }

  @Override public void onPresentationAudioClicked(String audioUrl) {
    Log.d("MURME", "onLatestAudioClicked -> " + audioUrl);
    player = new ExoPlayer(this);
    player.playAudio(audioUrl);
  }

  @Override public void onLatestAudioClicked(String audioSrc) {
    Log.d("MURME", "onLatestAudioClicked -> " + audioSrc);
    player = new ExoPlayer(this);
    player.playAudio(audioSrc);
  }

  @Override public void onRecordAudio(UserModel userModel) {

  }

  @Override protected void onDestroy() {
    super.onDestroy();
    player.getPlayer().release();
  }
}
