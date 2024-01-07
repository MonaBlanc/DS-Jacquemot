import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { RestaurantListComponent } from '../components/restaurant-list/restaurant-list.component';
import { RestaurantDetailsComponent } from '../components/restaurant-details/restaurant-details.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet, RestaurantListComponent, RestaurantDetailsComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'cours';
}
