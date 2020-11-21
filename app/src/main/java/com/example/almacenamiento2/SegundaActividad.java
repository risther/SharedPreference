package com.example.almacenamiento2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import yuku.ambilwarna.AmbilWarnaDialog;

public class SegundaActividad extends AppCompatActivity {

    private static final String TAG = "SegundaActividad";
    private TextView mName;
    private int mSize;
    private int mColor;
    private int mFont;

    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;

    private SeekBar sbFontSize;
    private TextView tvTestText;
    private TextView tvFontSize;
    private Button btnChangeColorApp;
    private ConstraintLayout clPrincipal;
    private Spinner spnFont;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda_actividad);
        mName = (TextView) findViewById(R.id.etNombre_SP);
        sbFontSize = (SeekBar)findViewById(R.id.sbSizeFont);
        tvTestText = (TextView)findViewById(R.id.tvTestText);
        tvFontSize = (TextView)findViewById(R.id.tvFontSize);
        btnChangeColorApp = (Button)findViewById(R.id.btnChangeColorApp);
        clPrincipal = (ConstraintLayout)findViewById(R.id.clPrincipal);
        spnFont = (Spinner)findViewById(R.id.spnFont);

        mColor = ContextCompat.getColor(SegundaActividad.this,R.color.colorPrimary);

        initializeButton(btnChangeColorApp);
        initializeSpinnerFont(spnFont);
        initializeSeekBar(sbFontSize);

        mPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        mEditor = mPreferences.edit();

        reviewSharedPreference();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mEditor.putInt(getString(R.string.size_font),mSize);
        mEditor.putInt(getString(R.string.color),mColor);
        mEditor.putInt(getString(R.string.font),mFont);
        mEditor.commit();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mEditor.putInt(getString(R.string.size_font),mSize);
        mEditor.putInt(getString(R.string.color),mColor);
        mEditor.putInt(getString(R.string.font),mFont);
        mEditor.commit();
    }

    private void reviewSharedPreference() {
        mSize = mPreferences.getInt(getString(R.string.size_font),(sbFontSize.getProgress()+10)*3);
        mColor = mPreferences.getInt(getString(R.string.color),-1);
        mFont = mPreferences.getInt(getString(R.string.font),0);

        initializeFontSize(mSize);
        clPrincipal.setBackgroundColor(mColor);
        spnFont.setSelection(mFont);
    }

    private void initializeSpinnerFont(Spinner spnFont) {
        this.spnFont.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mFont = position;
                setFont(mFont);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setFont(int position) {
        String pathFont="fonts/";
        switch(position){
            case 0:
                pathFont += "android.ttf";
                break;
            case 1:
                pathFont += "astro_armada.ttf";
                break;
            case 2:
                pathFont += "cartoon_fun.ttf";
                break;
            case 3:
                pathFont += "feeling_good.ttf";
                break;
            case 4:
                pathFont += "high_speed.ttf";
                break;
            case 5:
                pathFont += "star_jedi.ttf";
                break;
        }
        tvTestText.setTypeface(Typeface.createFromAsset(getAssets(),pathFont));
    }

    private void initializeButton(Button btnChangeColorApp) {
        this.btnChangeColorApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openColorPicker();
            }
        });
    }

    private void openColorPicker() {
        AmbilWarnaDialog ambilWarnaDialog = new AmbilWarnaDialog(this, mColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {

            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                mColor = color;
                clPrincipal.setBackgroundColor(mColor);
            }
        });
        ambilWarnaDialog.show();
    }

    private void initializeSeekBar(SeekBar sbFontSize) {
        this.sbFontSize.setProgress(this.sbFontSize.getMax()/2);
        this.sbFontSize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mSize = (progress+10)*3;
                tvFontSize.setText(getResources().getString(R.string.font_size) + mSize);
                tvTestText.setTextSize(mSize);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void initializeFontSize(int size) {
        sbFontSize.setProgress((size/3)-10);
        tvFontSize.setText(getResources().getString(R.string.font_size) + size);
        tvTestText.setTextSize(size);
        tvTestText.getTypeface();
    }

}