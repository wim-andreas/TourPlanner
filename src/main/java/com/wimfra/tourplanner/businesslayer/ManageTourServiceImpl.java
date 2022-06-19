package com.wimfra.tourplanner.businesslayer;

import com.wimfra.tourplanner.dataaccesslayer.TourDAO;
import com.wimfra.tourplanner.models.Tour;

import java.util.List;
import java.util.stream.Collectors;

public class ManageTourServiceImpl implements ManageTourService {

    private TourDAO tourDAO = new TourDAO();

    public ManageTourServiceImpl() {
    }

    @Override
    public List<Tour> getTours() {
        return tourDAO.GetTours();
    }

    @Override
    public List<Tour> search(String tourname, boolean caseSensitive) {
        List<Tour>items = getTours();

        if(caseSensitive){
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

    @Override
    public Tour getSingleTour(int id) {
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

}
