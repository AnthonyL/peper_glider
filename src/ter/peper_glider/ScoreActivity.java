package ter.peper_glider;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class ScoreActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_score);

		Button bPlay = (Button) findViewById(R.id.buttonPlay);
		bPlay.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
		    	Intent myIntent = new Intent(view.getContext(), GameActivity.class);
            	myIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		    	startActivity(myIntent);
            }
        });
		
		Button buttonReturn = (Button) findViewById(R.id.buttonReturn);
		buttonReturn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
		    	Intent myIntent = new Intent(view.getContext(), MainActivity.class);
            	myIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		    	startActivity(myIntent);
            }
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}
