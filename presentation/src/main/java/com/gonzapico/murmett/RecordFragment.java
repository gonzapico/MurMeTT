package com.gonzapico.murmett;

import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.gonzapico.murmett.recordAudio.RecordAudioPresenter;
import xyz.gonzapico.murmett.R;

public class RecordFragment extends BaseMMFragment
    implements com.gonzapico.murmett.recordAudio.RecordAudioView {

  private static final String ID_USER = "id_user";
  RecordAudioPresenter recordAudioPresenter;
  @BindView(R.id.tvTimer) TextView tvTimer;
  @BindView(R.id.ivRecordAudio) ImageView ivRecordAudio;
  private boolean isSpeakButtonLongPressed = false;

  private View.OnLongClickListener speakHoldListener = new View.OnLongClickListener() {

    @Override public boolean onLongClick(View pView) {
      // Do something when your hold starts here.
      isSpeakButtonLongPressed = true;
      Log.d("test",
          "test ->" + isSpeakButtonLongPressed + " " + pView.getX() + " -- " + pView.getY());
      return true;
    }
  };

  private View.OnTouchListener speakTouchListener = new View.OnTouchListener() {

    @Override public boolean onTouch(View pView, MotionEvent pEvent) {
      pView.onTouchEvent(pEvent);
      // We're only interested in when the button is released.
      if (pEvent.getAction() == MotionEvent.ACTION_UP) {
        // We're only interested in anything if our speak button is currently pressed.
        if (isSpeakButtonLongPressed) {
          // Do something when the button is released.
          isSpeakButtonLongPressed = false;
          int[] posXY = new int[2];
          ivRecordAudio.getLocationOnScreen(posXY);
          int x = posXY[0];
          int y = posXY[1];

          Display display = getActivity().getWindowManager().getDefaultDisplay();
          Point size = new Point();
          display.getSize(size);
          int width = size.x;
          int height = size.y;
          Log.d("test", width + "test ->" + isSpeakButtonLongPressed + (x - pEvent.getX()) + " y " + (y
              - pEvent.getY()));
          if ((x - pEvent.getX()) > width) hideView();
        }
      }
      return false;
    }
  };

  public static RecordFragment newInstance(String pathLastAudio) {
    RecordFragment recordFragment = new RecordFragment();
    if (!TextUtils.isEmpty(pathLastAudio)) {
      Bundle arguments = new Bundle();

      arguments.putString(ID_USER, pathLastAudio);
      recordFragment.setArguments(arguments);
    }

    return recordFragment;
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    recordAudioPresenter = new RecordAudioPresenter(getActivity());
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    if (getArguments() != null) {
      recordAudioPresenter.setFilename(getArguments().getString(ID_USER));
    }
    recordAudioPresenter.setRecordView(this);
    recordAudioPresenter.startTimer();
    recordAudioPresenter.startRecording();

    ivRecordAudio.setOnLongClickListener(speakHoldListener);
    ivRecordAudio.setOnTouchListener(speakTouchListener);
  }

  @OnClick(R.id.ivClose) void closeFragment() {
    hideView();
  }

  @Override public void hideView() {
    ((BaseMMActivity) getActivity()).popFragment();
  }

  @Override public void updateTimer(String time) {
    tvTimer.setText(time);
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.content_record_audio, container, false);
    ButterKnife.bind(this, view);
    return view;
  }

  @Override public void onDestroy() {
    super.onDestroy();

    release();
  }

  @Override public void onPause() {
    super.onPause();

    hideView();
  }

  private void release() {
    recordAudioPresenter.releaseTimer();
    recordAudioPresenter.stopRecording();
  }

  @Override public void showError(String errorMsg) {
    showToastMessage(errorMsg);
  }
}
