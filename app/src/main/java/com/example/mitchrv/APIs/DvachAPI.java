package com.example.mitchrv.APIs;

import com.example.mitchrv.model.Feed;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface DvachAPI {

    @Headers("Content-Type: application/json")
    @GET("/{board}/catalog.json")
    Observable<Feed> getStuff(@Path("board") String boardChar);
}
