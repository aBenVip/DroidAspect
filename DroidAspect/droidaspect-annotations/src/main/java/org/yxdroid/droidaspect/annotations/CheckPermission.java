package org.yxdroid.droidaspect.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * User: yxfang
 * Date: 03/06/2017
 * Time: 5:44 PM
 * ------------- Description -------------
 * ---------------------------------------
 */

@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckPermission
{
   String[] permissions();
}
