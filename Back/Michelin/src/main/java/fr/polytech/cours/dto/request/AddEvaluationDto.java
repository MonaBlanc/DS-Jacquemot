package fr.polytech.cours.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.polytech.cours.entity.RestaurantEntity;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddEvaluationDto {

    @JsonProperty("auteur")
    private String auteur;

    @JsonProperty("commentaire")
    private String commentaire;

    @JsonProperty("note")
    private Integer note;

    @JsonProperty("dateCreation")
    private Date dateCreation;

    @JsonProperty("dateModification")
    private Date dateModification;

    @JsonProperty("photoAvis")
    private String photoAvis;

    @JsonProperty("restaurant")
    private RestaurantEntity restaurant;

}
