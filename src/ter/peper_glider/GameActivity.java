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
import android.view.Display;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
 
public class GameActivity extends Activity
implements SensorEventListener {

    private MediaRecorder mRecorder = null;
    SensorManager sm = null;
	TextView tvGero;
	TextView tvAltitude;
	int altitude = 10;
	LayoutParams par;
	ImageView leaf;
	int widthView;
	int posCenter;
	int heightView;
	
	private Handler mHandler;
	private Runnable afficheDecibel = new Runnable () {
    	public void run(){
    		float decibel = (float) (20.0D * Math
	                .log10(mRecorder.getMaxAmplitude()));
			if(decibel > 90.0D && altitude > 0){
				altitude++;
			}else{
				altitude--;
			}
			par.height = par.height - (10 - altitude);
			par.width = par.width - (10 - altitude);
			leaf.invalidate();
			tvAltitude.setText(String.valueOf(altitude));
			mHandler.postDelayed(afficheDecibel, 1000);
    	}
    };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		sm = (SensorManager) getSystemService(SENSOR_SERVICE);
		setContentView(R.layout.activity_game);
		
		tvGero = (TextView) findViewById(R.id.tvGero);
		tvAltitude = (TextView) findViewById(R.id.tvAltitude);
		
		Display display = getWindowManager().getDefaultDisplay(); 
		int widthScreen = display.getWidth();  // deprecated height of game
		int heightScreen = display.getHeight();  // deprecated width of game

		leaf = (ImageView)findViewById(R.id.imageView1);

		heightView = leaf.getDrawable().getIntrinsicHeight();
		widthView = leaf.getDrawable().getIntrinsicWidth();
		tvGero.setText(heightView + " " + widthView);
		
		int centerWidth = widthScreen / 2;
		posCenter = centerWidth - (widthView/2);
		//tvGero.setText(widthScreen + " " + heightScreen);// w = 320 ; h = 480
		par = (LayoutParams)leaf.getLayoutParams();
		
		par.leftMargin = posCenter;
		par.topMargin = 30;

		leaf.setLayoutParams(par);
		leaf.invalidate();
		 
	    try {
	    	mRecorder = new MediaRecorder();
	        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		    mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
		    mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
		    mRecorder.setOutputFile("/dev/null");
			mRecorder.prepare();
			mRecorder.start();
		    
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
        	  //tvGero.setText("x: "+String.valueOf(direction_x)+" y: "+String.valueOf(direction_y)+" z : "+String.valueOf(direction_z));
        	  
        	  par = (LayoutParams)leaf.getLayoutParams();
        	  int pos = par.leftMargin;
        	  tvGero.setText(((Float)direction_z).intValue() + "toto : "+pos);
        	  
        	  if (pos<=(320-widthView) && pos>=0){
        		  tvGero.setText("bordure : "+pos);
        		  par.leftMargin =((Float)direction_z).intValue()+posCenter;
  				
  				
  				leaf.setLayoutParams(par);
  				leaf.invalidate();
        	  }
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
