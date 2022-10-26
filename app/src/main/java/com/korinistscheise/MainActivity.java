package com.korinistscheise;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ardaBomber(View v) {

        ardaBomber.init(this);

        AlertDialog.Builder Alert = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.arda_bomber, null);
        Alert.setView(view);


        RadioGroup rg = (RadioGroup) view.findViewById(R.id.radio);
        rg.setOnCheckedChangeListener((group, checkedId) -> {
            switch(checkedId){
                case R.id.radio_1:
                    // do operations specific to this selection
                    break;
                case R.id.radio_2:
                    // do operations specific to this selection
                    break;
                case R.id.radio_3:
                    // do operations specific to this selection
                    break;
            }
        });


        AlertDialog alertDialog = Alert.create();
        alertDialog.show();
        alertDialog.getWindow().setBackgroundDrawable(null);
    }

}