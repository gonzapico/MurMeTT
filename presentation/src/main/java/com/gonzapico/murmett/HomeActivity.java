package com.gonzapico.murmett;

import android.os.Bundle;
import android.util.Log;
import com.gonzapico.murmett.player.ExoPlayer;
import com.gonzapico.murmett.showUsers.UserModel;
import xyz.gonzapico.murmett.R;

public class HomeActivity extends BaseMMActivity implements ShowUsersFragment.UserListListener {

  private final String TAG_NAME = getClass().getName().toString();
  private ExoPlayer player;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (savedInstanceState == null) {
      addFragment(R.id.fragmentContainer, ShowUsersFragment.newInstance());
    }
    player = new ExoPlayer(this);
  }

  @Override public int getLayoutResource() {
    return R.layout.activity_home;
  }

  @Override public void onPresentationAudioClicked(String audioUrl) {
    Log.d(TAG_NAME, "onPresentationAudioClicked -> " + audioUrl);
    player.playAudio(audioUrl);
  }

  @Override public void onLatestAudioClicked(String audioSrc) {
    Log.d(TAG_NAME, "onLatestAudioClicked -> " + audioSrc);
    player.playAudio(audioSrc);
  }

  @Override public void onRecordAudio(UserModel userModel) {
    addFragment(R.id.fragmentContainer, RecordFragment.newInstance(userModel.getId()));
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    releasePlayer();
  }

  @Override public void onStopAudio() {
    player.getPlayer().stop();
  }

  private void releasePlayer() {
    player.release();
  }

  @Override public void onBackPressed() {

    /***
     * To avoid close the app when user tap back button and he/she is in the record view
     */
    if (getFragmentManager().getBackStackEntryCount() > 1) {
      // HomeActivity is composed by one fragment
      getFragmentManager().popBackStack();
    } else {
      this.finish();
    }
  }
}
