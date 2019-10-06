package com.example.ch13_practice_13_32_5;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import static java.lang.String.valueOf;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, TextWatcher {
    RadioGroup unit;
    EditText value;
    TextView degF;
    TextView degC;
    TextView degK;
    String key1;
    String key2;
    String key3;
    int checkId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unit=findViewById(R.id.unit);
        unit.setOnCheckedChangeListener(this);

        value=findViewById(R.id.value);
        value.addTextChangedListener(this);

        degF=findViewById(R.id.degF);
        degC=findViewById(R.id.degC);
        degK=findViewById(R.id.degK);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
    calc();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
    calc();
    }

    private void calc() {
        double val,f,c,g;
        String str = value.getText().toString();
        try {
            val =Double.parseDouble(str);
        } catch (Exception e){
            val=0;
        }
        if(unit.getCheckedRadioButtonId()==R.id.unitF){
            f=val;
            c=(f-32)*5/9;
            g=c+273.15;
        }
        else if(unit.getCheckedRadioButtonId()==R.id.unitC){
            c=val;
            f=c*9/5+32;
            g=c+273.15;
        }
        else{
            g=val;
            c=g-273.15;
            f=c*9/5+32;
        }

        degC.setText(String.format("%.1f",c)+getResources().getString(R.string.charC));
        degF.setText(String.format("%.1f",f)+getResources().getString(R.string.charF));
        degK.setText(String.format("%.2fK",g));

        key1=degC.toString();
        key2=degF.toString();
        key3=degK.toString();
        //checkId= unit.getCheckedRadioButtonId();
    }

    @Override
    public void onPause(){
        super.onPause();
        SharedPreferences.Editor editor=getPreferences(MODE_PRIVATE).edit();

        editor.putString("AAA",key1);
        editor.putString("紀錄2",key2);
        editor.putString("紀錄3",key3);
        //editor.putInt("紀錄4", checkId);
        editor.commit();
    }

    @Override
    public void onResume(){
        super.onResume();
        SharedPreferences showme=getPreferences(MODE_PRIVATE);
        key1=showme.getString("AAA","還敢");
        key2=showme.getString("紀錄2","下來啊");
        key3=showme.getString("紀錄3","冰鳥");
        //checkId=showme.getInt("紀錄4",0);

        degC.setText(key1);//+" "+getResources().getString(R.string.charC));
        degF.setText(key2);//+" "+getResources().getString(R.string.charF));
        degK.setText(key3);
        //unit.;

    }
}
