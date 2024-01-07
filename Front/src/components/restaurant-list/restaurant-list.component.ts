import { Component, Directive } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Restaurant, RestaurantService, Tag } from '../../services/RestaurantService';
import { FormsModule, NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { SetColorDirective } from '../../directives/set-color.directive';


@Component({
  selector: 'app-restaurant-list',
  standalone: true,
  imports: [CommonModule, FormsModule, SetColorDirective],
  templateUrl: './restaurant-list.component.html',
  styleUrl: './restaurant-list.component.css'
})
export class RestaurantListComponent {

  public restaurants: Restaurant[] = [];
  public illustration: String = '';
  public tagOptions: string[] = ["Gastronomique", "Bistrot", "Bistronomique", "Brasserie", "Fastfood"] ;

  public newRestaurant: Restaurant = {
    id: 0,
    nom: '',
    adresse: '',
    evaluations: [],
    evaluationFinale: {
      id: 0,
      nom: '',
      noteFinale: 0,
      description: ''
    },
    moyenne: 0,
    photoRestaurant: '',
    tags: []
  };

  selectedFile: File | null = null;

  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
  }


  constructor(private dataService: RestaurantService, private router: Router) { 
    dataService.getRestaurants().subscribe((data: Restaurant[]) => {
      this.restaurants = data;
      console.log(this.restaurants);
    });


    this.restaurants.forEach(restaurant => {
      dataService.getIllustration(restaurant.id).subscribe((data: String) => {
        this.illustration = data;
        console.log(data);
      });
    });
  }

  goToRestaurantDetails(restaurantId: number) {
    this.router.navigate(['/restaurant-details', restaurantId]);
  }


  getRowColorStyle(restaurant: Restaurant): any {
    if (restaurant.moyenne !== undefined) {
      const averageRating = restaurant.moyenne;

      if (averageRating > 2) {
        console.log('averageRating > 2');
        return { 'background-color': 'yellow' };
      } else if (averageRating < 1) {
        console.log('averageRating < 1');
        return { 'background-color': 'red' };
      }
    }
  }
  addRestaurant() {
    const newRestaurantData = this.newRestaurant;

    if (newRestaurantData.nom && newRestaurantData.adresse) {
      if (this.selectedFile) {
        this.dataService.uploadPhoto(this.selectedFile).subscribe(
          (response) => {
            console.log('File uploaded successfully:', response);
            newRestaurantData.photoRestaurant = response.url; 
            this.addRestaurantAfterFileUpload(newRestaurantData);
          },
          (error) => {
            console.error('Error uploading file:', error);
          }
        );
      } else {
        this.addRestaurantAfterFileUpload(newRestaurantData);
      }
    } else {
      console.warn('Please provide both nom and adresse for the new restaurant.');
    }
  }

  private addRestaurantAfterFileUpload(newRestaurantData: Restaurant) {
    this.dataService.addRestaurant(newRestaurantData).subscribe(
      (addedRestaurant: Restaurant) => {
        // Successfully added the restaurant
        console.log('Restaurant added:', addedRestaurant);

        this.restaurants.push(addedRestaurant);
        this.newRestaurant = {
          id: 0,
          nom: '',
          adresse: '',
          evaluations: [],
          evaluationFinale: {
            id: 0,
            nom: '',
            noteFinale: 0,
            description: ''
          },
          moyenne: 0,
          photoRestaurant: '',
          tags: []
        };
        this.selectedFile = null; // Clear the selected file after adding the restaurant
      },
      (error) => {
        // Handle the error case
        console.error('Error adding restaurant:', error);
      }
    );
  }
}
