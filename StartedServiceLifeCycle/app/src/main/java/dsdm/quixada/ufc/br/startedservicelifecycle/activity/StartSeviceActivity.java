package dsdm.quixada.ufc.br.startedservicelifecycle.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.widget.Button;

import dsdm.quixada.ufc.br.startedservicelifecycle.R;
import dsdm.quixada.ufc.br.startedservicelifecycle.services.MyService;

public class StartSeviceActivity extends AppCompatActivity {


    private static final String TAG = "serviceMusic";
    private Button btnStart;
    private Button btnStop;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_sevice_acevity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Intent intentService = new Intent(this, MyService.class);
        btnStart = (Button) findViewById(R.id.btn_Start);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "[StartSeviceActivity] Starting Service, btn Start.");
                startService(intentService);
            }
        });

       btnStop = (Button) findViewById(R.id.btn_Stop);
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Stopping Service., btn Stop");
                stopService(intentService);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_start_sevice_acevity, menu);
        return true;
    }

}
