package dd_pc.service;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class ServiceActivity extends Activity implements View.OnClickListener {

    Button buttonStart, buttonStop;
  static public   TextView text1;
    static public   String text2;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);

        buttonStart = (Button) findViewById(R.id.buttonStart);
        buttonStop = (Button) findViewById(R.id.buttonStop);

        buttonStart.setOnClickListener(this);
        buttonStop.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonStart:
                startService(new Intent(this, ServiceActivityClass.class));
                Log.v(this.getClass().getName(), "onClick: Starting service.");
                break;
            case R.id.buttonStop:
                stopService(new Intent(this, ServiceActivityClass.class));
                Log.v(this.getClass().getName(), "onClick: Stopping service.");

                break;
        }
    }
}
