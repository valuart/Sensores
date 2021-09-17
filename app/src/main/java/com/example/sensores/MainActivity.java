package com.example.sensores;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
 private TextView tvMostrar;
 private SensorManager sensorManager;
 private LeeSensor leeSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvMostrar=findViewById(R.id.tvMostrar);

        //obtrener lista de sensores disponibles
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        leeSensor= new LeeSensor();
        List<Sensor> listaSensores = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);

        if(listaSensores.size()>0) {
            sensorManager.registerListener(leeSensor, listaSensores.get(0), SensorManager.SENSOR_DELAY_GAME);

        } else {
            tvMostrar.setText("El Acelerómetro no está disponible");
        }


        /*StringBuilder mostrar=new StringBuilder();
        for(Sensor sensor:listaSensores){
            mostrar.append(sensor.getName()+" "+sensor.getVendor()+" "+sensor.getType()+ "\n");

        }
        tvMostrar.setText(mostrar.toString());*/

    }

    private class LeeSensor implements SensorEventListener{

        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            StringBuilder muestra =new StringBuilder();
            muestra.append("x "+sensorEvent.values[0]+"\n"+"y "+sensorEvent.values[1]+"\n z "+sensorEvent.values[2]);
            tvMostrar.setText(muestra.toString());
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        sensorManager.unregisterListener(leeSensor);
    }
}