package com.temnogrudova.lesson8_1;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.concurrent.TimeUnit;

public class MyService extends Service {
  
  final String LOG_TAG = "myLogs";

  public void onCreate() {
    super.onCreate();
    Log.d(LOG_TAG, "onCreate");
  }
  
  public int onStartCommand(Intent intent, int flags, int startId) {
    Log.d(LOG_TAG, "onStartCommand");
    // запуск без параметра
    // someTask();
    int time = intent.getIntExtra("time", 1);
    someTask1(time);
    return super.onStartCommand(intent, flags, startId);
  }

  public void onDestroy() {
    super.onDestroy();
    Log.d(LOG_TAG, "onDestroy");
  }

  public IBinder onBind(Intent intent) {
    Log.d(LOG_TAG, "onBind");
    return null;
  }
  
  void someTask() {

    /* Попытка без потока обойтись, поэтому блокируется экран
        for (int i = 1; i<=5; i++) {
      Log.d(LOG_TAG, "i = " + i);
      try {
        TimeUnit.SECONDS.sleep(1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
     */
    //Попытка с потоком, но без входного параметра
    Thread t = new Thread(new Runnable() {
      @Override
      public void run() {
        for (int i = 1; i<=5; i++) {
          Log.d(LOG_TAG, "i = " + i);
          try {
            TimeUnit.SECONDS.sleep(1);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
        stopSelf();
      }
    });
    t.start();

  }
  private void someTask1(final int time) {
    Thread t = new Thread(new Runnable() {
      @Override
      public void run() {
        Log.d(LOG_TAG, "start");
        try {
          TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        Log.d(LOG_TAG, "time = " + time);
      }
    });
    t.start();
  }

}