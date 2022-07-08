package com.wimfra.tourplanner.businesslayer.pdfreport;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.UnitValue;
import com.wimfra.tourplanner.businesslayer.ManageTourLogService;
import com.wimfra.tourplanner.businesslayer.ManageTourLogServiceImpl;
import com.wimfra.tourplanner.businesslayer.ManageTourService;
import com.wimfra.tourplanner.businesslayer.ManageTourServiceImpl;
import com.wimfra.tourplanner.businesslayer.parsing.ParserService;
import com.wimfra.tourplanner.businesslayer.parsing.ParserServiceImpl;
import com.wimfra.tourplanner.models.TourModel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;

public class iTextPDFReportService implements PDFReportService{
    private final ManageTourLogService tourLogService = new ManageTourLogServiceImpl();
    private final ManageTourService tourService = new ManageTourServiceImpl();
    private final ParserService parserService = new ParserServiceImpl();
    public static final String PDF_TARGET_SUMMARIZE = "./reports/summarized_reports/summarized_tour_report_";
    public static final String PDF_TARGET_TOUR = "./reports/tour_reports/tour_report_";
    public static final String PDF = ".pdf";


    @Override
    public void generateSummarizeReport() {
        String filePath = PDF_TARGET_SUMMARIZE + LocalDate.now() + getCurrentTimeValue() + PDF;
        Document report = createNewPDFFile(filePath);
        if(report != null){
            try {
                report.add(generateTableHeader());
                final var tourList = tourService.getTours();
                Table table = setupTableForSummarizeReport();
                for (TourModel tour : tourList) {
                    table.addCell(String.valueOf(tour.getTour_name()));
                    table.addCell(getAverageTime(tour));
                    table.addCell(String.valueOf(tour.getDistance()));
                    table.addCell(String.valueOf(getAverageRating(tour)));
                }
                report.add(table);
                report.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void generateTourReport(int tourID) {
        String filePath = PDF_TARGET_TOUR + LocalDate.now() + getCurrentTimeValue() + PDF;
        Document report = createNewPDFFile(filePath);
        if(report != null){
            try {
                // Setting up the tour table with the sufficient header
                report.add(generateTourHeader());
                final var tour = tourService.getSingleTour(tourID);
                Table tourTable = setupTableForTourReport();
                tourTable.addCell(String.valueOf(tour.getTour_name()));
                tourTable.addCell(getTourDescription(tour));
                report.add(tourTable);

                // Setting up the tourlog table with the sufficient header
                report.add(generateTourSecondHeader());
                Table tourLogTable = setupTableForTourReportWithLogs();
                final var tourLogs = tourLogService.getAllLogsFromSingleTour(tourID);
                tourLogs.forEach(logModel -> {
                    tourLogTable.addCell(String.valueOf(logModel.getDate()));
                    tourLogTable.addCell(String.valueOf(logModel.getTime()));
                    tourLogTable.addCell(String.valueOf(logModel.getDifficulty()));
                    tourLogTable.addCell(String.valueOf(logModel.getComment()));
                    tourLogTable.addCell(String.valueOf(logModel.getRating()));
                    tourLogTable.addCell(String.valueOf(logModel.getTotalTime()));
                });
                report.add(tourLogTable);
                addTourImageToReport(tourID, report);
                report.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String getTourDescription(TourModel tour) {
        if(tour != null){
            String currentDescription = "Tourname: " + tour.getTour_name() + "\n" +
                    "Description: " + tour.getDescription() + "\n" +
                    "From: " + tour.getFrom_where() + "\n" +
                    "To: " + tour.getTo_where() + "\n" +
                    "Transportation: " + tour.getTransportation() + "\n" +
                    "Distance: " + tour.getDistance() + "\n" +
                    "Duration: " + tour.getDuration() + "\n" +
                    "Info: " + tour.getRoute_info() + "\n";
            return currentDescription;
        }
        return "No tour available!";
    }

    @Override
    public Document createNewPDFFile(String pdf_target) {
        try {
            PdfWriter pdfWriter = new PdfWriter(pdf_target);
            PdfDocument pdf = new PdfDocument(pdfWriter);
            Document document = new Document(pdf);
            generateReportHeader(document);
            return document;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void generateReportHeader(Document document){
        ImageData imageData = null; //https://htw.wien/wp-content/uploads/2018/06/fhtw-logo.png
        try {
            imageData = ImageDataFactory.create(new URL("https://upload.wikimedia.org/wikipedia/commons/d/db/Logo_FH_Technikum_Wien.jpg"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        document.add(new Image(imageData));
    }

    private void addTourImageToReport(int tourID, Document document){
        ImageData imageData = null; // here the image of the tour from the mapQuest API will be added.

        try {
            imageData = ImageDataFactory.create("src/main/resources/images/" + tourID + ".jpg");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        document.add(new AreaBreak());
        document.add(new Image(imageData));
    }

    private Table setupTableForSummarizeReport() {
        Table table = new Table(UnitValue.createPercentArray(4)).useAllAvailableWidth();
        table.setFontSize(14).setBackgroundColor(ColorConstants.WHITE);
        table.addHeaderCell(getHeaderCell("Name"));
        table.addHeaderCell(getHeaderCell("Average Time"));
        table.addHeaderCell(getHeaderCell("Distance(km)"));
        table.addHeaderCell(getHeaderCell("Rating"));
        return table;
    }

    private Table setupTableForTourReport() {
        Table table = new Table(UnitValue.createPercentArray(4)).useAllAvailableWidth();
        table.setFontSize(14).setBackgroundColor(ColorConstants.WHITE);
        table.addHeaderCell(getHeaderCell("Tourname"));
        table.addHeaderCell(getHeaderCell("Tour description")).setWidth(1000);
        return table;
    }

    private Table setupTableForTourReportWithLogs() {
        Table table = new Table(UnitValue.createPercentArray(6)).useAllAvailableWidth();
        table.setFontSize(14).setBackgroundColor(ColorConstants.WHITE);
        table.addHeaderCell(getHeaderCell("Date"));
        table.addHeaderCell(getHeaderCell("Time"));
        table.addHeaderCell(getHeaderCell("Difficulty"));
        table.addHeaderCell(getHeaderCell("Comment"));
        table.addHeaderCell(getHeaderCell("Rating"));
        table.addHeaderCell(getHeaderCell("Total time"));
        return table;
    }

    private static Cell getHeaderCell(String s) {
        return new Cell().add(new Paragraph(s)).setItalic().setBackgroundColor(ColorConstants.GRAY);
    }

    private Paragraph generateTableHeader() throws IOException {
        return new Paragraph("TourPlanner: Overview of all currently saved Tours:")
                .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
                .setFontSize(20)
                .setItalic();
    }

    private Paragraph generateTourHeader() throws IOException {
        return new Paragraph("Tour Report from " + LocalDate.now())
                .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
                .setFontSize(20)
                .setItalic()
                .setFontColor(ColorConstants.GRAY);
    }

    private Paragraph generateTourSecondHeader() throws IOException {
        return new Paragraph("All existing logs from this tour: ")
                .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
                .setFontSize(20)
                .setItalic()
                .setFontColor(ColorConstants.GRAY);
    }

    private String getAverageTime(TourModel tour) {
        String returnValue;
        Double timesAdded = (double) 0;
        final var tourLogs = tourLogService.getAllLogsFromSingleTour(tour.getTour_id());
        if(tourLogs == null){
            returnValue = " - ";
        }
        else{
            for (var tourLog: tourLogs) {
                timesAdded = timesAdded + parserService.parseStringIntoDouble(tourLog.getTotalTime());
            }
            Double result = timesAdded / tourLogs.size();
            returnValue = parserService.parseDoubleIntoString(result);
            returnValue += " minute(s)";
        }

        return returnValue;
    }

    private String getAverageRating(TourModel tour) {
        String returnValue = "";
        final var tourLogs = tourLogService.getAllLogsFromSingleTour(tour.getTour_id());
        Double rating = (double) 0;
        if(tourLogs == null){
            returnValue = " - ";
        }
        else{
            for (var tourLog: tourLogs) {
                rating = rating + tourLog.getRating();
                Double finalValue = rating / tourLogs.size();
                returnValue = parserService.parseDoubleIntoString(finalValue);
                returnValue += " point(s)";
            }
        }
        return returnValue;
    }

    public String getCurrentTimeValue(){
        String currentValue = LocalTime.now().toString();
        String finalValue = "-";

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
