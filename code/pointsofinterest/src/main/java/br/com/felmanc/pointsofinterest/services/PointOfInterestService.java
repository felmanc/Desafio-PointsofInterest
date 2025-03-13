package br.com.felmanc.pointsofinterest.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.felmanc.pointsofinterest.dtos.PointOfInterestDTO;
import br.com.felmanc.pointsofinterest.entities.PointOfInterestEntity;
import br.com.felmanc.pointsofinterest.repositories.PointOfInterestRepository;

@Service
public class PointOfInterestService {

    @Autowired
    private PointOfInterestRepository pointOfInterestRepository;

    public List<PointOfInterestDTO> getPointsOfInterestDTO(Long xReferencia, Long yReferencia, Double dMax) {
        List<PointOfInterestEntity> points = pointOfInterestRepository.findWithinDistance(xReferencia, yReferencia, dMax);
        return points.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private PointOfInterestDTO convertToDTO(PointOfInterestEntity point) {
        return new PointOfInterestDTO(point.getId(), point.getNome(), point.getX(), point.getY());
    }
}

