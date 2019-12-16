package com.example.mitchrv.viewmodels;

import androidx.lifecycle.ViewModel;

import com.example.mitchrv.model.Messages;
import com.example.mitchrv.repositories.MessagesRepository;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class MessageRecyclerFragmentViewModel extends ViewModel {

    private MessagesRepository messagesRepository = new MessagesRepository();

    public MessageRecyclerFragmentViewModel() {
        Timber.i("init");
    }

    public Observable<Messages> getMessages(int num) {
        return messagesRepository.getMessages(num)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public ArrayList<String> getComments() {
        return messagesRepository.getComments();
    }

    public ArrayList<String> getImageUrls() {
        return messagesRepository.getImageUrls();
    }

    public void clearComments() {
        messagesRepository.removeComments();
    }

    public void clearImages() {
        messagesRepository.removeImages();
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
