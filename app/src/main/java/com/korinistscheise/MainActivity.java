package com.korinistscheise;

import static com.korinistscheise.ardaBomber.getIDbyName;
import static com.korinistscheise.ardaBomber.pref;
import static com.korinistscheise.ardaBomber.save;
import static java.lang.Integer.parseInt;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ardaBomber(View v) {

        ardaBomber ardaBomber = new ardaBomber(this);

        AlertDialog.Builder Alert = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.arda_bomber, null);
        Alert.setView(view);

        AtomicInteger randomizer = new AtomicInteger(parseInt(pref.getString("bomber_randomizer", "0")));
        String bomber_text = pref.getString("bomber_text", "BOOM!");
        String bomber_count = pref.getString("bomber_count", "5");

        String bak_rand = pref.getString("bomber_randomizer", "0");
        String bak_text = pref.getString("bomber_text", "BOOM!");
        String bak_count = pref.getString("bomber_count", "5");

        RadioGroup rg = view.findViewById(getIDbyName(this, "radio"));
        EditText count = view.findViewById(getIDbyName(this, "bomber_count"));
        EditText text = view.findViewById(getIDbyName(this, "bomber_text"));

        text.setText(bomber_text);
        count.setText(bomber_count);
        if (randomizer.get() == 1) rg.check(getIDbyName(this, "radio_1"));
        if (randomizer.get() == 2) rg.check(getIDbyName(this, "radio_2"));

        rg.setOnCheckedChangeListener((group, checkedId) -> {

            if (checkedId == getIDbyName(this, "radio_0")) randomizer.set(0);
            else if (checkedId == getIDbyName(this, "radio_1")) randomizer.set(1);
            else if (checkedId == getIDbyName(this, "radio_2")) randomizer.set(2);
            save(String.valueOf(randomizer), "randomizer");
        });

        text.addTextChangedListener(TextWatcher("text"));
        count.addTextChangedListener(TextWatcher("count"));

        AlertDialog alertDialog = Alert.create();
        alertDialog.show();
        alertDialog.getWindow().setBackgroundDrawable(null);

        alertDialog.setOnCancelListener(dialog -> {
            save(bak_rand, "randomizer");
            save(bak_count, "count");
            save(bak_text, "text");
        });

        view.findViewById(getIDbyName(this, "arda_cancel")).setOnClickListener(v1 -> {
            save(bak_rand, "randomizer");
            save(bak_count, "count");
            save(bak_text, "text");
            alertDialog.dismiss();
        });

        view.findViewById(getIDbyName(this, "arda_save")).setOnClickListener(v1 -> {
            save(String.valueOf(randomizer.get()), "randomizer");
            save(count.getEditableText().toString(), "count");
            save(text.getEditableText().toString(), "text");
            alertDialog.dismiss();
        });

        view.findViewById(getIDbyName(this, "arda_send")).setOnClickListener(v1 -> {
            ardaBomber.init(this, text.getEditableText().toString(), parseInt(count.getEditableText().toString()), randomizer.get());
            ardaBomber.execute();
            alertDialog.dismiss();
        });
    }

    public void sendMsg(String msg) {
        Log.e("caused    testtest", msg);
    }

    TextWatcher TextWatcher(String string) {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                save(s.toString(), string);
            }
        };
    }
}