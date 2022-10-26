package com.korinistscheise;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import java.util.Arrays;

public class ardaBomber extends AsyncTask<Void, Void, String> {

    public static SharedPreferences pref;
    String[] messages;
    int randomizer;


    public ardaBomber(String[] messages, int randomizer) {
        this.messages = messages;
        this.randomizer = randomizer;
    }

    public static void init(Context ctx) {
        pref = ctx.getSharedPreferences("Arda", Context.MODE_PRIVATE);
    }

    public static void saveMsg(String[] msg) {
        pref.edit().putString("bomber", Arrays.toString(msg)).apply();
    }

    public static String loadMsg() {
        return pref.getString("bomber", "[\"\"]");
    }

    @Override
    protected String doInBackground(Void... voids) {
        return null;
    }

}
