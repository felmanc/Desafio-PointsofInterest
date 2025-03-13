package br.com.felmanc.pointsofinterest.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.felmanc.pointsofinterest.dtos.PointOfInterestDTO;
import br.com.felmanc.pointsofinterest.services.PointOfInterestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/poi")
@Tag(name = "PointOfInterest", description = "Endpoints para retornar os pontos dentro de uma determinada região")
public class PointOfInterestController {

    private PointOfInterestService pointOfInterestService;

    public PointOfInterestController(PointOfInterestService pointOfInterestService) {
		this.pointOfInterestService = pointOfInterestService;
	}

    @Operation(summary = "Exibir estatísticas das transações")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Estatísticas calculadas com sucesso")
    })    
    @GetMapping
    public List<PointOfInterestDTO> getPointsOfInterest(
            @RequestParam("x") Long xReferencia,
            @RequestParam("y") Long yReferencia,
            @RequestParam("dMax") Double distMax) {
        return pointOfInterestService.getPointsOfInterestDTO(xReferencia, yReferencia, distMax);
    }

}
