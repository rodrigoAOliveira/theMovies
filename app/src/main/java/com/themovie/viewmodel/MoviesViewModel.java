package com.themovie.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.themovie.models.ResponseUpComing;
import com.themovie.retrofit.RetrofitConfig;
import com.themovie.service.MovieUpcomingService;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MoviesViewModel extends ViewModel {

    private static final String URL = "https://api.themoviedb.org";
    private MutableLiveData<ResponseUpComing> responseUpComingMutableLiveData;

    public LiveData<ResponseUpComing> getMovies() {
        if (responseUpComingMutableLiveData == null) {
            responseUpComingMutableLiveData = new MutableLiveData<>();
        }
        return responseUpComingMutableLiveData;
    }


    public void loadMovies(String apiKey, String language, int page) {
        MovieUpcomingService service = RetrofitConfig.getService(MovieUpcomingService.class, URL);

        service.getMovies(apiKey, language, page, new Callback<ResponseUpComing>() {
            @Override
            public void success(ResponseUpComing responseUpComing, Response response) {
                if (response.getStatus() == 200) {
                    responseUpComingMutableLiveData.setValue(responseUpComing);
                }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }
}
