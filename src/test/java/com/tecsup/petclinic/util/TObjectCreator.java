package com.tecsup.petclinic.util;

import com.tecsup.petclinic.domain.PetTO;
import com.tecsup.petclinic.domain.VetTO;
import com.tecsup.petclinic.entities.Pet;
import com.tecsup.petclinic.entities.Vet;

import java.util.ArrayList;
import java.util.List;

public class TObjectCreator {

	//obtener pet(listo)
	public static Pet getPet() {
		return new Pet(1,"Leo",1,1, null);
	}	
	//nuevo (listo)
	public static Pet newPet() {
		return new Pet(0,"Punky",1,1, null);
	}
	//creat nuevo (listo)
	public static Pet newPetCreated() {
		Pet pet = newPet();
		pet.setId(1000);
		return pet;
	}
	public static Pet newPetForUpdate() {
		return new Pet(0,"Bear",1,1,null);
	}
	public static Pet newPetCreatedForUpdate() {
		Pet pet = newPetForUpdate();
		pet.setId(4000);
		return pet;
	}
	public static Pet newPetForDelete() {
		return new Pet(0,"Bird",1,1, null);
	}
	public static Pet newPetCreatedForDelete() {
		Pet pet = newPetForDelete();
		pet.setId(2000);
		return pet;
	}
	public static List<PetTO> getAllPetTOs() {
		List<PetTO> petTOs  = new ArrayList<PetTO>();
		petTOs.add(new PetTO(1,"Leo",1,1, "2000-09-07"));
		petTOs.add(new PetTO(2,"Basil",6,2, "2002-08-06"));
		petTOs.add(new PetTO(3,"Rosy",2,3, "2001-04-17"));
		petTOs.add(new PetTO(4,"Jewel",2,3, "2000-03-07"));
		petTOs.add(new PetTO(5,"Iggy",3,4, "2000-11-30"));
		return petTOs;
	}
	public static List<Pet> getPetsForFindByName() {
		List<Pet> pets  = new ArrayList<Pet>();
		pets.add(new Pet(1,"Leo",1,1, null));
		return pets;
	}
	public static List<Pet> getPetsForFindByTypeId() {
		List<Pet> pets  = new ArrayList<Pet>();
		pets.add(new Pet(9,"Lucky",5,7, null));
		pets.add(new Pet(11,"Freddy",5,9, null));
		return pets;
	}
	public static List<Pet> getPetsForFindByOwnerId() {
		List<Pet> pets  = new ArrayList<Pet>();
		pets.add(new Pet(12,"Lucky",2,10, null));
		pets.add(new Pet(13,"Sly",1,10, null));
		return pets;
	}
	public static PetTO getPetTO() {
		return new PetTO(1,"Leo",1,1, "2000-09-07");
	}
	public static PetTO newPetTO() {
		return new PetTO(-1,"Beethoven",1,1, "2020-05-20");
	}
	public static PetTO newPetTOForDelete() {
		return new PetTO(10000,"Beethoven3",1,1, "2020-05-20");
	}
	//VET(listo)
	public static Vet getVet() {
		return new Vet(1,"James","Carter");
	}
	//(listo)
	public static Vet newVet() {
		return new Vet(0,"Pedro","julian");
	}
	//(listo)
	public static Vet newVetCreated() {
		Vet vet = newVet();
		vet.setId(1000);
		return vet;
	}

	public static Vet newVetForUpdate() {
		return new Vet(0,"jorge","Wester");
	}
	public static Vet newVetCreatedForUpdate() {
		Vet vet = newVetForUpdate();
		vet.setId(4000);
		return vet;
	}
	//duda
	public static Vet newVetForDelete() {
		return new Vet(0,"Walter","Tornt");
	}
	public static Vet newVetCreatedForDelete() {
		Vet vet = newVetForDelete();
		vet.setId(2000);
		return vet;
	}
	public static List<VetTO> getAllVetTOs() {
		List<VetTO> vetTOs  = new ArrayList<VetTO>();
		vetTOs.add(new VetTO(1,"James","Carter"));
		vetTOs.add(new VetTO(2,"Helen","Leary"));
		vetTOs.add(new VetTO(3,"Linda","Douglas"));
		vetTOs.add(new VetTO(4,"Rafael","Ortega"));
		vetTOs.add(new VetTO(5,"Henry","Stevens"));
		return vetTOs;
	}
	public static List<Vet> getVetsForFindByFirstName() {
		List<Vet> vets  = new ArrayList<Vet>();
		vets.add(new Vet(1,"James","Carter"));
		return vets;
	}
	public static List<Vet> getVetsForFindByLastName() {
		List<Vet> vets  = new ArrayList<Vet>();
		vets.add(new Vet(5,"Henry","Stevens"));
		return vets;
	}
	public static VetTO getVetTO() {
		return new VetTO(1,"James","Carter");
	}
	public static VetTO newVetTO() {
		return new VetTO(-1,"Marcos","Miguel");
	}
	public static VetTO newVetTOForDelete() {
		return new VetTO(10000,"Beethoven3","Miguel");
	}

}
