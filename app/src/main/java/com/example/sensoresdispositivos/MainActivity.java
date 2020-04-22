package com.example.sensoresdispositivos;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private SensorManager sensorManager;
    private ManejaEvento maneja;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }

    private class ManejaEvento implements SensorEventListener{

        @Override
        public void onSensorChanged(SensorEvent event) {

            Toast.makeText(getApplicationContext(),event.sensor.getName()+" "+event.values[0],Toast.LENGTH_LONG).show();

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        Sensor uno=null;
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);

        List<Sensor> listaSensores = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);
        maneja=new ManejaEvento();

        if(listaSensores.size()>0) {
            uno = listaSensores.get(0);
            sensorManager.registerListener(maneja,uno,SensorManager.SENSOR_DELAY_GAME);
        }

        Toast.makeText(this,uno.getName(),Toast.LENGTH_LONG).show();
        Log.d("sensor",uno.getName());
    }

    @Override
    protected void onStop() {
        super.onStop();
        sensorManager.unregisterListener(maneja);
    }
}
