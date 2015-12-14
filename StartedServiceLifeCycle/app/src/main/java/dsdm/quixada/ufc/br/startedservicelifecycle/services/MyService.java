package dsdm.quixada.ufc.br.startedservicelifecycle.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by andersonuchoa on 06/12/15.
 */
public class MyService extends Service  implements Runnable{


    private static final String TAG = "MyService";
    private boolean running = false;
   // private  MediaPlayer player;
    
    @Override
    public void onCreate() {
        Log.i(TAG, " onCreate() - Serviço Criado ");
      //  player = MediaPlayer.create(this,R.raw.music);
       // player.setLooping(false);
        super.onCreate();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "My Service Started", Toast.LENGTH_LONG).show();
        Log.i(TAG, "onStartCommand() - Executando Serviço");
        this.running = true;
        new Thread(this).start();

        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind");
        return null;
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy() - Parando Serviço");
        Toast.makeText(this, "My Service Stopped", Toast.LENGTH_LONG).show();
        this.running = false;
        //player.stop();
        super.onDestroy();
    }


    @Override
    public void run() {
        int i = 0;
        while (running && i <= 10) {
            Log.i("("+ i++ +") MyService", "MyService is running!!!");
            try {
                //player.start();
                Thread.sleep(5000);
            } catch (InterruptedException e) {
            }}
        running = false;

        }



}
