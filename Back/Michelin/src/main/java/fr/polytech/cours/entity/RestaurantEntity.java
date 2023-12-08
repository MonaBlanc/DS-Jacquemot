package fr.polytech.cours.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity(name = "restaurant")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantEntity {

    @Id
    @GeneratedValue()
    private Integer id;

    @Column(name = "nom", columnDefinition = "varchar(90)", nullable = false)
    private String nom;

    @Column(name = "adresse", nullable = false, columnDefinition = "varchar(255)")
    private String adresse;

    @OneToMany(mappedBy = "restaurant")
    private List<EvaluationEntity> evaluations;

    @Column(name = "moyenne",  nullable = true)
    private Float moyenne;

    @PreUpdate
    @PrePersist
    private void calculMoyenne() {
        if (evaluations != null && !evaluations.isEmpty()) {
            float sum = 0;
            for (EvaluationEntity evaluation : evaluations) {
                sum += evaluation.getNote();
            }
            this.moyenne = sum / evaluations.size();
        } else {
            this.moyenne = null;
        }
    }

    @OneToOne(mappedBy = "restaurant")
    private EvaluationFinaleEntity evaluationFinale;

    @Column(name = "photoRestaurant", nullable = true)
    private String photoRestaurant;

    @ElementCollection(targetClass = Tag.class)
    @Enumerated(EnumType.STRING)
    @Column(name = "tags", nullable = true)
    private List<Tag> tags;

    public enum Tag {
        Gastronomique,
        Bistrot,
        Bistronomique,
        Brasserie,
        Fastfood
    }
}
