import { Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { HomePageComponent } from './pages/home/home-page.component';
import { CategoryPageComponent } from './pages/category/category-page.component';

export const routes: Routes = [
    { 
        path: '',
        redirectTo: '/homePage',
        pathMatch: 'full'
    },
    {
        path: 'homePage',
        component: HomePageComponent
    },
    {
        path: 'categoryPage',
        component: CategoryPageComponent
    }
];