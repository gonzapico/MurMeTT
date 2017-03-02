package com.gonzapico.murmett;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.DialogOnDeniedPermissionListener;
import com.karumi.dexter.listener.single.PermissionListener;
import com.karumi.dexter.listener.single.SnackbarOnDeniedPermissionListener;
import java.io.IOException;
import xyz.gonzapico.murmett.R;

/**
 * Created by gfernandez on 1/03/17.
 */

public class RecordAudioView extends BaseMMActivity {

  private static final String LOG_TAG = "AudioRecordTest";
  private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;
  private final static String ALIAS = "alias";
  private static String mFileName = null;
  private RecordButton mRecordButton = null;
  private MediaRecorder mRecorder = null;
  private PlayButton mPlayButton = null;
  private MediaPlayer mPlayer = null;
  private Button uploadButton;
  // Requesting permission to RECORD_AUDIO
  private boolean permissionToRecordAccepted = false;
  private String aliasUser = "";

  public static Intent getCallingIntent(Context context, String alias) {
    Intent intentToLaunch = new Intent(context, RecordAudioView.class);
    intentToLaunch.putExtra(ALIAS, alias);
    return intentToLaunch;
  }

  @Override protected int getLayoutResource() {
    return R.layout.player_layout;
  }

  boolean isPermissionGranted = false;
  private void onRecord(boolean start) {
    if (start) {
      startRecording();
    } else {
      stopRecording();
    }
  }

  private void onPlay(boolean start) {
    if (start) {
      startPlaying();
    } else {
      stopPlaying();
    }
  }

  private void startPlaying() {
    mPlayer = new MediaPlayer();
    try {
      mPlayer.setDataSource(mFileName);
      mPlayer.prepare();
      mPlayer.start();
    } catch (IOException e) {
      Log.e(LOG_TAG, "prepare() failed");
    }
  }

  private void stopPlaying() {
    mPlayer.release();
    mPlayer = null;
  }

  private void startRecording() {
    mRecorder = new MediaRecorder();
    mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
    mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
    mRecorder.setOutputFile(mFileName);
    mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

    try {
      mRecorder.prepare();
    } catch (IOException e) {
      Log.e(LOG_TAG, "prepare() failed");
    }

    mRecorder.start();
  }

  private void stopRecording() {
    mRecorder.stop();
    mRecorder.release();
    mRecorder = null;
  }
LinearLayout llGlobal;
  @Override public void onCreate(Bundle icicle) {
    super.onCreate(icicle);

    // Record to the external cache directory for visibility
    mFileName = getExternalFilesDir(Environment.DIRECTORY_MUSIC).getAbsolutePath();
    mFileName += "/mm_" + getIntent().getStringExtra(ALIAS) + ".3gp";

    LinearLayout ll = new LinearLayout(this);
    ll.setOrientation(LinearLayout.VERTICAL);
    mRecordButton = new RecordButton(this.getApplicationContext());
    ll.addView(mRecordButton, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
        ViewGroup.LayoutParams.WRAP_CONTENT, 0));
    mPlayButton = new PlayButton(this);
    ll.addView(mPlayButton, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
        ViewGroup.LayoutParams.WRAP_CONTENT, 0));
    llGlobal = ll;
    setContentView(llGlobal);
    mActivity = this;
  }

  @Override public void onStop() {
    super.onStop();
    if (mRecorder != null) {
      mRecorder.release();
      mRecorder = null;
    }

    if (mPlayer != null) {
      mPlayer.release();
      mPlayer = null;
    }
  }
  private Activity mActivity;
  class RecordButton extends Button {
    boolean mStartRecording = true;

    OnClickListener clicker = new OnClickListener() {
      public void onClick(View v) {
        /*
        PermissionListener dialogPermissionListener =
            DialogOnDeniedPermissionListener.Builder
                .withContext(mActivity)
                .withTitle("Micro permission")
                .withMessage("Micro permission is needed to save a record with your sweet voice!")
                .withButtonText(android.R.string.ok)
                .build();
        */
        Dexter.withActivity(mActivity)
            .withPermission(Manifest.permission.RECORD_AUDIO)
            .withListener(new PermissionListener() {
              @Override public void onPermissionGranted(PermissionGrantedResponse response) {

                isPermissionGranted = true;

                onRecord(mStartRecording);
                if (mStartRecording) {
                  setText("Stop recording");
                } else {
                  setText("Start recording");
                }
                mStartRecording = !mStartRecording;
              }

              @Override public void onPermissionDenied(PermissionDeniedResponse response) {/* ... */}

              @Override public void onPermissionRationaleShouldBeShown(PermissionRequest permission,
                  PermissionToken token) {/* ... */}
            })
            .onSameThread().check();
      }
    };

    public RecordButton(Context ctx) {
      super(ctx);
      setText("Start recording");
      setOnClickListener(clicker);
    }
  }

  class PlayButton extends Button {
    boolean mStartPlaying = true;

    OnClickListener clicker = new OnClickListener() {
      public void onClick(View v) {
        onPlay(mStartPlaying);
        if (mStartPlaying) {
          setText("Stop playing");
        } else {
          setText("Start playing");
        }
        mStartPlaying = !mStartPlaying;
      }
    };

    public PlayButton(Context ctx) {
      super(ctx);
      setText("Start playing");
      setOnClickListener(clicker);
    }
  }
}
