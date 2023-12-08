package fr.polytech.cours.controller;

import fr.polytech.cours.dto.response.RestaurantDto;
import fr.polytech.cours.service.RestaurantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@Slf4j
public class RestaurantController {

    private final RestaurantService restaurantService;


    @GetMapping("/restaurants")
    public List<RestaurantDto> getRestaurants() {
            return this.restaurantService.getRestaurants().stream().map(restaurantEntity -> RestaurantDto.buildFromEntity(restaurantEntity)).toList();
    }

    @GetMapping("/restaurants/{id}")
    public RestaurantDto getRestaurantById(@PathVariable Integer id) {
        return RestaurantDto.buildFromEntity(this.restaurantService.getRestaurantById(id));
    }

    @PostMapping("/restaurants")
    public RestaurantDto addRestaurant(@Valid @RequestBody RestaurantDto restaurantDto) {
        return RestaurantDto.buildFromEntity(this.restaurantService.addRestaurant(restaurantDto));
    }

    @DeleteMapping("/restaurants/{id}")
    public void deleteRestaurant(@PathVariable Integer id) {
        this.restaurantService.deleteRestaurant(id);
    }

}
