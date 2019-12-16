package com.example.mitchrv.viewmodels;


import androidx.lifecycle.ViewModel;
import com.example.mitchrv.repositories.ThreadsRepository;
import java.util.ArrayList;
import io.reactivex.Observable;

public class RecyclerFragmentViewModel extends ViewModel {

    private Observable<String> observableFeed;

    private ThreadsRepository threadsRepository;

    public void init() {
        if (observableFeed!=null) {
            return;
        }
        threadsRepository = threadsRepository.getInstance();
        observableFeed = this.threadsRepository.getFeed();
    }

    public Observable<String> getFeed() {
        return observableFeed;
    }

    public ArrayList<String> getNames() {
        return threadsRepository.getNames();
    }

    public ArrayList<String> getImageUrls() {
        return threadsRepository.getImageUrls();
    }

    public ArrayList<Integer> getNums() {
        return threadsRepository.getNums();
    }
}
