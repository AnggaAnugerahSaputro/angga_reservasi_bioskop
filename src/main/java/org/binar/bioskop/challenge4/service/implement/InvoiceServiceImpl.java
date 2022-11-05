package org.binar.bioskop.challenge4.service.implement;

import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import org.binar.bioskop.challenge4.request.FileDataDB;
import org.binar.bioskop.challenge4.service.InvoiceService;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.HashMap;

@Slf4j
@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Override
    public FileDataDB generateFileInvoice(String filename) throws  JRException{

             HashMap<String, Object> map = new HashMap<>();
             map.put("name", "Angga");
             map.put("film", "One Piece RED 2022");
             map.put("showdate","2022-09-01");
             map.put("startTime", "17:00:00");
             map.put("endTime", "19:00:00");
             map.put("seatRow", "B");
             map.put("seatNumber", "1");
             map.put("studioName", "A");

        InputStream employeeReportStream = getClass().getResourceAsStream("/BioskopJasper.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(employeeReportStream);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map);
        byte[] pdfFile = JasperExportManager.exportReportToPdf(jasperPrint);
        FileDataDB fileDataDB = new FileDataDB();
        fileDataDB.setData(pdfFile);
        fileDataDB.setFilename(filename);
        fileDataDB.setFileType("application/pdf");
        return fileDataDB;

    }


}

