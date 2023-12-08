package fr.polytech.cours.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity(name = "evaluations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EvaluationEntity {

    @Id
    @GeneratedValue()
    private Integer id;

    @Column(name = "auteur", columnDefinition = "varchar(50)")
    private String auteur;

    @Column(name = "commentaire", columnDefinition = "varchar(255)")
    private String commentaire;

    @Column(name = "note")
    @Min(0)
    @Max(3)
    private Integer note;

    @Column(name = "dateCreation")
    private Date dateCreation;

    @PrePersist
    protected void onCreate() {
        this.dateCreation = new Date();
    }

    @PostPersist
    protected void onUpdate() {
        this.dateModification = new Date();
    }

    @Column(name = "dateModification")
    private Date dateModification;

    @Column(name = "photoAvis")
    private String photoAvis;

    @ManyToOne()
    @JoinColumn(name = "restaurant")
    @JsonIgnore
    private RestaurantEntity restaurant;
}
