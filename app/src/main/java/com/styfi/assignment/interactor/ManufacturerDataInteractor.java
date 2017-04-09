package com.styfi.assignment.interactor;

import android.util.Log;

import com.styfi.assignment.application.StyfiAssignmentApplication;
import com.styfi.assignment.dao.ModelDAO;
import com.styfi.assignment.model.ManufacturerData;
import com.styfi.assignment.model.ManufacturerDataResponse;

import java.sql.SQLException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Kartik on 9/4/17.
 */

public class ManufacturerDataInteractor {

    private static final String TAG = ManufacturerDataInteractor.class.getSimpleName();
    private ModelDAO modelDAO;

    public void getManufacturerList(OnDataFetchedListener onDataFetchedListener) throws SQLException {
        modelDAO = new ModelDAO(StyfiAssignmentApplication.getDatabase());

        List<ManufacturerData> manufacturerDataList = modelDAO.getAllModelList(ManufacturerData.class);

        if (manufacturerDataList == null || manufacturerDataList.isEmpty()) {
            fetchManufacturerListFromServer(onDataFetchedListener);
        } else {
            onDataFetchedListener.onDataFetched(manufacturerDataList);
        }

    }

    private void fetchManufacturerListFromServer(final OnDataFetchedListener onDataFetchedListener) {
        Call<ManufacturerDataResponse> call = StyfiAssignmentApplication.getApiEndpointInterface().getManufacturerData();
        call.enqueue(new Callback<ManufacturerDataResponse>() {
            @Override
            public void onResponse(Call<ManufacturerDataResponse> call, Response<ManufacturerDataResponse> response) {
                if (response.body() != null) {
                    if (response.body().getMessage().equals("success")) {
                        final List<ManufacturerData> manufacturerDataList = response.body().getData();

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    persistManufacturerData(manufacturerDataList);
                                } catch (Exception e) {
                                    Log.e(TAG, " " + e.getMessage(), e);
                                }
                            }
                        }).run();

                        onDataFetchedListener.onDataFetched(manufacturerDataList);
                    } else {
                        onDataFetchedListener.onDataFetched(null);
                    }
                } else {
                    onDataFetchedListener.onDataFetched(null);
                }
            }

            @Override
            public void onFailure(Call<ManufacturerDataResponse> call, Throwable t) {
                if (t != null && t.getMessage() != null) {
                    Log.e(TAG, " " + t.getMessage(), t);
                }
                onDataFetchedListener.onDataFetched(null);
            }
        });
    }

    private void persistManufacturerData(List<ManufacturerData> manufacturerDataList) throws Exception {
        modelDAO.bulkPersist(manufacturerDataList);
    }

    public interface OnDataFetchedListener {
        void onDataFetched(List<ManufacturerData> manufacturerDataList);
    }
}
