package com.example.vitamiodemo01;

import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.MediaPlayer.OnPreparedListener;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends Activity {

	private VideoView vvv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if (!LibsChecker.checkVitamioLibs(this))
			return;
		init();
	}

	private void init() {
		vvv = (VideoView) findViewById(R.id.vvv);
		System.out.println(vvv == null);
		vvv.setMediaController(new MediaController(this));
		/**
		 * //1.flv
			//2.3gp
			3.avi
			//4.rmvb
			//5.mkv
		 */
		
		
		
		
		
		
		
		
		
		//################设置视频地址##############################
		/**---------------本地视频---------------**/
//		vvv.setVideoPath("/storage/emulated/0/Download/video_test.mp4");//本地地址播放 支持mp4
//				vvv.setVideoPath("/storage/emulated/0/Download/2.3gp");			//本地地址播放  支持3gp
//				vvv.setVideoPath("/storage/emulated/0/Download/3.avi");// 可以播放声音.但是没有视频
//				vvv.setVideoPath("/storage/emulated/0/Download/4.rmvb");// 可以播放声音.但是没有视频
				vvv.setVideoPath("/storage/emulated/0/Download/5.mkv");//

		//常规格式
		/**---------------网络视频---------------**/

		//		vvv.setVideoPath("http://192.168.1.100:8080/mp4.mp4");//网络地址 支持网络的mp4
		//		vvv.setVideoPath("http://192.168.1.100:8080/2.3gp");  //网络地址 支持网络的3gp
		//				vvv.setVideoPath("http://192.168.1.100:8080/flv.flv");//网络地址 支持网络的3gp
		//				vvv.setVideoPath("http://192.168.1.100:8080/3.avi");  //网络地址 支持网络的avi
		//				vvv.setVideoPath("http://192.168.1.100:8080/4.rmvb"); //网络地址 支持网络的rmvb
		//		vvv.setVideoPath("http://192.168.1.100:8080/5.mkv"); //网络地址 支持网络的mkv

		//对流媒体支持
				
//				vvv.setVideoPath("mms://mediasrv2.iptv.xmg.com.cn/yinyue");//支持mms流媒体协议-->收音机	
//				vvv.setVideoPath("http://live.3gv.ifeng.com/zixun.m3u8");//支持hls协议-->在线直播-->凤凰卫视
				vvv.setVideoPath("rtsp://218.204.223.237:554/live/1/66251FC11353191F/e7ooqwcfbqjoo80j.sdp");//支持rtsp协议-->实时流媒体传输协议-->监控
		//		vvv.setVideoPath("rtsp://xgrammyawardsx.is.livestream-api.com/livestreamiphone/grammyawards");//支持rtsp协议-->实时流媒体传输协议-->监控
		//		vvv.setVideoPath("http://m.livestream.com");//支持rtsp协议-->实时流媒体传输协议-->监控

		//http://dl76.80s.la:920/1406/筷子兄弟小苹果/筷子兄弟小苹果.mp4
		
		
		//################设置视频地址##############################
		vvv.start();
		vvv.setOnPreparedListener(new OnPreparedListener() {

			@Override
			public void onPrepared(MediaPlayer mp) {
				Toast.makeText(getApplicationContext(), "准备好了", 0).show();
			}
		});

		//vvv.setVideoURI(Uri.parse("http://f5.r.56.com/f5.c186.56.com/flvdownload/0/2/sc_141249584035hd_clear.flv?v=1&t=W1ho-pj4TBZ04fA_snju0w&r=5745&e=1414389696&tt=379&sz=26349090&vid=128255495"));
		//		vvv.setVideoURI(Uri.parse("http://www.56.com/u82/v_MTI4MjU1NDk1.html?ptag=vsogou"));
		//vvv.setVideoURI(Uri.parse("http://live.3gv.ifeng.com/zixun.m3u8"));
		//vvv.setMediaController(new MediaController(this));
		/*vvv.requestFocus();
		vvv.setOnPreparedListener(new OnPreparedListener() {		
			@Override
			public void onPrepared(MediaPlayer mp) {
				//vvv.start(); 
				//mp.start();
				
			}
		});*/

	}

}
