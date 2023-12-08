package fr.polytech.cours.service;

import fr.polytech.cours.dto.request.AddEvaluationFinaleDto;
import fr.polytech.cours.dto.response.EvaluationFinaleDto;
import fr.polytech.cours.entity.EvaluationFinaleEntity;
import fr.polytech.cours.entity.RestaurantEntity;
import fr.polytech.cours.exception.ResourceNotFoundException;
import fr.polytech.cours.repository.EvaluationFinaleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class EvaluationFinaleService {

    private final EvaluationFinaleRepository evaluationFinaleRepository;
    private final RestaurantService restaurantService;

    public EvaluationFinaleEntity getEvaluationFinale(final Integer restaurantId) {
        final RestaurantEntity restaurant = this.restaurantService.getRestaurantById(restaurantId);
        return restaurant.getEvaluationFinale();
    }

    public EvaluationFinaleEntity getEvaluationFinaleById(final Integer id) {
        return this.evaluationFinaleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Evaluation finale with id " + id + " + not found"));
    }

    public EvaluationFinaleEntity addEvaluationFinaleToRestaurant(final Integer restaurantId, final AddEvaluationFinaleDto evaluationFinaleDto) {
        final RestaurantEntity restaurant = this.restaurantService.getRestaurantById(restaurantId);

        final EvaluationFinaleEntity evaluationFinaleToInsert = EvaluationFinaleEntity.builder()
                .nom(evaluationFinaleDto.getNom())
                .description(evaluationFinaleDto.getDescription())
                .noteFinale(evaluationFinaleDto.getNoteFinale())
                .build();

        restaurant.setEvaluationFinale(evaluationFinaleToInsert);

        this.evaluationFinaleRepository.save(evaluationFinaleToInsert);
        this.restaurantService.saveRestaurant(restaurant);

        return evaluationFinaleToInsert;
    }

    public void deleteEvaluationFinale(final Integer id) {
        this.evaluationFinaleRepository.deleteById(id);
    }

}
