package com.samples.app.tvtimedemo.di.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by $Abed Elaziz Shehadeh on 20, January, 2018
 * elaziz.shehadeh@gmail.com
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}
