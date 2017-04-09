package com.styfi.assignment.interfaces;

import com.styfi.assignment.model.ManufacturerDataResponse;

import retrofit2.Call;
import retrofit2.http.GET;


public interface APIEndpointInterface {

    @GET("/api/cron/assign_task")
    Call<ManufacturerDataResponse> getManufacturerData();

}