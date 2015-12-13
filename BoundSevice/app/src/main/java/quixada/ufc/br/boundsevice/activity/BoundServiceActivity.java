package quixada.ufc.br.boundsevice.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import quixada.ufc.br.boundsevice.R;
import quixada.ufc.br.boundsevice.service.CalculadoraService;

/**
 * Created by andersonuchoa on 13/12/15.
 */

public class BoundServiceActivity extends AppCompatActivity {


    private final String TAG = "BoundServiceActivity";
    private CalculadoraService calculadoraService;
    private  boolean idBound = false;

    private EditText etNumero1, etNumero2;
    private Button btnSoma, btnSubtracao, btnDivisao, btnMultiplicacao;
    private TextView textViewResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bound_service_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Log.i(TAG, "onCreate()");

        textViewResultado = (TextView) findViewById(R.id.textView_resultado);
        etNumero1 = (EditText) findViewById(R.id.input_numero1);
        etNumero2 = (EditText) findViewById(R.id.input_numero2);

        btnSoma = (Button) findViewById(R.id.btn_soma);
        btnSubtracao = (Button) findViewById(R.id.btn_subtracao);
        btnDivisao = (Button) findViewById(R.id.btn_divisao);
        btnMultiplicacao = (Button) findViewById(R.id.btn_multiplicacao);


        btnSoma.setOnClickListener(calcularOperacao);
        btnDivisao.setOnClickListener(calcularOperacao);
        btnMultiplicacao.setOnClickListener(calcularOperacao);
        btnSubtracao.setOnClickListener(calcularOperacao);
    }



    private boolean validarCampos() {

        boolean validado = true;
        String numero1 = etNumero1.getText().toString();
        String numero2 = etNumero2.getText().toString();


        if (numero1.isEmpty()){
            etNumero1.setError("O campo deve ser preenchido!");
            validado = false;
        }else {
            etNumero1.setError(null);
        }
        if (numero2.isEmpty()){
            etNumero2.setError("O campo deve ser preenchido!");
            validado = false;
        }else {
            etNumero2.setError(null);
        }

        return validado;
    }


    private  View.OnClickListener calcularOperacao = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            if(!validarCampos()){
                return;
            }

            double numero1;
            double numero2;

            numero1 = Double.parseDouble(etNumero1.getText().toString().trim());
            numero2 = Double.parseDouble(etNumero2.getText().toString().trim());


            double resultado = 0;

            switch (v.getId()){
                case R.id.btn_soma :
                    resultado = calculadoraService.soma(numero1,numero2);
                    break;
                case R.id.btn_subtracao :
                    resultado = calculadoraService.subtracao(numero1,numero2);
                    break;
                case R.id.btn_multiplicacao :
                    resultado = calculadoraService.multiplicacao(numero1,numero2);
                    break;
                case R.id.btn_divisao :
                        resultado = calculadoraService.divisao(numero1, numero2);
                    break;
            }

            textViewResultado.setText(resultado + "");


        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
        if(!idBound) {
            Intent intent = new Intent(this, CalculadoraService.class);
            bindService(intent, connection, Context.BIND_AUTO_CREATE);
        }

    }


    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
        Log.i(TAG, "Desfazendo ligação service");
        if(idBound){
            unbindService(connection);
            idBound = false;
        }

    }

    private ServiceConnection connection = new ServiceConnection() {

        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            Log.i(TAG, "Service Connected");
            CalculadoraService.LocalBinder binder = (CalculadoraService.LocalBinder) service;
            calculadoraService = binder.getService();
            idBound = true;
        }

        public void onServiceDisconnected(ComponentName arg0) {
            Log.i(TAG, "Service Disconnected");
            idBound = false;
        }
    };

}
