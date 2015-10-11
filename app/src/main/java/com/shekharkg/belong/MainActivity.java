package com.shekharkg.belong;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.loopj.android.http.RequestParams;
import com.shekharkg.belong.adapter.CustomListAdapter;
import com.shekharkg.belong.beans.Data;
import com.shekharkg.belong.beans.Product;
import com.shekharkg.belong.utils.CallBack;
import com.shekharkg.belong.utils.NetworkClient;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CallBack, AbsListView.OnScrollListener {

  private ListView listView;
  private CustomListAdapter customListAdapter;
  private android.support.v7.widget.CardView progressView;
  private Data data;
  private int pageCount;
  private boolean isApiCalledOnScrollToEnd;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    pageCount = 1;
    listView = (ListView) findViewById(R.id.listView);
    progressView = (CardView) findViewById(R.id.progressView);

    new NetworkClient().getDevices(MainActivity.this, MainActivity.this, new RequestParams(), pageCount++, true);
  }

  @Override
  public void successOperation(Object object, boolean isCalledFirstTime) {

    List<Product> productList = null;
    if (!isCalledFirstTime)
      productList = new ArrayList<>(data.getProductList());

    data = (Data) object;

    if (!isCalledFirstTime)
      for (int i = 0; i < productList.size(); i++)
        data.getProductList().add(i, productList.get(i));

    if (isCalledFirstTime) {
      customListAdapter = new CustomListAdapter(data.getProductList(), this);
      listView.setAdapter(customListAdapter);
      listView.setVisibility(View.VISIBLE);
      listView.setOnScrollListener(this);
    } else {
      customListAdapter.productList = data.getProductList();
      customListAdapter.notifyDataSetChanged();
    }
    isApiCalledOnScrollToEnd = false;
    progressView.setVisibility(View.GONE);
  }

  @Override
  public void failedOperation(Object object, boolean isCalledFirstTime) {
//    progressView.setVisibility(View.GONE);
  }

  @Override
  public void onScrollStateChanged(AbsListView view, int scrollState) {

  }

  @Override
  public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
    final int lastItem = firstVisibleItem + visibleItemCount;
    if (lastItem == totalItemCount) {
      if (!isApiCalledOnScrollToEnd) {
        progressView.setVisibility(View.VISIBLE);
        new NetworkClient().getDevices(MainActivity.this, MainActivity.this, new RequestParams(), pageCount++, false);
      }
      isApiCalledOnScrollToEnd = true;
    }
  }
}
