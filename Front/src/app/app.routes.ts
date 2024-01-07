import { Routes } from '@angular/router';
import { RestaurantListComponent } from '../components/restaurant-list/restaurant-list.component';
import { RestaurantDetailsComponent } from '../components/restaurant-details/restaurant-details.component';

export const routes: Routes = [
    { path: 'restaurants', component: RestaurantListComponent },
    { path: '', redirectTo: '/restaurants', pathMatch: 'full' },
    { path: 'restaurant-details/:id', component: RestaurantDetailsComponent }

];
