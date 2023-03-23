package it.softwareInside.ApiAPiacereLezione23bis.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data

@Entity
public class Incantesimo {
	@Id
	private String id;

	private String name;
	private String incantation;
	private String effect;
	private boolean canBeVerbal;
	private String type;
	private String creator;

	public Incantesimo(String name, String incantation, String effect, 
			boolean canBeVerbal, String type, String creator) {
		setName(name);
		setIncantation(incantation);
		setEffect(effect);
		setCanBeVerbal(canBeVerbal);
		setType(type);
		setCreator(creator);
	}
}
