package org.yxdroid.droidaspect.aspect;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.content.PermissionChecker;
import android.util.Log;
import android.widget.Toast;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.yxdroid.droidaspect.annotations.CheckPermission;

import java.lang.reflect.Method;

import static android.R.attr.targetSdkVersion;

/**
 * User: yxfang
 * Date: 03/06/2017
 * Time: 5:01 PM
 * ------------- Description -------------
 * ---------------------------------------
 */
@Aspect
public class DroidLogAspect
{
    private static final String TAG = "DroidLogAspect";

    @Pointcut("execution(@org.yxdroid.droidaspect.annotations.DroidLog  * *(..))")
    public void annoDroidLog()
    {

    }

    @Pointcut("execution(@org.yxdroid.droidaspect.annotations.CheckPermission  * *(..))")
    public void annoCheckPermission()
    {

    }


    @Around("annoDroidLog()")
    public void handleDroidLogPointCut(ProceedingJoinPoint joinPoint) throws Throwable
    {
        Context context = (Context) joinPoint.getTarget();
        Toast.makeText(context, "hello aop", Toast.LENGTH_SHORT).show();

        joinPoint.proceed();
    }

    @Around("annoCheckPermission()")
    public Object handleCheckPermissionPointCut(ProceedingJoinPoint joinPoint) throws Throwable
    {
        Context context = (Context) joinPoint.getTarget();

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        CheckPermission annotation = method.getAnnotation(CheckPermission.class);
        Log.d(TAG, method.getReturnType().getName());
        String[] permissions = annotation.permissions();

        boolean result;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            result = check(context, permissions);
        }
        else
        {
            result = check(context, permissions);
        }

        if (result)
        {
            return joinPoint.proceed();
        }
        return null;
    }

    private static boolean check(Context context, String[] permissions)
    {
        boolean result = true;
        for (String permission : permissions)
        {
            if (targetSdkVersion >= Build.VERSION_CODES.M)
            {
                result = context.checkSelfPermission(permission)
                        == PackageManager.PERMISSION_GRANTED;
            }
            else
            {
                result = PermissionChecker.checkSelfPermission(context, permission)
                        == PermissionChecker.PERMISSION_GRANTED;
            }
            Log.d(TAG, permission + "2222222 " + result);
            if (!result)
            {
                Log.e(TAG, String.format("%s permission denied", permission));
            }
        }
        return result;
    }
}
