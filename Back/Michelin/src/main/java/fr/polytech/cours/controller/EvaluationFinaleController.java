package fr.polytech.cours.controller;

import fr.polytech.cours.dto.request.AddEvaluationFinaleDto;
import fr.polytech.cours.dto.response.EvaluationFinaleDto;
import fr.polytech.cours.service.EvaluationFinaleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@Slf4j
public class EvaluationFinaleController {
    private final EvaluationFinaleService evaluationFinaleService;



    @GetMapping("/restaurants/{restaurantId}/evaluationfinale")
    public EvaluationFinaleDto getEvaluationFinale(@PathVariable Integer restaurantId) {
        return EvaluationFinaleDto.buildFromEntity(this.evaluationFinaleService.getEvaluationFinale(restaurantId));
    }

    @PostMapping("/restaurants/{restaurantId}/evaluationfinale")
    public EvaluationFinaleDto AddEvaluationFinale(@PathVariable Integer restaurantId, @Valid @RequestBody AddEvaluationFinaleDto addEvaluationFinaleDto) {
        return EvaluationFinaleDto.buildFromEntity(this.evaluationFinaleService.addEvaluationFinaleToRestaurant(restaurantId, addEvaluationFinaleDto));
    }

    @DeleteMapping("/restaurants/{restaurantId}/evaluationfinale")
    public void deleteEvaluationFinale(@PathVariable Integer restaurantId) {
        this.evaluationFinaleService.deleteEvaluationFinale(restaurantId);
    }

}