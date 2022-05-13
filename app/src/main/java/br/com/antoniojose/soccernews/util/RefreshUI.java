package br.com.antoniojose.soccernews.util;

import android.content.Context;
import android.content.Intent;

import br.com.antoniojose.soccernews.App;
import br.com.antoniojose.soccernews.MainActivity;

abstract public class RefreshUI {
     public static void refreshAcitvity(){
         App.getIntance().startActivity(new Intent(App.getIntance(), MainActivity.class));
     }
}
