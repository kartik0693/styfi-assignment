package com.styfi.assignment.presenter;

import android.util.Log;

import com.styfi.assignment.interactor.ManufacturerDataInteractor;
import com.styfi.assignment.model.ManufacturerData;
import com.styfi.assignment.view.ManufacturerView;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Kartik on 9/4/17.
 */

public class ManufacturerDataPresenter implements ManufacturerDataInteractor.OnDataFetchedListener {

    private static final String TAG = ManufacturerDataPresenter.class.getSimpleName();
    private ManufacturerView mainView;
    private ManufacturerDataInteractor manufacturerDataInteractor;

    public ManufacturerDataPresenter(ManufacturerView mainView, ManufacturerDataInteractor manufacturerDataInteractor) {
        this.mainView = mainView;
        this.manufacturerDataInteractor = manufacturerDataInteractor;
    }

    public void onCreate() {
        if (mainView != null) {
            mainView.showProgress();
        }

        try {
            manufacturerDataInteractor.getManufacturerList(this);
        } catch (SQLException e) {
            Log.e(TAG, " " + e.getMessage(), e);
            onError();
        }
    }

    private void onError() {
        if (mainView != null) {
            mainView.hideProgress();
            mainView.showMessage("Sorry! Data cannot be loaded right now.");
        }
    }

    public void onDestroy() {
        mainView = null;
    }

    private void onEmptyData() {
        if (mainView != null) {
            mainView.hideProgress();
            mainView.showMessage("Sorry! No data available right now.");
        }
    }

    @Override
    public void onDataFetched(List<ManufacturerData> manufacturerDataList) {
        if (mainView != null) {
            if (manufacturerDataList == null) {
                onError();
            } else if (manufacturerDataList.isEmpty()) {
                onEmptyData();
            } else {
                mainView.setItems(manufacturerDataList);
                mainView.hideProgress();
            }
        }
    }
}
