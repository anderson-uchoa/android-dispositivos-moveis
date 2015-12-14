package quixada.ufc.br.boundservice.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by andersonuchoa on 13/12/15.
 */
public class CalculadoraService  extends Service{

    private static final String TAG = "CalculadoraService";
    private final IBinder iBinder = new LocalBinder();


    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind");
        return iBinder;
    }

    public class LocalBinder extends Binder{

         public CalculadoraService getService(){
             return CalculadoraService.this;

         }

    }


    public double soma (double numero1, double numero2){
        return  numero1 + numero2;
    }

    public double subtracao (double numero1, double numero2){
        return  numero1 - numero2;

    }

    public double multiplicacao (double numero1, double numero2){
        return  numero1 * numero2;

    }

    public double divisao (double numero1, double numero2){
        return  numero1/numero2;

    }

}
