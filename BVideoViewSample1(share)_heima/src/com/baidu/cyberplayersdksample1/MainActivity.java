package com.baidu.cyberplayersdksample1;

import com.baidu.cyberplayer.sdk.BCyberPlayerFactory;
import com.baidu.cyberplayer.sdk.BEngineManager;
import com.baidu.cyberplayer.sdk.BEngineManager.OnEngineListener;
import com.baidu.cyberplayersdksample1.R;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {
	private final String TAG = "MainActivity";
	private Button mInstallBtn;
	private Button mPlayBtn;
	private TextView mInfoTV;
	private EditText mSourceET;

	private final int UPDATE_INFO = 0;

	//您的ak
	private String AK = "GsuXf8CSLerYpSh1Smksnitt";
	//您的sk的前16位
	private String SK = "zssOn8r43S1cya7X";

	//返回值对应的含义
	String[] mRetInfo = new String[] { "RET_NEW_PACKAGE_INSTALLED", "RET_NO_NEW_PACKAGE", "RET_STOPPED",
			"RET_CANCELED", "RET_FAILED_STORAGE_IO", "RET_FAILED_NETWORK", "RET_FAILED_ALREADY_RUNNING",
			"RET_FAILED_OTHERS", "RET_FAILED_ALREADY_INSTALLED", "RET_FAILED_INVALID_APK" };

	Handler mUIHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case UPDATE_INFO:
				mInfoTV.setText((String) msg.obj);
				break;
			default:
				break;
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//初始化BCyberPlayerFactory, 在其他任何接口调用前需要先对BCyberPlayerFactory进行初始化
		BCyberPlayerFactory.init(this);

		setContentView(R.layout.activity_main);

		initUI();
	}

	void initUI() {
		mInstallBtn = (Button) findViewById(R.id.installBtn);
		mPlayBtn = (Button) findViewById(R.id.playBtn);
		mInfoTV = (TextView) findViewById(R.id.infoTV);
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

		mInstallBtn.setOnClickListener(this);
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
		case R.id.installBtn:
			//检测engine是否安装,如果没安装先安装engine
			checkEngineInstalled();
			break;
		case R.id.playBtn:
			//播放一个视频
			playVideo();
			break;
		default:
			break;
		}
	}

	private void playVideo() {
		//如果engine没安装,进行提示
		if (!isEngineInstalled()) {
			setInfo("CyberPlayerEngine not installed,\n please install it first");
		} else {//如果已经安装
			String source = mSourceET.getText().toString();
			if (source == null || source.equals("")) {
				//简单检测播放源的合法性,不合法不播放
				setInfo("Please input a valid video path");
			} else {
				//播放前需要用ak,sk来初始化BEngineManager, 播放的时候会对ak,sk进行权限认证
				//当前您也可以到VideoViewPlayingActivity进行初始化
				BEngineManager mgr = BCyberPlayerFactory.createEngineManager();
				mgr.initCyberPlayerEngine(AK, SK);

				Intent intent = new Intent(this, VideoViewPlayingActivity.class);
				intent.setData(Uri.parse(source));
				startActivity(intent);
			}
		}
	}

	/**
	 * 检测engine是否安装
	 * @return
	 */
	private boolean isEngineInstalled() {
		BEngineManager mgr = BCyberPlayerFactory.createEngineManager();
		return mgr.EngineInstalled();
	}

	/**
	 * 下载并安装engine
	 */
	private void installEngine() {
		BEngineManager mgr = BCyberPlayerFactory.createEngineManager();
		mgr.installAsync(mEngineListener);
	}

	/**
	 * 检测engine是否安装,如果没有安装需要安装engine
	 */
	private void checkEngineInstalled() {
		if (isEngineInstalled()) {
			setInfo("CyberPlayerEngine Installed.\n");
		} else {
			//安装engine
			installEngine();
		}
	}

	private OnEngineListener mEngineListener = new OnEngineListener() {
		String info = "";

		String dlhead = "install engine: onDownload   ";
		String dlbody = "";

		@Override
		public boolean onPrepare() {
			// TODO Auto-generated method stub
			info = "install engine: onPrepare.\n";
			setInfo(info);
			return true;
		}

		@Override
		public int onDownload(int total, int current) {
			// TODO Auto-generated method stub
			if (dlhead != null) {
				info += dlhead;
				dlhead = null;
			}
			dlbody = current + "/" + total;
			setInfo(info + dlbody + "\n");
			return DOWNLOAD_CONTINUE;
		}

		@Override
		public int onPreInstall() {
			// TODO Auto-generated method stub
			info += dlbody;
			info += "\n";
			info += "install engine: onPreInstall.\n";
			setInfo(info);

			return DOWNLOAD_CONTINUE;
		}

		@Override
		public void onInstalled(int result) {
			// TODO Auto-generated method stub
			info += "install engine: onInstalled, ret = " + mRetInfo[result] + "\n";
			setInfo(info);
			if (result == OnEngineListener.RET_NEW_PACKAGE_INSTALLED) {
				//安装完成
			}
		}
	};

	private void setInfo(String info) {
		Message msg = new Message();
		msg.what = UPDATE_INFO;
		msg.obj = info;
		mUIHandler.sendMessage(msg);
	}

}
