import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CommonModule, Location } from '@angular/common';
import { Restaurant, RestaurantService, Evaluation } from '../../services/RestaurantService';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-restaurant-details',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './restaurant-details.component.html',
  styleUrls: ['./restaurant-details.component.css']
})
export class RestaurantDetailsComponent implements OnInit {

  photoOptions: string[] = []; 
  public restaurant!: Restaurant;
  public newEvaluation: Evaluation = {
    id: 0,
    auteur: '',
    note: 0,
    commentaire: '',
    dateCreation: new Date(),
    dateModification: new Date(),
    photos: []
  };

  constructor(
    private route: ActivatedRoute,
    private location: Location,
    private dataService: RestaurantService
  ) {}

  ngOnInit() {
    this.route.params.subscribe(params => {
      const restaurantId = +params['id'];
      if (restaurantId) {
        this.dataService.getRestaurant(restaurantId).subscribe((data: Restaurant) => {
          this.restaurant = data;
          console.log(this.restaurant);
        });
      }
    });
  }

  addEvaluation() {
    const restaurantId = this.restaurant.id;
    const newEvaluationData = this.newEvaluation;

    if (newEvaluationData.auteur && newEvaluationData.note >= 0 && newEvaluationData.note <= 3) {
      this.dataService.addEvaluation(restaurantId, newEvaluationData).subscribe(
        (addedEvaluation: Evaluation) => {
          console.log('Evaluation added:', addedEvaluation);
          this.restaurant.evaluations.push(addedEvaluation);
          this.newEvaluation = {
            id: 0,
            auteur: '',
            note: 0,
            commentaire: '',
            dateCreation: new Date(),
            dateModification: new Date(),
            photos: []
          };
        },
        (error) => {
          console.error('Error adding evaluation:', error);
        }
      );
    } else {
      console.warn('Please provide valid values for auteur and note.');
    }
  }

  deleteEvaluation(evaluationId: number) {
    
  }

  goBack() {
    this.location.back();
  }
}
