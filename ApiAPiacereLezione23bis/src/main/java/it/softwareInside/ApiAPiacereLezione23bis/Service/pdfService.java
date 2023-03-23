package it.softwareInside.ApiAPiacereLezione23bis.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.springframework.beans.factory.annotation.Autowired;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

@org.springframework.stereotype.Service
public class pdfService {

	@Autowired
	Service service;

	/**
	 * Questo metodo serve per stampare un incantesimo PDF tramite id un
	 * incantesimo.
	 * 
	 * @param id
	 * @return
	 */
	public ByteArrayInputStream generaPDFInca(String id) {

		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {

			Paragraph contenuto = new Paragraph();

			contenuto.add(service.cercaIncant(id).toString());

			PdfWriter.getInstance(document, out);
			document.open();
			document.add(contenuto);
			document.close();

		} catch (DocumentException ex) {

			return null;
		}

		return new ByteArrayInputStream(out.toByteArray());
	}

	/**
	 * Questo metodo serve per stampare un PDF dove all'interno c'è tutto il
	 * Database di incantesimi.
	 * 
	 * @param id
	 * @return
	 */

	public ByteArrayInputStream generaPdfData() {

		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {

			Paragraph contenuto = new Paragraph();

			contenuto.add(service.findAllIn().toString());

			PdfWriter.getInstance(document, out);
			document.open();
			document.add(contenuto);
			document.close();

		} catch (DocumentException ex) {

			return null;
		}

		return new ByteArrayInputStream(out.toByteArray());
	}

}
