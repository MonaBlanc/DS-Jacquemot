import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export interface Restaurant {
  id: number;
  nom: string;
  adresse: string;
  evaluations: Evaluation[];
  evaluationFinale: EvaluationFinale;
  moyenne: number;
  photoRestaurant: string;
  tags: Tag[];
}

export interface Evaluation {
  id: number;
  auteur: string;
  note: number;
  commentaire: string;
  dateCreation: Date;
  dateModification: Date;
  photos: string[];
}

export interface EvaluationFinale {
    id: number;
    nom: string;
    noteFinale: number;
    description: string;
  }

  export enum Tag {
    Gastronomique,
    Bistrot,
    Bistronomique,
    Brasserie,
    Fastfood
}

@Injectable()
export class RestaurantService {
  private apiUrl = 'http://localhost:8080/restaurants';
  static Tag: { [s: string]: string; } | ArrayLike<string>;

  constructor(private httpClient: HttpClient) {}

  getRestaurants(): Observable<Restaurant[]> {
    return this.httpClient.get<Restaurant[]>(this.apiUrl);
  }

  getRestaurant(id: number): Observable<Restaurant> {
    return this.httpClient.get<Restaurant>(`${this.apiUrl}/${id}`);
  }

  addRestaurant(restaurant: Restaurant): Observable<Restaurant> {
    return this.httpClient.post<Restaurant>(this.apiUrl, restaurant);
  }

  getEvaluationPhotos(restaurantId: number, evaluationId: number): Observable<string[]> {
    return this.httpClient.get<string[]>(`${this.apiUrl}/${restaurantId}/evaluations/${evaluationId}/photos`);
  }

  addEvaluation(restaurantId: number, evaluation: Evaluation): Observable<Evaluation> {
    return this.httpClient.post<Evaluation>(`${this.apiUrl}/${restaurantId}/evaluations`, evaluation);
  }

  updateRestaurant(restaurantId: number, restaurant: Restaurant): Observable<Restaurant> {
    return this.httpClient.put<Restaurant>(`${this.apiUrl}/${restaurantId}`, restaurant);
  }

  getIllustration(id: number): Observable<String> {
    return this.httpClient.get<String>(`${this.apiUrl}/${id}/illustration`);
  }
}
