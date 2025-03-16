package br.com.felmanc.pointsofinterest.services;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.felmanc.pointsofinterest.dtos.PointOfInterestDTO;
import br.com.felmanc.pointsofinterest.entities.PointOfInterestEntity;
import br.com.felmanc.pointsofinterest.exceptions.TransacaoInvalidaException;
import br.com.felmanc.pointsofinterest.mappers.PointOfInterestMapper;
import br.com.felmanc.pointsofinterest.repositories.PointOfInterestRepository;

@Service
public class PointOfInterestService {

    private static final Logger logger = LoggerFactory.getLogger(PointOfInterestService.class);

    private PointOfInterestRepository pointOfInterestRepository;

    private PointOfInterestMapper pointOfInterestMapper;
    
    public PointOfInterestService(PointOfInterestRepository pointOfInterestRepository,
			PointOfInterestMapper pointOfInterestMapper) {
		this.pointOfInterestRepository = pointOfInterestRepository;
		this.pointOfInterestMapper = pointOfInterestMapper;
	}

    public List<PointOfInterestDTO> getPointsOfInterestDTO(Long xReferencia, Long yReferencia, Double dMax) {
        logger.info("Obtendo pontos de interesse com referência: x={}, y={}, distância máxima={}", xReferencia, yReferencia, dMax);

        // Validação para garantir que xReferencia, yReferencia e dMax não sejam nulos
        if (xReferencia == null || yReferencia == null || dMax == null) {
            String errorMessage = "xReferencia, yReferencia e dMax não podem ser nulos.";
            logger.error(errorMessage);
            throw new TransacaoInvalidaException(errorMessage);
        }

        List<PointOfInterestEntity> points = pointOfInterestRepository.findWithinDistance(xReferencia, yReferencia, dMax);
        logger.debug("Número de pontos encontrados: {}", points.size());
        
        return points.stream().map(pointOfInterestMapper::toDto).collect(Collectors.toList());
    }

    public PointOfInterestEntity addPointOfInterest(PointOfInterestDTO poiDTO) {
        logger.info("Adicionando um novo ponto de interesse: {}", poiDTO.getNome());

        if (poiDTO.getX() == null || poiDTO.getY() == null) {
            String errorMessage = "As coordenadas X e Y não podem ser nulas.";
            logger.error(errorMessage);
            throw new TransacaoInvalidaException(errorMessage);
        }

        if (poiDTO.getNome() == null || poiDTO.getNome().isEmpty()) {
            String errorMessage = "O nome do ponto de interesse não pode ser nulo ou vazio.";
            logger.error(errorMessage);
            throw new TransacaoInvalidaException(errorMessage);
        }

        if (poiDTO.getX() < 0 || poiDTO.getY() < 0) {
            String errorMessage = String.format("Não é permitido adicionar pontos de interesse com coordenadas negativas: x=%d, y=%d", poiDTO.getX(), poiDTO.getY());
            logger.error(errorMessage);
            throw new TransacaoInvalidaException(errorMessage);
        }

        PointOfInterestEntity poiEntity = new PointOfInterestEntity();
        poiEntity.setNome(poiDTO.getNome());
        poiEntity.setX(poiDTO.getX());
        poiEntity.setY(poiDTO.getY());
        PointOfInterestEntity savedEntity = pointOfInterestRepository.save(poiEntity);
        logger.debug("Ponto de interesse salvo com ID: {}", savedEntity.getId());
        return savedEntity;
    }

}

