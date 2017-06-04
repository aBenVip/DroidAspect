package org.yxdroid.droidaspect;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.yxdroid.droidaspect.annotations.DroidLog;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        run();
    }

    @DroidLog
    public void run()
    {
        startActivity(new Intent(this, LoginActivity.class));
    }

}
