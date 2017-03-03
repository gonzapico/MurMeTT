package com.gonzapico.murmett.recordAudio;

/**
 * Created by gfernandez on 3/03/17.
 */

public interface RecordAudioView {

  void hideView();

  void updateTimer(String time);

  void showError(String s);
}
