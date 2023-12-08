package fr.polytech.cours.service;

import fr.polytech.cours.dto.response.RestaurantDto;
import fr.polytech.cours.entity.RestaurantEntity;
import fr.polytech.cours.exception.ResourceNotFoundException;
import fr.polytech.cours.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public List<RestaurantEntity> getRestaurants() {
        return this.restaurantRepository.findAll();
    }

    public RestaurantEntity getRestaurantById(final Integer id) {
        return this.restaurantRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Restaurant with id " + id + " + not found"));
    }

    public RestaurantEntity saveRestaurant(RestaurantEntity restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public RestaurantEntity addRestaurant(final RestaurantDto restaurantDto) {
        final RestaurantEntity restaurantToInsert = RestaurantEntity.builder().nom(restaurantDto.getNom()).adresse(restaurantDto.getAdresse()).evaluations(restaurantDto.getEvaluations()).moyenne(restaurantDto.getMoyenne()).evaluationFinale(restaurantDto.getEvaluationFinale()).photoRestaurant(restaurantDto.getPhotoRestaurant()).tags(restaurantDto.getTags()).build();
        return this.restaurantRepository.save(restaurantToInsert);
    }

    public void deleteRestaurant(final Integer id) {
        this.restaurantRepository.deleteById(id);
    }

}
