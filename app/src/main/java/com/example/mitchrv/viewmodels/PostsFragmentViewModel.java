package com.example.mitchrv.viewmodels;

import androidx.lifecycle.ViewModel;

import com.example.mitchrv.model.Messages;
import com.example.mitchrv.repositories.PostsRepository;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class PostsFragmentViewModel extends ViewModel {

    private PostsRepository postsRepository = new PostsRepository();

    public PostsFragmentViewModel() {
        Timber.i("init");
    }

    public Observable<Messages> getMessages(int num) {
        return postsRepository.getMessages(num)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public ArrayList<String> getComments() {
        return postsRepository.getComments();
    }

    public ArrayList<String> getImageUrls() {
        return postsRepository.getImageUrls();
    }

    public void clearComments() {
        postsRepository.removeComments();
    }

    public void clearImages() {
        postsRepository.removeImages();
    }


    //removes data when we come back
    @Override
    protected void onCleared() {
        super.onCleared();
        clearComments();
        clearImages();
        Timber.d("oncleared called");
    }
}
