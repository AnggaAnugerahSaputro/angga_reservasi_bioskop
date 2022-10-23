package org.binar.bioskop.challenge4.service;


import net.sf.jasperreports.engine.JRException;
import org.binar.bioskop.challenge4.request.FileDataDB;

import java.io.FileNotFoundException;


public interface InvoiceService {

    FileDataDB generateFileInvoice(String filename) throws JRException, FileNotFoundException;
}
