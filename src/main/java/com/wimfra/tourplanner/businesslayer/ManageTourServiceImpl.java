package com.wimfra.tourplanner.businesslayer;

import com.fasterxml.jackson.databind.JsonNode;
import com.wimfra.tourplanner.businesslayer.mapquest.MapQuestAPI;
import com.wimfra.tourplanner.dataaccesslayer.TourDAO;
import com.wimfra.tourplanner.models.TourModel;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ManageTourServiceImpl implements ManageTourService {

    private TourDAO tourDAO = new TourDAO();

    public ManageTourServiceImpl() {
    }

    @Override
    public List<TourModel> getTours() {
        return tourDAO.GetTours();
    }

    @Override
    public List<TourModel> search(String tourname, boolean caseSensitive) {
        List<TourModel> items = getTours();
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
        return tourDAO.GetSingleTour(id);
    }

    @Override
    public int addNewTour(List<String> data) {
       return tourDAO.AddNewTour(data);
    }

    @Override
    public void deleteTour(int tour_id) {
        tourDAO.DeleteTour(tour_id);
    }

    @Override
    public void editTourData(List<String> data, int id) {
        tourDAO.EditTourData(data, id);
    }

    @Override
    public void createImage(int id) {
        System.out.print("Bin ich hier?");

        TourModel tour = tourDAO.GetSingleTour(id);

        File dir = new File("./src/main/resources/images/");
        FilenameFilter filter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.equals(id + ".jpg");
            }
        };
        String[] files = dir.list(filter);
        if (files == null) {
            System.out.println("Either dir does not exist or is not a directory");
        } else {
            if (files.length == 0) {
                byte[] image = MapQuestAPI.getStaticMap(tour.getFrom_where(), tour.getTo_where());
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream("./src/main/resources/images/" + id + ".jpg");
                    System.out.print("Tour erstellt");
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
