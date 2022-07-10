package com.wimfra.tourplanner.businesslayer;

import com.wimfra.tourplanner.businesslayer.mapquest.MapQuestAPI;
import com.wimfra.tourplanner.dataaccesslayer.TourDAO;
import com.wimfra.tourplanner.logger.ILoggerWrapper;
import com.wimfra.tourplanner.logger.LoggerFactory;
import com.wimfra.tourplanner.models.TourModel;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class ManageTourServiceImpl implements ManageTourService {

    private TourDAO tourDAO = new TourDAO();

    private static final ILoggerWrapper logger = LoggerFactory.getLogger(ManageTourServiceImpl.class);

    public ManageTourServiceImpl() {
    }

    @Override
    public List<TourModel> getTours() {
        return tourDAO.getTours();
    }

    @Override
    public List<TourModel> search(String tourname, boolean caseSensitive) {
        List<TourModel> items = tourDAO.getTours();
        if (tourname != null) {
            if (caseSensitive) {
                return items
                        .stream()
                        .filter(x -> x.getTour_name().contains(tourname))
                        .collect(Collectors.toList());
            }
            return items
                    .stream()
                    .filter(x -> x.getTour_name().toLowerCase().contains(tourname.toLowerCase()))
                    .collect(Collectors.toList());
        }
        return items;
    }

    @Override
    public TourModel getSingleTour(int id) {
        return tourDAO.getSingleTour(id);
    }

    @Override
    public int addNewTour(List<String> data) {
       return tourDAO.addNewTour(data);
    }

    @Override
    public void deleteTour(int tour_id) {
        tourDAO.deleteTour(tour_id);
    }

    @Override
    public void editTourData(List<String> data, int id) {
        tourDAO.editTourData(data, id);
    }

    @Override
    public void createImage(int id) {

        TourModel tour = tourDAO.getSingleTour(id);

        File dir = new File("./src/main/resources/images/");
        FilenameFilter filter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.equals(id + ".jpg");
            }
        };
        String[] files = dir.list(filter);
        if (files == null) {
            logger.error("Either dir does not exist or is not a directory");
        } else {
            if (files.length == 0) {
                byte[] image = MapQuestAPI.getStaticMap(tour.getFrom_where(), tour.getTo_where());
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream("./src/main/resources/images/" + id + ".jpg");
                    logger.info("Tour image created");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                try {
                    assert fos != null;
                    assert image != null;
                    fos.write(image);
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        assert fos != null;
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }


    }



}
