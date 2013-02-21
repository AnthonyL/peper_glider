package ter.peper_glider;

import java.io.IOException;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class GameActivity extends Activity
implements SensorEventListener {
	
	static final private double EMA_FILTER = 0.6;
    private MediaRecorder mRecorder = null;
    private double mEMA = 0.0;
    
	SensorManager sm = null;
	TextView tvGero;
	private Handler mHandler;
	private Runnable afficheDecibel = new Runnable () {
    	public void run(){
    		float decibel = (float) (20.0D * Math
	                .log10(mRecorder.getMaxAmplitude()));
			
			Log.i("logMarker", "decibel " + decibel);
			mHandler.postDelayed(afficheDecibel, 1000);
    	}
    };
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		sm = (SensorManager) getSystemService(SENSOR_SERVICE);
		setContentView(R.layout.activity_game);
		tvGero = (TextView) findViewById(R.id.tvGero);
		
		 
	    try {
	    	mRecorder = new MediaRecorder();
	        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		    mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
		    mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
		    mRecorder.setOutputFile("/dev/null");
			mRecorder.prepare();
			mRecorder.start();
		    mEMA = 0.0;
		    
		    mHandler = new Handler();
	        mHandler.postDelayed(afficheDecibel, 1000);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		int sensor = event.sensor.getType();
		float [] values = event.values;
		
        synchronized (this) {
          if (sensor == Sensor.TYPE_ORIENTATION) {
        	  float direction_x = values[0];
        	  float direction_y = values[1];
        	  float direction_z = values[2];
        	  tvGero.setText("x: "+String.valueOf(direction_x)+" y: "+String.valueOf(direction_y)+" z : "+String.valueOf(direction_z));
          }
        }
	}
	
	 protected void onResume() {
	        super.onResume();
	        sm.registerListener(this, sm.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_NORMAL);
	}

	protected void onStop() {
	    sm.unregisterListener(this, sm.getDefaultSensor(Sensor.TYPE_ORIENTATION));
	    super.onStop();
	}
	
	public double getAmplitude() {
	        if (mRecorder != null)
	                return  (mRecorder.getMaxAmplitude()/2700.0);
	        else
	                return 0;
	
	}

}
