package dsdm.quixada.ufc.br.startedsevicebybroadcast.service;

import android.app.Service;
import android.content.Intent;

import android.os.IBinder;
import android.util.Log;

import dsdm.quixada.ufc.br.startedsevicebybroadcast.R;


/**
 * Created by andersonuchoa on 06/12/15.
 */
public class MyService extends Service implements Runnable{




    private static final String TAG = "MyService";
    public boolean running = false;


    public MyService(){}


    @Override
    public void onCreate() {
        Log.i(TAG, " onCreate() - Serviço Criado ");
        super.onCreate();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand() - Executando Serviço");

        if(!this.running) {
            this.running = true;
            new Thread(this).start();
        }

        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public IBinder onBind(Intent intent){
    Log.i(TAG, "onBind");
        return null;
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy() - Parando Serviço");
        running = false;
        super.onDestroy();
    }


    @Override
    public void run() {
        int i = 0;
        while (running) {
            Log.i(TAG, "MyService is running!!!");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
            }
        }
        running = false;
    }


}
