package com.gonzapico.murmett.recordAudio;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import com.gonzapico.murmett.BaseMMActivity;
import com.gonzapico.murmett.exception.ErrorMessageFactory;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import java.io.IOException;
import xyz.gonzapico.murmett.R;

/**
 * Created by gfernandez on 3/03/17.
 */

public class RecordAudioPresenter {
  private static final String LOG_TAG = "AudioRecordTest";
  private static String mFileName = null;
  private static Context mContext;
  Handler timerHandler = new Handler();
  long startTime = 0;
  private MediaRecorder mRecorder = null;
  private RecordAudioView mRecordAudioView;
  Runnable timerRunnable = new Runnable() {

    @Override public void run() {
      long millis = System.currentTimeMillis() - startTime;
      int seconds = (int) (millis / 1000);
      int minutes = seconds / 60;
      seconds = seconds % 60;

      mRecordAudioView.updateTimer(mContext.getResources()
          .getString(R.string.timer, String.format("%02d", minutes),
              String.format("%02d", seconds)));

      timerHandler.postDelayed(this, 500);
    }
  };

  public RecordAudioPresenter(Context context) {
    mContext = context;
  }

  public void setFilename(String userId) {
    mFileName = mContext.getExternalFilesDir(Environment.DIRECTORY_MUSIC).getAbsolutePath();
    mFileName += "/mm_" + userId + ".3gp";
  }

  public void startRecording() {
    if (!shouldCheckPermissions()) {
      mRecorder = new MediaRecorder();
      mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
      mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
      mRecorder.setOutputFile(mFileName);
      mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

      try {
        mRecorder.prepare();
      } catch (IOException e) {
        Log.e(LOG_TAG, "prepare() failed");
        mRecordAudioView.showError(e.getMessage());
      }

      try {
        mRecorder.start();
      } catch (Exception e) {
        mRecordAudioView.showError(ErrorMessageFactory.create(mContext, new Exception()));
        hideView();
      }
    } else {
      checkPermissions();
    }
  }

  public void stopRecording() {
    if (mRecorder != null) {
      mRecorder.stop();
      mRecorder.release();
      mRecorder = null;
    }
  }

  public void releaseTimer() {
    timerHandler.removeCallbacks(timerRunnable);
  }

  public void hideView() {
    mRecordAudioView.hideView();
  }

  public void setRecordView(RecordAudioView recordAudioView) {
    this.mRecordAudioView = recordAudioView;
  }

  public void startTimer() {
    startTime = System.currentTimeMillis();
    timerHandler.postDelayed(timerRunnable, 0);
  }

  private boolean shouldCheckPermissions() {
    return (ContextCompat.checkSelfPermission((BaseMMActivity) mContext,
        Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED);
  }

  private void checkPermissions() {
    Dexter.withActivity((BaseMMActivity) mContext)
        .withPermission(Manifest.permission.RECORD_AUDIO)
        .withListener(new PermissionListener() {
          @Override public void onPermissionGranted(PermissionGrantedResponse response) {

            startTimer();
            startRecording();
          }

          @Override public void onPermissionDenied(PermissionDeniedResponse response) {
            hideView();
          }

          @Override public void onPermissionRationaleShouldBeShown(PermissionRequest permission,
              PermissionToken token) {
            token.continuePermissionRequest();
          }
        }).onSameThread()
        .check();
  }
}
