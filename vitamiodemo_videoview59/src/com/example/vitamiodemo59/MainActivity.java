package com.example.vitamiodemo59;

import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.MediaPlayer.OnCompletionListener;
import io.vov.vitamio.MediaPlayer.OnErrorListener;
import io.vov.vitamio.MediaPlayer.OnPreparedListener;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends Activity implements OnPreparedListener, OnErrorListener, OnCompletionListener {

	private VideoView mVitamio_vv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//使用vitamio需要初始化引擎,引擎的检查
		/**---------------需要注意 begin---------------**/
		if (!LibsChecker.checkVitamioLibs(this))
			return;
		/**---------------需要注意 end---------------**/

		mVitamio_vv = (VideoView) findViewById(R.id.vitamio_vv);
		/**---------------设置监听---------------**/
		mVitamio_vv.setOnPreparedListener(this);
		mVitamio_vv.setOnErrorListener(this);
		mVitamio_vv.setOnCompletionListener(this);
		//设置路径
		//本地视频
		//网络视频
		//流媒体
		mVitamio_vv.setVideoPath("storage/emulated/0/Download/5.mkv");
		//设置控制条
		mVitamio_vv.setMediaController(new MediaController(this));
		//开始播放
		mVitamio_vv.start();
	}

	@Override
	public void onCompletion(MediaPlayer mp) {
		// TODO
		Toast.makeText(getApplicationContext(), "播放完了", 0).show();
	}

	@Override
	public boolean onError(MediaPlayer mp, int what, int extra) {
		Toast.makeText(getApplicationContext(), "资源有问题", 0).show();
		// TODO
		return false;
	}

	@Override
	public void onPrepared(MediaPlayer mp) {
		Toast.makeText(getApplicationContext(), "准备好了", 0).show();
		// TODO

	}

}
