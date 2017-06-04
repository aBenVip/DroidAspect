package org.yxdroid.droidaspect;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import org.yxdroid.droidaspect.annotations.CheckPermission;

/**
 * User: yxfang
 * Date: 03/06/2017
 * Time: 5:30 PM
 * ------------- Description -------------
 * ---------------------------------------
 */

public class LoginActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_login);

        doAction();
    }

    @CheckPermission(permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA})
    public boolean doAction()
    {
        return false;
    }
}
