package dsdm.quixada.ufc.br.startedservicebybroadcast.broadCastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import dsdm.quixada.ufc.br.startedservicebybroadcast.service.MyService;

/**
 * Created by andersonuchoa on 13/12/15.
 */
public class ReceiverStartService extends BroadcastReceiver {

    public static final String TAG = "ReceiverStartService";

   public ReceiverStartService(){}


    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)){
            Log.i(TAG, ".ACTION_BOOT_COMPLETED");
        }else if( intent.getAction().equals(Intent.ACTION_SCREEN_ON)){
            Log.i(TAG, ".ACTION_SCREEN_ON");
        }else{
            Log.i(TAG, intent.getAction());
            return;
        }
        Log.i(TAG, "Acionando Serviço");

        Intent i = new Intent(context, MyService.class);
        context.startService(i);

    }
}
