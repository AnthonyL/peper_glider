package ter.peper_glider;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class HelpActivity  extends Activity{
 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_help);
		
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
        return true;
    }
}
