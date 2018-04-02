package practicaltest01var02.eim.systems.cs.pub.ro.practicaltest01var02.service;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Date;

import practicaltest01var02.eim.systems.cs.pub.ro.practicaltest01var02.general.Constants;

/**
 * Created by Florentina on 02-Apr-18.
 */

public class ProcessingThread extends Thread {

    private Context context = null;
    private boolean isRunning = true;

    private double sum;
    private double dif;

    public ProcessingThread(Context context, int firstNumber, int secondNumber) {
        this.context = context;

        sum = firstNumber + secondNumber;
        dif = firstNumber - secondNumber;
    }

    @Override
    public void run() {
        Log.d("[TEST]", "Thread has started!");

        while (isRunning) {
            sendMessage(0);
            sleep();
            sendMessage(1);
            this.stopThread();
        }

        Log.d("[TEST]", "Thread has stopped!");
    }

    private void sendMessage(int next) {
        Intent intent = new Intent();
        intent.setAction(Constants.actionTypes[next]);
        intent.putExtra("message", sum + " " + dif);
        context.sendBroadcast(intent);
    }

    private void sleep() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    public void stopThread() {
        isRunning = false;
    }
}