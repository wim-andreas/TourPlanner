package com.wimfra.tourplanner.viewmodel;

import com.wimfra.tourplanner.businesslayer.JavaAppManager;
import com.wimfra.tourplanner.businesslayer.JavaAppManagerFactory;
import com.wimfra.tourplanner.businesslayer.ManageTourService;
import com.wimfra.tourplanner.businesslayer.ManageTourServiceImpl;
import com.wimfra.tourplanner.logger.ILoggerWrapper;
import com.wimfra.tourplanner.logger.LoggerFactory;
import com.wimfra.tourplanner.mediator.Mediator;
import com.wimfra.tourplanner.mediator.MediatorFactory;
import com.wimfra.tourplanner.models.Tour;
import com.wimfra.tourplanner.viewmodel.observerpattern.Publisher;
import com.wimfra.tourplanner.viewmodel.observerpattern.ViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class RouteViewModel implements ViewModel {
    // gets the connection to the business layer
    private JavaAppManager manager = JavaAppManagerFactory.GetManager();
    private static final ILoggerWrapper logger = LoggerFactory.getLogger(RouteViewModel.class);
    private ManageTourService tourService = new ManageTourServiceImpl();
    private Publisher publisher;
    private final Mediator mediator = MediatorFactory.getMediator();

    // different properties for bindings
    public final StringProperty description = new SimpleStringProperty();

    // different actions - communication with business and data access layer
    public Tour getSingleTour(int id) {
        return tourService.getSingleTour(id);
    }

    // different getters and setters
    public StringProperty getDescriptionProperty(){
        return this.description;
    }

    public void fetchCurrentDescription(int id) {
        Tour tour = getSingleTour(id);
        if (tour != null) {
            String currentDescription = "Tourname: " + tour.getTour_name() + "\n" +
                    "Description: " + tour.getDescription() + "\n" +
                    "From: " + tour.getFrom_where() + "\n" +
                    "To: " + tour.getTo_where() + "\n" +
                    "Transportation: " + tour.getTransportation() + "\n" +
                    "Distance: " + tour.getDistance() + "\n" +
                    "Duration: " + tour.getDuration() + "\n" +
                    "Info: " + tour.getRoute_info() + "\n";
            description.setValue("");
            description.setValue(currentDescription);
        }
        return;
    }

    // Observer pattern methods
    @Override
    public void updateFromDB() {
        fetchCurrentDescription(mediator.getTourID());
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }
}
