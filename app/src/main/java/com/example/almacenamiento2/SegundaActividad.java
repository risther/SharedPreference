package com.example.almacenamiento2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

public class SegundaActividad extends AppCompatActivity {

    private static final String TAG = "SegundaActividad";
    private TextView mName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda_actividad);
        mName = (TextView) findViewById(R.id.etNombre_SP);

        SharedPreferences mPreferences =
                PreferenceManager.
                        getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor =
                mPreferences.edit();

        String name =
                mPreferences.getString
                        (getString(R.string.name), "");
        mName.setText(name);

    }

}