package com.example.uimode.java;

import android.util.Log;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;

public class JavaTest {


  static {
      m = 300;
      Log.i("1","----> static代码块");
  }

  public static  int m = 100;

  public  JavaTest(){
      Log.i("1","----> m的值: "+m);
  }

}
