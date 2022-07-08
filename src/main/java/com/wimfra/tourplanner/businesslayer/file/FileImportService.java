package com.wimfra.tourplanner.businesslayer.file;

import com.wimfra.tourplanner.viewmodel.observerpattern.Publisher;
import javafx.stage.Window;

public interface FileImportService {
    void importOneTour(Window currentWindow, Publisher publisher);
}
