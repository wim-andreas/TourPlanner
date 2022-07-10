package com.wimfra.tourplanner.viewmodel;

import com.wimfra.tourplanner.businesslayer.JavaAppManager;
import com.wimfra.tourplanner.businesslayer.JavaAppManagerFactory;
import com.wimfra.tourplanner.businesslayer.ManageTourService;
import com.wimfra.tourplanner.businesslayer.ManageTourServiceImpl;
import com.wimfra.tourplanner.logger.ILoggerWrapper;
import com.wimfra.tourplanner.logger.LoggerFactory;
import com.wimfra.tourplanner.mediator.Mediator;
import com.wimfra.tourplanner.mediator.MediatorFactory;
import com.wimfra.tourplanner.models.TourModel;
import com.wimfra.tourplanner.viewmodel.observerpattern.Publisher;
import com.wimfra.tourplanner.viewmodel.observerpattern.ViewModel;
import javafx.beans.property.*;
import javafx.scene.image.Image;

public class RouteViewModel implements ViewModel {
    // gets the connection to the business layer
    private JavaAppManager manager = JavaAppManagerFactory.GetManager();
    private static final ILoggerWrapper logger = LoggerFactory.getLogger(RouteViewModel.class);
    private ManageTourService tourService = new ManageTourServiceImpl();
    private Publisher publisher;
    private final Mediator mediator = MediatorFactory.getMediator();

    // different properties for bindings
    public final StringProperty description = new SimpleStringProperty();
    public final ObjectProperty<Image> imageProperty = new SimpleObjectProperty<>();

    // different actions - communication with business and data access layer
    public TourModel getSingleTour(int id) {
        return tourService.getSingleTour(id);
    }

    // different getters and setters
    public StringProperty getDescriptionProperty(){
        return this.description;
    }

    public  ObjectProperty<Image> getImageProperty() {
        return this.imageProperty;
    }

    public void fetchCurrentDescription(int id) {
        TourModel tour = getSingleTour(id);
        if (tour != null) {
            String currentDescription = "Tourname: " + tour.getTour_name() + "\n\n" +
                    "Description: " + tour.getDescription() + "\n\n" +
                    "From: " + tour.getFrom_where() + "\n\n" +
                    "To: " + tour.getTo_where() + "\n\n" +
                    "Transportation: " + tour.getTransportation() + "\n\n" +
                    "Distance(km): " + tour.getDistance() + "\n\n" +
                    "Duration(min): " + tour.getDuration() + "\n\n" +
                    "Info: " + tour.getRoute_info() + "\n\n";
            description.setValue("");
            description.setValue(currentDescription);
        }
        return;
    }

    public void fetchImage(int id) {
        if(getClass().getResourceAsStream("/images/" + id + ".jpg") != null){
            Image image =  new Image((getClass().getResourceAsStream("/images/" + id + ".jpg")));
            imageProperty.setValue(image);
        }
        return;
    }

    // Observer pattern methods
    @Override
    public void updateFromDB() {
        fetchCurrentDescription(mediator.getTourID());
        if(mediator.getTourID() != 0){
            fetchImage(mediator.getTourID());
        }
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }



}
