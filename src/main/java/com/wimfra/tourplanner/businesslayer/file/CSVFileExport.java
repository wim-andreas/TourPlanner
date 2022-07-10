package com.wimfra.tourplanner.businesslayer.file;

import com.wimfra.tourplanner.businesslayer.ManageTourService;
import com.wimfra.tourplanner.businesslayer.ManageTourServiceImpl;
import com.wimfra.tourplanner.logger.ILoggerWrapper;
import com.wimfra.tourplanner.logger.LoggerFactory;
import com.wimfra.tourplanner.models.TourModel;
import com.wimfra.tourplanner.view.AddTourController;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class CSVFileExport implements FileExportService {
    private static final ILoggerWrapper logger = LoggerFactory.getLogger(CSVFileExport.class);
    private final ManageTourService tourService = new ManageTourServiceImpl();
    private final static String DELIMITER = ";";
    private final static String SEPARATOR = "\n";
    private final static String HEADER = "tour_name;description;from_where;to_where;transportation;distance;duration;route_info";

    public CSVFileExport() {
    }

    @Override
    public void exportOneTour(int tourID) {
        if(tourID >= 0){
            logger.debug("Starting the export of a single tour into a csv file...");
            FileWriter file = null;
            TourModel exportedTour = tourService.getSingleTour(tourID);
            try {
                file = new FileWriter("./fileexports/" + LocalDate.now() + getCurrentTimeValue() + ".csv");
                file.append(HEADER);
                file.append(SEPARATOR);

                //adding the tours values;
                file.append(exportedTour.getTour_name());
                file.append(DELIMITER);
                file.append(exportedTour.getDescription());
                file.append(DELIMITER);
                file.append(exportedTour.getFrom_where());
                file.append(DELIMITER);
                file.append(exportedTour.getTo_where());
                file.append(DELIMITER);
                file.append(exportedTour.getTransportation());
                file.append(DELIMITER);
                file.append(exportedTour.getDistance());
                file.append(DELIMITER);
                file.append(exportedTour.getDuration());
                file.append(DELIMITER);
                file.append(exportedTour.getRoute_info());
                file.close();
                logger.debug("Export of tour into csv file finished successfully!");
            } catch (IOException e) {
                logger.error(e.getStackTrace().toString());
            }
        }
    }

    private String getCurrentTimeValue(){
        String currentValue = LocalTime.now().toString();
        String finalValue = "_";

        for(int i = 0; i < currentValue.length(); i++){
            if(currentValue.charAt(i) != ':' && currentValue.charAt(i) != '.'){
                finalValue += currentValue.charAt(i);
            }
            else if(currentValue.charAt(i) == ':'){
                finalValue += "-";
            }
            else{
                break;
            }
        }
        return finalValue;
    }
}
