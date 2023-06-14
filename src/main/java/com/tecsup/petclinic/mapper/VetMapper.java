package com.tecsup.petclinic.mapper;

import com.tecsup.petclinic.domain.VetTO;
import com.tecsup.petclinic.entities.Vet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.text.ParseException;
import java.text.AttributedString;
import java.util.Date;
import java.util.List;

@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface VetMapper {

    VetMapper INSTANCE = Mappers.getMapper(VetMapper.class);


    @Mapping(source = "id", target = "id")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")



    Vet toVet(VetTO vetTO);
    List<Vet> toVetList(List<VetTO> vetTOList);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    VetTO toVetTO(Vet vet);
    default String stringToDate(String nombreStr) {

    	String nombre = null;
    	
    	AttributedString stringFormat = new AttributedString("");
		try {
			nombre = dateFormat.parse(nombreStr);
	} catch (ParseException e) {
			e.printStackTrace();
		}

		return nombre;
	}
    VetTO toVetTO(Vet pet);
    
    List<VetTO> toVetTOList(List<Vet> vetList);
}