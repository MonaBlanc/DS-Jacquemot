package fr.polytech.cours.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.polytech.cours.entity.EvaluationEntity;
import fr.polytech.cours.entity.EvaluationFinaleEntity;
import fr.polytech.cours.entity.RestaurantEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDto {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("nom")
    private String nom;

    @JsonProperty("adresse")
    private String adresse;

    @JsonProperty("evaluations")
    private List<EvaluationEntity> evaluations;

    @JsonProperty("moyenne")
    private Float moyenne;

    @JsonProperty("evaluationFinale")
    private EvaluationFinaleEntity evaluationFinale;

    @JsonProperty("photoRestaurant")
    private String photoRestaurant;

    @JsonProperty("tags")
    private List<RestaurantEntity.Tag> tags;

    @JsonProperty("listeEvaluations")List<EvaluationEntity> listeEvaluations;

    public static RestaurantDto buildFromEntity(RestaurantEntity restaurantEntity) {
        return RestaurantDto.builder()
                .id(restaurantEntity.getId())
                .nom(restaurantEntity.getNom())
                .adresse(restaurantEntity.getAdresse())
                .evaluations(restaurantEntity.getEvaluations())
                .moyenne(restaurantEntity.getMoyenne())
                .evaluationFinale(restaurantEntity.getEvaluationFinale())
                .photoRestaurant(restaurantEntity.getPhotoRestaurant())
                .tags(restaurantEntity.getTags())
                .build();
    }

}
