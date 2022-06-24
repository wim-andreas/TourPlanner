package com.wimfra.tourplanner.viewmodel.observerpattern;

import java.util.ArrayList;
import java.util.List;

public class Publisher {

    private List<ViewModel> subscribers = new ArrayList<>();

    public void subscribe(ViewModel model) {
        subscribers.add(model);
    }

    public void unSubscribe(ViewModel model) {
        subscribers.remove(model);
    }

    // same as the notify Method that is used in many guides for the observer pattern - makes all the viewmodels update to the current status of the database
    public void notifySubs() {
        for (ViewModel subscriber : subscribers) {
            subscriber.updateFromDB();
        }
    }

    public void notifySingleSub(String string){
        if(string.equals("AddLog")){
            ViewModel sub = subscribers.
        }
    }

}
