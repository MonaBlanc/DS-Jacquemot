package fr.polytech.cours.service;

import fr.polytech.cours.dto.request.AddEvaluationDto;
import fr.polytech.cours.entity.EvaluationEntity;
import fr.polytech.cours.entity.RestaurantEntity;
import fr.polytech.cours.repository.EvaluationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EvaluationService {

    private final EvaluationRepository evaluationRepository;
    private final RestaurantService restaurantService;

    public EvaluationEntity addEvaluationToRestaurant(final Integer restaurantId, final AddEvaluationDto dto) {
        final RestaurantEntity restaurant = this.restaurantService.getRestaurantById(restaurantId);
        final EvaluationEntity evaluation = EvaluationEntity.builder()
                .auteur(dto.getAuteur())
                .commentaire(dto.getCommentaire())
                .note(dto.getNote())
                .dateCreation(dto.getDateCreation())
                .dateModification(dto.getDateModification())
                .restaurant(restaurant)
                .photoAvis(dto.getPhotoAvis())
                .build();
        return this.evaluationRepository.save(evaluation);
    }

    public List<EvaluationEntity> getEvaluations() {
        return this.evaluationRepository.findAll();
    }
}
