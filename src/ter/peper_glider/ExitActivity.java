package ter.peper_glider;

import android.app.Activity;
import android.os.Bundle;

public class ExitActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		if (getIntent().getBooleanExtra("EXIT", false)) {
			System.exit(RESULT_OK);
			/*
			android.os.Process.killProcess(android.os.Process.myPid());
			super.onDestroy();*/
		}
	}
}
