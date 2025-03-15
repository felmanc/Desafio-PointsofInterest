package br.com.felmanc.pointsofinterest.services;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.felmanc.pointsofinterest.dtos.PointOfInterestDTO;
import br.com.felmanc.pointsofinterest.entities.PointOfInterestEntity;
import br.com.felmanc.pointsofinterest.exceptions.TransacaoInvalidaException;
import br.com.felmanc.pointsofinterest.repositories.PointOfInterestRepository;

@Service
public class PointOfInterestService {

    private static final Logger logger = LoggerFactory.getLogger(PointOfInterestService.class);

    private PointOfInterestRepository pointOfInterestRepository;

    public PointOfInterestService(PointOfInterestRepository pointOfInterestRepository) {
        this.pointOfInterestRepository = pointOfInterestRepository;
    }

    public List<PointOfInterestDTO> getPointsOfInterestDTO(Long xReferencia, Long yReferencia, Double dMax) {
        logger.info("Obtendo pontos de interesse com referência: x={}, y={}, distância máxima={}", xReferencia, yReferencia, dMax);
        List<PointOfInterestEntity> points = pointOfInterestRepository.findWithinDistance(xReferencia, yReferencia, dMax);
        logger.debug("Número de pontos encontrados: {}", points.size());
        return points.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private PointOfInterestDTO convertToDTO(PointOfInterestEntity point) {
        logger.debug("Convertendo PointOfInterestEntity para PointOfInterestDTO: {}", point.getNome());
        return new PointOfInterestDTO(point.getNome(), point.getX(), point.getY());
    }

    public PointOfInterestEntity addPointOfInterest(PointOfInterestDTO poiDTO) {
        logger.info("Adicionando um novo ponto de interesse: {}", poiDTO.getNome());

        // Verificação de valores negativos para x ou y
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

