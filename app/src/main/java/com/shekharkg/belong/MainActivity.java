package com.shekharkg.belong;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.loopj.android.http.RequestParams;
import com.shekharkg.belong.beans.Data;
import com.shekharkg.belong.utils.CallBack;
import com.shekharkg.belong.utils.NetworkCLient;

public class MainActivity extends AppCompatActivity implements CallBack {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        new NetworkCLient().getRideAvailability(MainActivity.this, MainActivity.this, new RequestParams());
      }
    });
  }

  @Override
  public void successOperation(Object object) {
    Data data = (Data) object;
  }

  @Override
  public void failedOperation(Object object) {

  }
}
