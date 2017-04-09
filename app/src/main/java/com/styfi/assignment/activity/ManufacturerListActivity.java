package com.styfi.assignment.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.styfi.assignment.R;
import com.styfi.assignment.adapter.ManufacturerDataListAdapter;
import com.styfi.assignment.interactor.ManufacturerDataInteractor;
import com.styfi.assignment.model.ManufacturerData;
import com.styfi.assignment.presenter.ManufacturerDataPresenter;
import com.styfi.assignment.view.ManufacturerView;

import java.util.List;

public class ManufacturerListActivity extends AppCompatActivity implements ManufacturerView {

    ListView manufacturersListView;
    ProgressBar progressBar;
    ManufacturerDataPresenter manufacturerDataPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manufacturer_list);
        manufacturersListView = (ListView) findViewById(R.id.list_view_manufacturers);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        manufacturerDataPresenter = new ManufacturerDataPresenter(this, new ManufacturerDataInteractor());
        manufacturerDataPresenter.onCreate();
    }

    @Override
    protected void onDestroy() {
        manufacturerDataPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        manufacturersListView.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
        manufacturersListView.setVisibility(View.VISIBLE);
    }

    @Override
    public void setItems(List<ManufacturerData> items) {
        manufacturersListView.setAdapter(new ManufacturerDataListAdapter(this, R.layout.list_item_manufacturer_data, items));
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
