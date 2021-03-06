package ter.peper_glider;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView titreApp, legendApp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button bPlay = (Button) findViewById(R.id.buttonPlay);
		bPlay.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
		    	Intent myIntent = new Intent(view.getContext(), GameActivity.class);
		    	startActivity(myIntent);
            }
        });
		
	
		Button bHighScore = (Button) findViewById(R.id.buttonHighScore);
		bHighScore.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
		    	Intent myIntent = new Intent(view.getContext(), ScoreActivity.class);
		    	startActivity(myIntent);	    	
            }
        });
		
		
		Button bHelp = (Button) findViewById(R.id.buttonHelp);
		bHelp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	Intent myIntent = new Intent(view.getContext(), HelpActivity.class);
		    	startActivity(myIntent);	    	
            }
        });
	}

	public void methodeExit(View v) {
		System.exit(RESULT_OK);		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
			case R.id.item1:
				System.exit(RESULT_OK);
		}
		return true;
	}
}
