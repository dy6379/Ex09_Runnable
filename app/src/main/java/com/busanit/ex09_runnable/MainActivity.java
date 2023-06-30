package com.busanit.ex09_runnable;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
//    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BackgroundThread thread = new BackgroundThread();
                thread.start();
            }
        });
    }

    private class BackgroundThread extends Thread{
        int value =0;

        @Override
        public void run() {
            for(int i=0; i<30; i++){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                value +=1;
                Log.d("myLog","value : "+value);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText("value 값 : "+value);
                    }
                });

//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        textView.setText("value 값 : "+value);
//                    }
//                });
            }
        }
    }
}