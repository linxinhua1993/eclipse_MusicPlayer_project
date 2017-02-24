package com.baidu.bvideoviewsample1;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	private final String TAG = "MainActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
	}

	public void click(View v) {
		String path = "http://192.168.1.100:8080/3.avi";
		playVideo(path);
	}

	private void playVideo(String path) {
		String source = path;
		if (source == null || source.equals("")) {
			/**
			 * 简单检测播放源的合法性,不合法不播放
			 */
			Toast.makeText(this, "please input your video source", 500).show();
			Intent intent = new Intent(this, VideoViewPlayingActivity.class);
			intent.setData(Uri.parse(source));
			startActivity(intent);

		} else {
			Intent intent = new Intent(this, VideoViewPlayingActivity.class);
			intent.setData(Uri.parse(source));
			startActivity(intent);
		}
	}
}
