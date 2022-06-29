package com.wimfra.tourplanner.viewmodel;

import com.wimfra.tourplanner.businesslayer.pdfreport.PDFReportService;
import com.wimfra.tourplanner.businesslayer.pdfreport.iTextPDFReportService;
import com.wimfra.tourplanner.logger.ILoggerWrapper;
import com.wimfra.tourplanner.logger.LoggerFactory;
import com.wimfra.tourplanner.viewmodel.observerpattern.Publisher;
import com.wimfra.tourplanner.viewmodel.observerpattern.ViewModel;
import lombok.extern.log4j.Log4j2;

public class MenubarViewModel implements ViewModel {
    // gets the connection to the business layer
    private static final ILoggerWrapper logger = LoggerFactory.getLogger(MenubarViewModel.class);
    private PDFReportService pdfReportService = new iTextPDFReportService();
    private Publisher publisher;

    public void generateSummarizeReport() {
        pdfReportService.generateSummarizeReport();
        logger.debug("Summarized Tour Report has been created successfully!");
    }

    public void generateTourReport(int tourID) {
        pdfReportService.generateTourReport(tourID);
    }

    @Override
    public void updateFromDB() {

    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

}
