package com.baidu.bvideoviewsample1;

import com.baidu.bvideoviewsample1.R;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	private final String TAG = "MainActivity";
	private Button mPlayBtn;
	private EditText mSourceET;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		initUI();
	}

	void initUI() {
		mPlayBtn = (Button) findViewById(R.id.playBtn);
		mSourceET = (EditText) findViewById(R.id.getET);
		
		
		

		//################设置视频地址##############################
		/**---------------本地视频---------------**/
		//				mSourceET.setText("/storage/emulated/0/Download/video_test.mp4");//本地地址播放 支持mp4
		//						mSourceET.setText("/storage/emulated/0/Download/2.3gp");			//本地地址播放  支持3gp
		//		mSourceET.setText("/storage/emulated/0/Download/3.avi");// 可以播放声音.但是没有视频
		//		mSourceET.setText("/storage/emulated/0/Download/4.rmvb");// 可以播放声音.但是没有视频
		//		mSourceET.setText("/storage/emulated/0/Download/5.mkv");//

		//常规格式
		/**---------------网络视频---------------**/

		//		mSourceET.setText("http://192.168.1.100:8080/mp4.mp4");//网络地址 支持网络的mp4
		//		mSourceET.setText("http://192.168.1.100:8080/2.3gp");  //网络地址 支持网络的3gp
		//		mSourceET.setText("http://192.168.1.100:8080/flv.flv");//网络地址 支持网络的3gp
		//				mSourceET.setText("http://192.168.1.100:8080/3.avi");  //网络地址 支持网络的avi
		//		mSourceET.setText("http://192.168.1.100:8080/4.rmvb"); //网络地址 支持网络的rmvb
		//		mSourceET.setText("http://192.168.1.100:8080/5.mkv"); //网络地址 支持网络的mkv

		//对流媒体支持

		//		mSourceET.setText("mms://mediasrv2.iptv.xmg.com.cn/yinyue");//支持mms流媒体协议-->收音机	
		//		mSourceET.setText("http://live.3gv.ifeng.com/zixun.m3u8");//支持hls协议-->在线直播-->凤凰卫视
		//		mSourceET.setText("http://videofile2.cutv.com/mg/010002_t/2015/05/18/G15/G15fgfffhgjigoiimhiuy4_cug.mp4.m3u8");//
		mSourceET.setText("rtsp://218.204.223.237:554/live/1/66251FC11353191F/e7ooqwcfbqjoo80j.sdp");//支持rtsp协议-->实时流媒体传输协议-->监控
		//		mSourceET.setText("rtsp://xgrammyawardsx.is.livestream-api.com/livestreamiphone/grammyawards");//支持rtsp协议-->实时流媒体传输协议-->监控
		//		mSourceET.setText("http://m.livestream.com");//支持rtsp协议-->实时流媒体传输协议-->监控

		//http://dl76.80s.la:920/1406/筷子兄弟小苹果/筷子兄弟小苹果.mp4

		//################设置视频地址##############################

		//		mSourceET.setText("http://bcs.duapp.com/dlna-sample/out_MP4_AVC_AAC_320x240_2013761628.mp4?sign=MBO:C09e40adc8851224375a26cf2c6d12a0:7zwy3HtoM%2B5hXB2%2FlJFN6OkWFCs%3D");
		//		mSourceET.setText("http://pl.youku.com/playlist/m3u8?ts=1409991730&keyframe=0&vid=XNzE4Njc2Mzc2&type=mp4&ep=cSaUE0CEVMgD5ybYiT8bZiriJnJaXJZ0vGqB%2F4gxAsZuG%2BrQkTfYwA%3D%3D&sid=340999263257112160b5e&token=8997&ctype=12&ev=1&oip=3079203019");
		//		mSourceET.setText("http://live.3gv.ifeng.com/zixun.m3u8");
		//		mSourceET.setText("rtsp://218.204.223.237:554/live/1/66251FC11353191F/e7ooqwcfbqjoo80j.sdp");

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		mPlayBtn.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int id = v.getId();
		switch (id) {
		case R.id.playBtn:
			/**
			 * 播放一个视频
			 */
			playVideo();
			break;
		default:
			break;
		}
	}

	private void playVideo() {
		String source = mSourceET.getText().toString();
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
