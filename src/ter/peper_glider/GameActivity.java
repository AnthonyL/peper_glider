package ter.peper_glider;
 
import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;
 
public class GameActivity extends Activity
implements SensorEventListener {

	SensorManager sm = null;
	TextView tvGero;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		sm = (SensorManager) getSystemService(SENSOR_SERVICE);
		setContentView(R.layout.activity_game);
		tvGero = (TextView) findViewById(R.id.tvGero);
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
}
