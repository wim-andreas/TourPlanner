package com.wimfra.tourplanner.businesslayer.pdfreport;

import com.itextpdf.layout.Document;

public interface PDFReportService {
    void generateSummarizeReport();
    void generateTourReport();
    Document createNewPDFFile(String pdf_target);
}
