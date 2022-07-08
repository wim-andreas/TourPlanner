package com.wimfra.tourplanner.businesslayer.file;

import com.wimfra.tourplanner.businesslayer.ManageTourService;
import com.wimfra.tourplanner.businesslayer.ManageTourServiceImpl;
import com.wimfra.tourplanner.viewmodel.observerpattern.Publisher;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVFileImport implements FileImportService {
    private final ManageTourService tourService = new ManageTourServiceImpl();
    private final static String DELIMITER = ";";

    public CSVFileImport() {
    }

    @Override
    public void importOneTour(Window currentWindow, Publisher publisher) {
        String filepath = getChoseFileName(currentWindow);
        if("no file selected".equals(filepath)){
            //TODO: write logger message
        }
        else{
            List<String> newTour = getTourDataFromFile(filepath);
            int id = tourService.addNewTour(newTour);
            tourService.createImage(id);
            publisher.notifySingleSubscriber("TourListViewModel");
            publisher.notifySingleSubscriber("RouteViewModel");
        }
    }

    private String getChoseFileName(Window currentWindow){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        File selectedFile = fileChooser.showOpenDialog(currentWindow);
        if (selectedFile != null) {
            return selectedFile.getAbsolutePath();
        }
        else{
            return "no file selected";
        }
    }

    private List<String> getTourDataFromFile(String filepath){
        List<List<String>> entrys = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(DELIMITER);
                entrys.add(Arrays.asList(values));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String> tourData = new ArrayList<>();
        tourData.add(0, entrys.get(1).get(7));
        tourData.add(0, entrys.get(1).get(6));
        tourData.add(0, entrys.get(1).get(5));
        tourData.add(0, entrys.get(1).get(4));
        tourData.add(0, entrys.get(1).get(3));
        tourData.add(0, entrys.get(1).get(2));
        tourData.add(0, entrys.get(1).get(1));
        tourData.add(0, entrys.get(1).get(0));

        return tourData;
    }

}
