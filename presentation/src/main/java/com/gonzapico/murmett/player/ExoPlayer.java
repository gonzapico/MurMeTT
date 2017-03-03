package com.gonzapico.murmett.player;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveVideoTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by gfernandez on 1/03/17.
 */

@Singleton public class ExoPlayer implements MurMePlayer {

  private Context mContext;
  private SimpleExoPlayer mPlayer;

  @Inject public ExoPlayer(Context context) {
    mContext = context;
    setUpExoPlayer();
  }

  private void setUpExoPlayer() {
    // 1. Create a default TrackSelector
    Handler mainHandler = new Handler();
    BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
    TrackSelection.Factory videoTrackSelectionFactory =
        new AdaptiveVideoTrackSelection.Factory(bandwidthMeter);
    TrackSelector trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);

    // 2. Create a default LoadControl
    LoadControl loadControl = new DefaultLoadControl();

    // 3. Create the player
    mPlayer = ExoPlayerFactory.newSimpleInstance(mContext, trackSelector, loadControl);
  }

  @Override public void playAudio(String src) {
    // Measures bandwidth during playback. Can be null if not required.
    DefaultBandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
    // Produces DataSource instances through which media data is loaded.
    DataSource.Factory dataSourceFactory =
        new DefaultDataSourceFactory(mContext, Util.getUserAgent(mContext, "xyz.gonzapico.murmett"),
            bandwidthMeter);
    // Produces Extractor instances for parsing the media data.
    ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
    // This is the MediaSource representing the media to be played.
    MediaSource videoSource =
        new ExtractorMediaSource(Uri.parse(src), dataSourceFactory, extractorsFactory, null, null);
    // Prepare the player with the source.
    mPlayer.prepare(videoSource);
    mPlayer.setPlayWhenReady(true);
  }

  public SimpleExoPlayer getPlayer() {
    return mPlayer;
  }

  @Override public void release() {
    if (mPlayer != null) mPlayer.release();
  }
}
