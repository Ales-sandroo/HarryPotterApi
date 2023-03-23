package it.softwareInside.ApiAPiacereLezione23bis.Service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import it.softwareInside.ApiAPiacereLezione23bis.Models.Incantesimo;
import it.softwareInside.ApiAPiacereLezione23bis.Repository.IncantesimoRepository;

@org.springframework.stereotype.Service
public class Service {

	@Autowired
	IncantesimoRepository incantesimoRepository;

	/**
	 * Questo metodo genera un incantesimo che viene resistuito all'API.
	 * 
	 * @param id
	 * @return
	 */
	public Incantesimo[] generate() {
		RestTemplate rest = new RestTemplate();
		ResponseEntity<Incantesimo[]> incantesimo = rest.getForEntity("https://wizard-world-api.herokuapp.com/Spells/",
				Incantesimo[].class);
		return incantesimo.getBody();
	}

	/**
	 * Questo metodo restituisce la quantità di incantesimi esistenti nell'API.
	 * 
	 * @param id
	 * @return
	 */
	public int quantita() {
		int quantita = generate().length;
		return quantita;
	}

	/**
	 * Questo metodo aggiunge un incantesimo random nel nostro database tra tutti
	 * gli incantesimi presenti.
	 * 
	 * @param id
	 * @return
	 */
	public String add() {
		try {
			Random random = new Random();
			incantesimoRepository.save(generate()[random.nextInt(1, quantita())]);
			return "incantesimo aggiunto";
		} catch (Exception e) {
			return "incantesimo non aggiunto";
		}

	}

	/**
	 * Questo metodo controlla se nel database esiste già quell'incantesimo.
	 * 
	 * @param id
	 * @return
	 */
	public boolean isIdHere(String id) {
		for (Incantesimo elemento : findAllIn()) {
			if (elemento.getId().equals(id))
				return true;
		}
		return false;
	}

	/**
	 * Questo metodo cerca un incantesimo per id nel nostro database.
	 * 
	 * @param id
	 * @return
	 */
	public Incantesimo cercaIncant(String id) {
		try {
			return incantesimoRepository.findById(id).get();

		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	/**
	 * Questo metodo serve ad eliminare un incantesimo per id nel nostro database.
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteIncantById(String id) {
		try {
			incantesimoRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	/**
	 * Questo metodo fa vedere a video tutti gli incantesimi esistenti nel nostro
	 * database.
	 * 
	 * @param id
	 * @return
	 */
	public Iterable<Incantesimo> findAllIn() {
		try {

			return incantesimoRepository.findAll();
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

	}
}
