package com.shekharkg.belong;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.HorizontalScrollView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.loopj.android.http.RequestParams;
import com.shekharkg.belong.adapter.CustomListAdapter;
import com.shekharkg.belong.beans.Data;
import com.shekharkg.belong.beans.Folders;
import com.shekharkg.belong.beans.Product;
import com.shekharkg.belong.utils.CallBack;
import com.shekharkg.belong.utils.NetworkClient;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CallBack, AbsListView.OnScrollListener {

  private ListView listView;
  private HorizontalScrollView filterScrollView;
  private RadioGroup filterRG;
  private CustomListAdapter customListAdapter;
  private android.support.v7.widget.CardView progressView;

  private Data data;
  private int pageCount;
  private boolean isApiCalledOnScrollToEnd;
  private List<RadioButton> filterRadioButtonList;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    pageCount = 1;
    listView = (ListView) findViewById(R.id.listView);
    progressView = (CardView) findViewById(R.id.progressView);
    filterScrollView = (HorizontalScrollView) findViewById(R.id.filterScrollView);
    filterRG = (RadioGroup) findViewById(R.id.filterRG);
    filterRadioButtonList = new ArrayList<>();

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
      filterScrollView.setVisibility(View.VISIBLE);
      listView.setOnScrollListener(this);
      showFilterOptions();
    } else {
      customListAdapter.productList = data.getProductList();
      customListAdapter.notifyDataSetChanged();
    }
    isApiCalledOnScrollToEnd = false;
    progressView.setVisibility(View.GONE);
  }

  private void showFilterOptions() {
    for(Folders folders : data.getFoldersList()){
      final RadioButton radioButton = (RadioButton) LayoutInflater.from(this).inflate(R.layout.radio_button, null);
      radioButton.setText(folders.getName());
      filterRadioButtonList.add(radioButton);
      filterRG.addView(radioButton);
    }
  }

  @Override
  public void failedOperation(Object object, boolean isCalledFirstTime) {
    progressView.setVisibility(View.GONE);
    showRetryDialog((String) object, isCalledFirstTime);
  }

  private void showRetryDialog(String errorMsg, final boolean isCalledFirstTime) {
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setTitle(errorMsg);

    builder.setPositiveButton("Retry",
        new DialogInterface.OnClickListener() {
          public void onClick(DialogInterface dialog, int which) {
            progressView.setVisibility(View.VISIBLE);
            new NetworkClient().getDevices(MainActivity.this, MainActivity.this, new RequestParams(), pageCount++, isCalledFirstTime);
          }
        }
    );
    builder.show();
  }

  @Override
  public void onScrollStateChanged(AbsListView view, int scrollState) {

  }

  @Override
  public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
    final int lastItem = firstVisibleItem + visibleItemCount;
    if (lastItem == totalItemCount) {
      if (!isApiCalledOnScrollToEnd && data.getProductList().size() < data.getTotalNumberOfProducts()) {
        progressView.setVisibility(View.VISIBLE);
        new NetworkClient().getDevices(MainActivity.this, MainActivity.this, new RequestParams(), pageCount++, false);
      }
      isApiCalledOnScrollToEnd = true;
    }
  }
}
