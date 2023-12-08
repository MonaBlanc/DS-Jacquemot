package fr.polytech.cours.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddEvaluationFinaleDto {

    @JsonProperty("nom")
    private String nom;

    @JsonProperty("description")
    private String description;

    @JsonProperty("noteFinale")
    private Integer noteFinale;

}
