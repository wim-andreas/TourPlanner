package com.wimfra.tourplanner.businesslayer;



import com.wimfra.tourplanner.dataaccesslayer.TourDAO;
import com.wimfra.tourplanner.models.Tour;

import java.util.List;
import java.util.stream.Collectors;

public class JavaAppManagerImpl implements  JavaAppManager{
TourDAO tourDAO = new TourDAO();

    @Override
    public List<Tour> GetTours() {
        return tourDAO.GetTours();
    }

    @Override
    public List<Tour> Search(String tourname, boolean caseSensitive) {
        List<Tour>items = GetTours();

        if(caseSensitive){
            return items
                    .stream()
                    .filter(x -> x.getTourname().contains(tourname))
                    .collect(Collectors.toList());
        }
        return items
                .stream()
                .filter(x -> x.getTourname().toLowerCase().contains(tourname.toLowerCase()))
                .collect(Collectors.toList());
    }
}
