package com.korinistscheise;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

public class ardaBomber extends AsyncTask<Void, Void, String> {

    public static SharedPreferences pref;
    MainActivity ctx;
    String msg;
    int randomizer;
    int count;

    public ardaBomber(MainActivity ctx) {
        pref = ctx.getSharedPreferences("Arda", Context.MODE_PRIVATE);
    }

    public static int getIDbyName(Context ctx, String resName) {
        return ctx.getResources().getIdentifier(resName, "id", ctx.getPackageName());
    }

    public static void save(String save, String s) {
        pref.edit().putString("bomber_" + s, save).apply();
    }

    public void init(MainActivity ctx, String msg, int count, int randomizer) {
        this.ctx = ctx;
        this.msg = msg;
        this.randomizer = randomizer;
        this.count = count;
    }

    @Override
    protected String doInBackground(Void... voids) {
        if (randomizer == 0) for (int i = 0; i < count; i++)ctx.sendMsg(msg);

        else if (randomizer == 1) {
            String[] msgs = msg.split("\\$");
            for (int i = 0; i < count; ) {
                for (int e = 0; e < msgs.length && i < count; e++, i++) {
                    ctx.sendMsg(msgs[e]);
                    Log.e("caused fmkjjjjj", msgs[e]);
                }

            }
        } else if (randomizer == 2) {
            String[] msgs = msg.split("\\$");
            for (int i = 0; i < count; i++) {
                int rand = (int) (Math.random() * msgs.length);
                Log.e("caused fmkjjjjj", String.valueOf(rand));
                ctx.sendMsg(msgs[rand]);
            }
        }

        return null;
    }


}
