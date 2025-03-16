package br.com.felmanc.pointsofinterest.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.felmanc.pointsofinterest.dtos.PointOfInterestDTO;
import br.com.felmanc.pointsofinterest.entities.PointOfInterestEntity;

@Mapper(componentModel = "spring")
public interface PointOfInterestMapper {

    // Converte o DTO para a Entidade
	@Mapping(target = "id", ignore = true)
    PointOfInterestEntity toEntity(PointOfInterestDTO dto);

    // Converte a Entidade para o DTO
    PointOfInterestDTO toDto(PointOfInterestEntity entity);
}

