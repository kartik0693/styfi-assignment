package com.styfi.assignment.view;

import com.styfi.assignment.model.ManufacturerData;

import java.util.List;

/**
 * Created by Kartik on 9/4/17.
 */

public interface ManufacturerView {

    void showProgress();

    void hideProgress();

    void setItems(List<ManufacturerData> items);

    void showMessage(String message);
}
