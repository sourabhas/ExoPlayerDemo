package com.soushetty.exoplayerdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.net.URL;

/*ExoPlayer is an open source project that is not part of the Android framework and is distributed separately from the Android SDK.
ExoPlayer’s standard audio and video components are built on Android’s MediaCodec API, which was released in Android 4.1 (API level 16).
Because ExoPlayer is a library, you can easily take advantage of new features as they become available by updating your app.*/


/* get the exoplayer library dependencie from : https://github.com/google/ExoPlayer  and paste in build gradle file */

public class MainActivity extends AppCompatActivity {

    private PlayerView playerView;
    private SimpleExoPlayer exoPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerView=findViewById(R.id.player_view);


    }

    @Override
    protected void onStart() {
        super.onStart();
        //passing the context i.e this activity and using new default track selector to configure the exoplayer
        exoPlayer= ExoPlayerFactory.newSimpleInstance(this,new DefaultTrackSelector());

        playerView.setPlayer(exoPlayer); // setting up the relation between them, i.e the view and the video player


        //the data source creation
        DataSource.Factory datasourcefactory=new DefaultDataSourceFactory(this,
                Util.getUserAgent(this,getString(R.string.app_name))); //we are creating an instance through which the video player will be loaded

        // This is the MediaSource representing the media to be played.

        MediaSource videosource=new ExtractorMediaSource.Factory(datasourcefactory)
                .createMediaSource(Uri.parse("http://you.be/Rwyt1ScCM-k"));  //enter the URI of the video you want to play.

        // Prepare the player with the source.
        exoPlayer.prepare(videosource);



    }

    @Override
    protected void onStop() {
        super.onStop();
        playerView.setPlayer(null);
       // release the player when it’s no longer needed, so as to free up limited resources
        exoPlayer.release();
        exoPlayer=null;
    }
}
