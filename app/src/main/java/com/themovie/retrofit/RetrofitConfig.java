package com.themovie.retrofit;

import com.squareup.okhttp.OkHttpClient;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

public class RetrofitConfig {


    public static <S> S getService(Class<S> serviceClass, String urlBase) {
        RestAdapter.Builder builder;

        {
            builder = new RestAdapter.Builder();
            builder.setEndpoint(urlBase);
            builder.setClient(
                    new OkClient(new OkHttpClient())
            );
        }

        RestAdapter adapter = builder.build();

        return adapter.create(serviceClass);
    }

}
