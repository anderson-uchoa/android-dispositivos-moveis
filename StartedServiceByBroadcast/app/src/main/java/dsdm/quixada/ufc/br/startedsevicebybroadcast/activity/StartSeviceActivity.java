package dsdm.quixada.ufc.br.startedsevicebybroadcast.activity;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import dsdm.quixada.ufc.br.startedsevicebybroadcast.R;
import dsdm.quixada.ufc.br.startedsevicebybroadcast.service.MyService;
import dsdm.quixada.ufc.br.startedsevicebybroadcast.broadCastReceiver.ReceiverStartService;

/**
 * Created by andersonuchoa on 13/12/15.
 */

public class StartSeviceActivity extends AppCompatActivity {


    private static final String TAG = "StartSeviceActivity";
    private Button btnStop;
    BroadcastReceiver mReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_sevice_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Log.i(TAG, "Registrando BroadCast Receiver ");

        /*
        *  Registrando o BroadCast Receiver
        * */
        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        mReceiver = new ReceiverStartService();
        registerReceiver(mReceiver, filter);


        btnStop = (Button) findViewById(R.id.btn_Stop);

        /*
        *  Removida da pilha de activities ao clicar no botao Stop
        * */
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Stopping Service.");
                Intent intent = new Intent(getApplicationContext(), MyService.class);
                stopService(intent);


            }
        });


    }

       /*
        *  Removida da pilha de activities ao fechar a Activity
        * */

    @Override
    protected void onDestroy() {
        Log.i(TAG, "onDestroy Activity");
        Intent intent = new Intent(getApplicationContext(), MyService.class);
        stopService(intent);

        if (mReceiver != null)
            unregisterReceiver(mReceiver);
        super.onDestroy();


    }

}
