package com.shekharkg.belong.utils;

/**
 * Created by ShekharKG on 10/9/2015.
 */
public interface CallBack {

  public void successOperation(Object object, boolean isCalledFirstTime);
  public void failedOperation(Object object, boolean isCalledFirstTime);

}
