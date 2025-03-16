package br.com.felmanc.pointsofinterest.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.felmanc.pointsofinterest.dtos.PointOfInterestDTO;
import br.com.felmanc.pointsofinterest.entities.PointOfInterestEntity;
import br.com.felmanc.pointsofinterest.services.PointOfInterestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/poi")
@Tag(name = "PointOfInterest", description = "Endpoints para retornar os pontos dentro de uma determinada região")
public class PointOfInterestController {

	private static final Logger logger = LoggerFactory.getLogger(PointOfInterestController.class);
	
    private PointOfInterestService pointOfInterestService;

	public PointOfInterestController(PointOfInterestService pointOfInterestService) {
		this.pointOfInterestService = pointOfInterestService;
	}

    @Operation(summary = "Show nearest points of interest")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Estatísticas calculadas com sucesso")
    })    
    @GetMapping("/near")
    public List<PointOfInterestDTO> getPointsOfInterest(
            @RequestParam("x") Long xReferencia,
            @RequestParam("y") Long yReferencia,
            @RequestParam("dMax") Double distMax) {
        logger.info("Recebendo solicitação para listar pontos de interesse. Referência: x={}, y={}, distância máxima={}", 
                     xReferencia, yReferencia, distMax);
        List<PointOfInterestDTO> response = pointOfInterestService.getNearPointsOfInterestDTO(xReferencia, yReferencia, distMax);
        logger.info("Número de pontos de interesse retornados: {}", response.size());
        return response;
    }

    @Operation(summary = "Show all points of interest")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Estatísticas calculadas com sucesso")
    })    
    @GetMapping("/all")
    public List<PointOfInterestDTO> getAllPointsOfInterest() {
        logger.info("Recebendo solicitação para listar todos os pontos de interesse.");
        List<PointOfInterestDTO> response = pointOfInterestService.getAllPointsOfInterestDTO();
        logger.info("Número de pontos de interesse retornados: {}", response.size());
        return response;
    }
    
    @Operation(summary = "Create a new point of interest")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Created",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = PointOfInterestEntity.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input", 
                content = @Content),
        @ApiResponse(responseCode = "409", description = "Erro de duplicação de entrada", 
                content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "422", description = "Unprocessable Entity", 
                content = @Content(mediaType = "application/json"))
    })
    @PostMapping
    public ResponseEntity<Object> createPointOfInterest(@RequestBody PointOfInterestDTO poiDTO) {
        logger.info("Recebendo solicitação para criar um novo ponto de interesse: {}", poiDTO.getNome());
        PointOfInterestEntity createdPoi = pointOfInterestService.addPointOfInterest(poiDTO);
        logger.info("Novo ponto de interesse criado com sucesso. ID: {}", createdPoi.getId());
        return new ResponseEntity<>(createdPoi, HttpStatus.CREATED);
    }
}
