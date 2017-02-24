package com.example.videoviewdemo59;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends Activity implements OnPreparedListener, OnErrorListener, OnCompletionListener {

	private VideoView mVv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//1.实例化videoView
		mVv = (VideoView) findViewById(R.id.vv);
		//设置相关的监听
		mVv.setOnPreparedListener(this);
		mVv.setOnErrorListener(this);
		mVv.setOnCompletionListener(this);
		/**
		//1.flv
		//2.3gp
		//3.avi
		//4.rmvb
		//5.mkv
		//storage/emulated/0/Download/5.mkv
		 */
		//2.设置视频的地址
		//		mVv.setVideoPath("/storage/emulated/0/Download/video_test.mp4");
		mVv.setVideoPath("http://192.168.1.100:8080/1.mp4");
//		mVv.setVideoPath("http://192.168.1.100:8080/2.3gp");
		//		mVv.setVideoURI(Uri.parse("http://192.168.1.100:8080/2.3gp"));

		//3.设置控制条
		mVv.setMediaController(new MediaController(this));
		//4.开始播放
		mVv.start();

	}

	@Override
	public void onCompletion(MediaPlayer mp) {
		// TODO
		Toast.makeText(getApplicationContext(), "播放完了", 0).show();

	}

	@Override
	public boolean onError(MediaPlayer mp, int what, int extra) {
		Toast.makeText(getApplicationContext(), "资源有问题", 0).show();
		return false;
	}

	@Override
	public void onPrepared(MediaPlayer mp) {
		Toast.makeText(getApplicationContext(), "准备好了", 0).show();
	}

}
