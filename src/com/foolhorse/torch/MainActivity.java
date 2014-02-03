package com.foolhorse.torch;

import android.app.Activity;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.view.Menu;

public class MainActivity extends Activity {

	Parameters parameter;
	Camera camera;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// 设置背景，需呀设计
		setContentView(R.layout.activity_main);

		try {
			camera = Camera.open();
			parameter = camera.getParameters();
			
			parameter.setFlashMode(Parameters.FLASH_MODE_TORCH);
			
			camera.setParameters(parameter);
			camera.startPreview();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		this.finish();
		try {
			parameter = camera.getParameters();
			parameter.setFlashMode(Parameters.FLASH_MODE_OFF);
			camera.setParameters(parameter);

			camera.stopPreview();
			camera.release();
			camera = null;
		} 
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
