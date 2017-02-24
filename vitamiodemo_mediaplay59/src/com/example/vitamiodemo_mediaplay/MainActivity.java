package com.example.vitamiodemo_mediaplay;

import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.MediaPlayer.OnCompletionListener;
import io.vov.vitamio.MediaPlayer.OnErrorListener;
import io.vov.vitamio.MediaPlayer.OnPreparedListener;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity implements OnErrorListener, OnPreparedListener, OnCompletionListener {

	private MediaPlayer mMediaPlayer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//使用vitamio需要初始化引擎,引擎的检查
		/**---------------需要注意 begin---------------**/
		if (!LibsChecker.checkVitamioLibs(this))
			return;
		/**---------------需要注意 end---------------**/

	}

	public void click(View v) {
		try {
			mMediaPlayer = new MediaPlayer(this);
			mMediaPlayer.setOnErrorListener(this);
			mMediaPlayer.setOnPreparedListener(this);
			mMediaPlayer.setOnCompletionListener(this);
			mMediaPlayer.reset();
			mMediaPlayer.setDataSource("mms://mediasrv2.iptv.xmg.com.cn/yinyue");
			mMediaPlayer.prepareAsync();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void onCompletion(MediaPlayer mp) {
		Toast.makeText(getApplicationContext(), "播放完了", 0).show();
	}

	@Override
	public void onPrepared(MediaPlayer mp) {
		Toast.makeText(getApplicationContext(), "准备好了", 0).show();
		mMediaPlayer.start();

	}

	@Override
	public boolean onError(MediaPlayer mp, int what, int extra) {
		Toast.makeText(getApplicationContext(), "资源有问题", 0).show();
		// TODO
		return true;
	}

	@Override
	protected void onDestroy() {
		if (mMediaPlayer != null) {
			mMediaPlayer.release();
			mMediaPlayer = null;
		}
		super.onDestroy();
	}

}
