package com.styfi.assignment.application;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.styfi.assignment.R;
import com.styfi.assignment.db.StyfiAssignmentDatabase;
import com.styfi.assignment.interfaces.APIEndpointInterface;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class StyfiAssignmentApplication extends Application {

    private static final String TAG = StyfiAssignmentApplication.class.getSimpleName();
    static StyfiAssignmentApplication application;
    private static StyfiAssignmentDatabase database;
    private static APIEndpointInterface apiEndpointInterface;

    public static StyfiAssignmentApplication getApplication() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;

        if (database == null) {
            setDatabase(this);
        }
    }

    public static APIEndpointInterface getApiEndpointInterface() {
        if (apiEndpointInterface == null) {
            apiEndpointInterface = createAPIEndpoint();
        }
        return apiEndpointInterface;
    }

    private static APIEndpointInterface createAPIEndpoint() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(application.getResources().getString(R.string.base_url))
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(APIEndpointInterface.class);
    }

    public static StyfiAssignmentDatabase getDatabase() {
        return database;
    }

    public static void setDatabase(Context context) {
        if (database == null) {
            database = new StyfiAssignmentDatabase(context);
        }
    }
}
