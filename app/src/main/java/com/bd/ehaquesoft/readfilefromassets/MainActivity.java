package com.bd.ehaquesoft.readfilefromassets;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

public class MainActivity extends AppCompatActivity {

    TextView tv_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_text = (TextView) findViewById(R.id.tv_text);

        tv_text.setText(getTextFromAssets("example.html"));

        tv_text.setText(tv_text.getText()+"\n\n"+getTextFromAssets("example.txt"));
    }


    private String getTextFromAssets(String fileName){

        try {
            final int bufferSize = 1024;
            final char[] buffer = new char[bufferSize];

            InputStream inputStream = getResources().getAssets().open(fileName);

            final StringBuilder out = new StringBuilder();
            Reader in = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            int charsRead;
            while((charsRead = in.read(buffer, 0, buffer.length)) > 0) {
                out.append(buffer, 0, charsRead);
            }

            Log.e("TEXT", out.toString());

            return out.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }
}
