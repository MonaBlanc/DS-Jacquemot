package fr.polytech.cours.controller;

import fr.polytech.cours.dto.request.AddEvaluationDto;
import fr.polytech.cours.dto.response.EvaluationDto;
import fr.polytech.cours.service.EvaluationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@Slf4j
public class EvaluationController {

    private final EvaluationService evaluationService;

    @PostMapping("/restaurants/{restaurantId}/evaluations")
    public EvaluationDto addEvaluation(@PathVariable Integer restaurantId, @Valid @RequestBody AddEvaluationDto addEvaluationDto) {
        return EvaluationDto.buildFromEntity(this.evaluationService.addEvaluationToRestaurant(restaurantId, addEvaluationDto));
    }

    @GetMapping("/restaurants/{restaurantId}/evaluations")
    public List<EvaluationDto> getEvaluations(@PathVariable Integer restaurantId) {
        return this.evaluationService.getEvaluations().stream().map(evaluationEntity -> EvaluationDto.buildFromEntity(evaluationEntity)).toList();
    }
}
