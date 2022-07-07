package com.wimfra.tourplanner.viewmodel;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ClickMeViewModel {

    private final StringProperty textArea = new SimpleStringProperty();
    public StringProperty textAreaProperty() {
        return textArea;
    }


    public void loadSecret() {

        textArea.setValue("Thanks for using this app! Support us by donating to our PayPayl!\n\n" +
                "(\"`-''-/\").___..--''\"`-._ \n" +
                " `6_ 6  )   `-.  (     ).`-.__.`) \n" +
                " (_Y_.)'  ._   )  `._ `. ``-..-' \n" +
                "   _..`--'_..-_/  /--'_.'\n" +
                "  ((((.-''  ((((.'  (((.-' ");

    }
}
