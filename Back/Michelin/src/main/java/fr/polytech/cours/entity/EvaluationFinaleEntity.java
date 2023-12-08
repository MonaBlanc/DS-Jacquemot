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
import java.util.List;

@Entity(name = "evaluationFinale")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EvaluationFinaleEntity {

    @Id
    @GeneratedValue()
    private Integer id;

    @Column(name = "nom", columnDefinition = "varchar(90)", nullable = false)
    private String nom;


    @Column(name = "noteFinale", nullable = false)
    @Min(0)
    @Max(3)
    private Integer noteFinale;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @OneToOne()
    @JoinColumn(name = "restaurant")
    @JsonIgnore
    private RestaurantEntity restaurant;
}
