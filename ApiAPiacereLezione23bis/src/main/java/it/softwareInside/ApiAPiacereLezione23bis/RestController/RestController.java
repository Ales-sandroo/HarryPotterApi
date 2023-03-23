package it.softwareInside.ApiAPiacereLezione23bis.RestController;

import java.io.ByteArrayInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.softwareInside.ApiAPiacereLezione23bis.Models.Incantesimo;
import it.softwareInside.ApiAPiacereLezione23bis.Service.Service;

@org.springframework.web.bind.annotation.RestController
public class RestController {
	@Autowired
	Service service;
	@Autowired
	it.softwareInside.ApiAPiacereLezione23bis.Service.pdfService pdfService;

	@GetMapping("/add")
	public String add() {
		return service.add();
	}

	@GetMapping("/delete")
	public boolean deleteInca(@RequestParam("id") String id) {
		return service.deleteIncantById(id);
	}

	@GetMapping("/find-all")
	public Iterable<Incantesimo> findAll() {
		return service.findAllIn();
	}

	@RequestMapping(value = "/pdf", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> generaInca(@RequestParam("id") String id) {

		try {

			ByteArrayInputStream bis = pdfService.generaPDFInca(id);

			var headers = new HttpHeaders();
			headers.add("Content-Disposition", "inline; filename=nome.pdf");

			return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
					.body(new InputStreamResource(bis));

		} catch (Exception e) {
			return null;

		}

	}

	@RequestMapping(value = "/pdf-data", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> generaInca() {

		try {

			ByteArrayInputStream bis = pdfService.generaPdfData();

			var headers = new HttpHeaders();
			headers.add("Content-Disposition", "inline; filename=nome.pdf");

			return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
					.body(new InputStreamResource(bis));

		} catch (Exception e) {
			return null;

		}

	}
}
