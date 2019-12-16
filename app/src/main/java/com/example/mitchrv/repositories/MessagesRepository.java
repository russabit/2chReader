package com.example.mitchrv.repositories;

import androidx.annotation.Nullable;

import com.example.mitchrv.APIs.DvachMessageAPI;
import com.example.mitchrv.CustomInterceptor;
import com.example.mitchrv.model.Messages;

import java.util.ArrayList;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

//Singleton pattern

public class MessagesRepository {

    private static final String TAG = "MessagesRepository";

    private static MessagesRepository instance;
    private ArrayList<String> mComments = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();

    private static final String BASE_URL = "https://2ch.hk/";
    @Nullable
    private Messages threadMessageCash = null;

    private HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

    public static MessagesRepository getInstance() {
        if (instance == null) instance = new MessagesRepository();
        instance.interceptor.level(HttpLoggingInterceptor.Level.BODY);
        return instance;
    }

    OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addInterceptor(interceptor) // This is used to add ApplicationInterceptor.
            .addNetworkInterceptor(new CustomInterceptor()) //This is used to add NetworkInterceptor.
            .build();

    Retrofit retrofitMSG = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build();

    DvachMessageAPI dvachMessageAPI = retrofitMSG.create(DvachMessageAPI.class);

    public Observable<Messages> getMessages(int num) {

        if (threadMessageCash != null) {
            Timber.d("from cash");
            return Observable.just(threadMessageCash);
        } else {
            Timber.d("from net");
            return dvachMessageAPI
                    .getStuff("get_thread", "pr", num, 1)
                    .flatMap(Observable::fromIterable)
                    .doOnNext(message -> {
                        mComments.add(message.getComment());
                        Timber.d("inside RX - mComments.size() %s", mComments.size());
                        if (!message.getFiles().isEmpty())
                            mImageUrls.add("https://2ch.hk" + message.getFiles().get(0).getPath());
                        else mImageUrls.add(null);
                        Timber.d("inside RX - mImageUrls.size(): %s", mImageUrls.size());
                        Timber.d("initImageBitmaps: mImageUrls: %s", mImageUrls);
                    })
                    .doOnNext(messages -> threadMessageCash = messages);
        }
    }

    public ArrayList<String> getComments() {
        return mComments;
    }

    public ArrayList<String> getImageUrls() {
        return mImageUrls;
    }

    public void removeComments() {
        if (!mComments.isEmpty())
            mComments.clear();
    }

    public void removeImages() {
        if (!mImageUrls.isEmpty())
            mImageUrls.clear();
    }
}
