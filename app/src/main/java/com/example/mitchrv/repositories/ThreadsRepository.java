package com.example.mitchrv.repositories;

import androidx.annotation.Nullable;

import com.example.mitchrv.APIs.DvachAPI;
import com.example.mitchrv.model.Feed;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

public class ThreadsRepository {

    private static ThreadsRepository instance;
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    private ArrayList<Integer> mNums = new ArrayList<>();
    @Nullable
    private String threadCash = null;

    private static final String BASE_URL = "https://2ch.hk/";


    public static ThreadsRepository getInstance() {
        if (instance == null) instance = new ThreadsRepository();
        return instance;
    }

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();

    DvachAPI dvachAPI = retrofit.create(DvachAPI.class);

    public Observable<String> getFeed(String boardChar) {

        if (threadCash != null) {
            Timber.d("from cash");
            return Observable.just(threadCash);
        }

        else {
            Timber.d("from net");
            return dvachAPI
                    .getStuff(boardChar)
                    .flatMapIterable(Feed::getThreads)
                    .doOnNext(threads -> mNames.add(threads.getSubject()))
                    .doOnNext(threads -> mNums.add(threads.getNum()))
                    .flatMap(threads -> Observable.fromArray(threads.getFiles().get(0)))
                    .doOnNext(files -> mImageUrls.add("https://2ch.hk" + files.getPath()))
                    .flatMap(files -> Observable.fromArray(files.getPath()))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnNext(threads -> threadCash = threads);
        }
    }

    public ArrayList<String> getNames() {
        return mNames;
    }

    public ArrayList<String> getImageUrls() {
        return mImageUrls;
    }

    public ArrayList<Integer> getNums() {
        return mNums;
    }
}
