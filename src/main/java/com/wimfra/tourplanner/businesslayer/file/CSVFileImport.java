package com.wimfra.tourplanner.businesslayer.file;

import com.wimfra.tourplanner.businesslayer.ManageTourService;
import com.wimfra.tourplanner.businesslayer.ManageTourServiceImpl;

public class CSVFileImport implements FileImportService {
    private final ManageTourService tourService = new ManageTourServiceImpl();
    private final static String DELIMITER = ";";
    private final static String SEPARATOR = "\n";

    public CSVFileImport() {
    }

    @Override
    public void importOneTour() {




    }
}
