package fr.polytech.cours.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.polytech.cours.entity.EvaluationEntity;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EvaluationDto {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("auteur")
    private String auteur;

    @JsonProperty("commentaire")
    private String commentaire;

    @Size(min = 0)
    @Size(max = 3)
    @JsonProperty("note")
    private Integer note;

    @JsonProperty("dateCreation")
    private Date dateCreation;

    @JsonProperty("dateModification")
    private Date dateModification;

    @JsonProperty("photoAvis")
    private String photoAvis;


    public static EvaluationDto buildFromEntity(EvaluationEntity evaluationEntity) {
        return EvaluationDto.builder()
                .id(evaluationEntity.getId())
                .auteur(evaluationEntity.getAuteur())
                .commentaire(evaluationEntity.getCommentaire())
                .note(evaluationEntity.getNote())
                .dateCreation(evaluationEntity.getDateCreation())
                .dateModification(evaluationEntity.getDateModification())
                .photoAvis(evaluationEntity.getPhotoAvis())
                .build();
    }

}
