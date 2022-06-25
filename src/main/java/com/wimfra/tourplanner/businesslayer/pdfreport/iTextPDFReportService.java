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
import com.wimfra.tourplanner.models.Tour;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;

public class iTextPDFReportService implements PDFReportService{
    private final ManageTourLogService tourLogService = new ManageTourLogServiceImpl();
    private final ManageTourService tourService = new ManageTourServiceImpl();
    private final ParserService parserService = new ParserServiceImpl();
    public static final String PDF_TARGET_SUMMARIZE = "./reports/summarized_reports/summarized_tour_report.pdf";
    public static final String PDF_TARGET_TOUR = "./reports/tour_reports/tour_report.pdf";

    @Override
    public void generateSummarizeReport() {
        Document report = createNewPDFFile(PDF_TARGET_SUMMARIZE);
        if(report != null){
            try {
                report.add(generateTableHeader());
                final var tourList = tourService.getTours();
                Table table = setupTableForSummarizeReport();
                for (Tour tour : tourList) {
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
        Document report = createNewPDFFile(PDF_TARGET_TOUR);
        if(report != null){
            try {
                report.add(generateTourHeader());
                report.add(new AreaBreak());
                final var tourList = tourService.getTours();
                Table table = setupTableForSummarizeReport();
                for (Tour tour : tourList) {
                    table.addCell(String.valueOf(tour.getTour_name()));
                    table.addCell(getAverageTime(tour));
                    table.addCell(String.valueOf(tour.getDistance()));
                    table.addCell(String.valueOf(getAverageRating(tour)));
                }
                report.add(table);
               // report.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
        ImageData imageData = null;
        try {
            imageData = ImageDataFactory.create(new URL("https://www.technikum-wien.at/sites/default/files/logo-300x160.png"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        document.add(new Image(imageData));
    }

    private Table setupTableForSummarizeReport() {
        Table table = new Table(UnitValue.createPercentArray(4)).useAllAvailableWidth();
        table.setFontSize(14).setBackgroundColor(ColorConstants.WHITE);
        table.addHeaderCell(getHeaderCell("Name"));
        table.addHeaderCell(getHeaderCell("Average Time"));
        table.addHeaderCell(getHeaderCell("Distance"));
        table.addHeaderCell(getHeaderCell("Rating"));
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
                .setFontSize(26)
                .setBold()
                .setFontColor(ColorConstants.BLUE);
    }

    private String getAverageTime(Tour tour) {
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

    private String getAverageRating(Tour tour) {
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
}