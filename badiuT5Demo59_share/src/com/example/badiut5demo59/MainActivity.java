package com.example.badiut5demo59;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.baidu.cyberplayer.sdk.BCyberPlayerFactory;
import com.baidu.cyberplayer.sdk.BEngineManager;
import com.baidu.cyberplayer.sdk.BEngineManager.OnEngineListener;

public class MainActivity extends Activity {
	//AK,SK需要我们注册百度的开发者.并且添加应用
	private String AK = "GsuXf8CSLerYpSh1Smksnitt";
	private String SK = "zssOn8r43S1cya7X";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//初始化BCyberPlayerFactory, 在其他任何接口调用前需要先对BCyberPlayerFactory进行初始化
		BCyberPlayerFactory.init(this);
		setContentView(R.layout.activity_main);

		//检测engine是否安装,如果没安装先安装engine
		checkEngineInstalled();
	}

	public void click(View v) {
//		String path = "rtsp://218.204.223.237:554/live/1/66251FC11353191F/e7ooqwcfbqjoo80j.sdp";
		String path = "http://192.168.1.100:8080/4.rmvb";
		playVideo(path);
	}

	/**
	 * 具体的播放操作
	 */
	private void playVideo(String path) {
		//如果engine没安装,进行提示
		if (!isEngineInstalled()) {
			setInfo("CyberPlayerEngine not installed,\n please install it first");
		} else {//如果已经安装
			String source = path;
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

	/**
	 * 下载并安装engine
	 */
	private void installEngine() {
		BEngineManager mgr = BCyberPlayerFactory.createEngineManager();
		mgr.installAsync(mEngineListener);
	}

	/**
	 * 检测engine是否安装
	 * @return
	 */
	private boolean isEngineInstalled() {
		BEngineManager mgr = BCyberPlayerFactory.createEngineManager();
		return mgr.EngineInstalled();
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

	public void setInfo(String str) {
		Toast.makeText(getApplicationContext(), str, 0).show();
	}

	//返回值对应的含义
	String[] mRetInfo = new String[] { "RET_NEW_PACKAGE_INSTALLED", "RET_NO_NEW_PACKAGE", "RET_STOPPED",
			"RET_CANCELED", "RET_FAILED_STORAGE_IO", "RET_FAILED_NETWORK", "RET_FAILED_ALREADY_RUNNING",
			"RET_FAILED_OTHERS", "RET_FAILED_ALREADY_INSTALLED", "RET_FAILED_INVALID_APK" };
}
