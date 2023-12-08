package fr.polytech.cours.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.polytech.cours.entity.EvaluationFinaleEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvaluationFinaleDto {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("nom")
    private String nom;

    @JsonProperty("description")
    private String description;

    @JsonProperty("noteFinale")
    private Integer noteFinale;

    public static EvaluationFinaleDto buildFromEntity(EvaluationFinaleEntity evaluationFinaleEntity) {
        return EvaluationFinaleDto.builder()
                .id(evaluationFinaleEntity.getId())
                .nom(evaluationFinaleEntity.getNom())
                .description(evaluationFinaleEntity.getDescription())
                .noteFinale(evaluationFinaleEntity.getNoteFinale())
                .build();
    }

}
