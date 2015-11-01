package com.example.arvind.autocompletetest;

import android.app.ActionBar;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {
    RelativeLayout relativeLayout;
    AutoCompleteTextView actView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String str[] = {"Yo", "My", "Name", "Is", "Slim", "Shady"};
        relativeLayout = (RelativeLayout)findViewById(R.id.relativeLayout);
        actView = new AutoCompleteTextView(MainActivity.this);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams((int) RelativeLayout.LayoutParams.WRAP_CONTENT,(int)RelativeLayout.LayoutParams.WRAP_CONTENT );
        params.leftMargin= 80;
        params.topMargin = 130;

        actView.setLayoutParams(params);
        actView.setEms(10);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,str);

        actView.setThreshold(1);
        actView.setAdapter(arrayAdapter);

        relativeLayout.addView(actView);
    }
}
