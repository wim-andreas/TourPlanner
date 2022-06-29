package com.wimfra.tourplanner.businesslayer;

import com.wimfra.tourplanner.businesslayer.mapquest.MapQuestAPI;
import com.wimfra.tourplanner.dataaccesslayer.TourDAO;
import com.wimfra.tourplanner.models.TourModel;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
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
    public void addNewTour(List<String> data) {
        tourDAO.AddNewTour(data);
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
    public void createTourImage(String from_where, String to_where, int tour_id) {
        byte[] image = MapQuestAPI.getStaticMap(from_where, to_where);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("./src/main/resources/images/" +tour_id + ".jpg");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            fos.write(image);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
