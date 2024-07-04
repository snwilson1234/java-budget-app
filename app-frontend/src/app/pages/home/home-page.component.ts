import { Component, EventEmitter, OnInit, Output } from "@angular/core";
import { MatListModule } from '@angular/material/list';
import { CommonModule } from "@angular/common";
import { Category } from "../../interface/category";
import { ApiService } from "../../service/api.service";
import { Observable, catchError, startWith } from "rxjs";
import { AppState } from "../../interface/app-state";
import { CustomResponse } from "../../interface/custom-response";

@Component({
    selector: 'home-page',
    standalone: true,
    imports: [
        CommonModule,
        MatListModule
    ],
    templateUrl: './home-page.component.html',
    styleUrl: './home-page.component.scss',
    providers: [
        ApiService
    ]
})
export class HomePageComponent implements OnInit {

    @Output() openCategoryPageEvent = new EventEmitter<Category>();

    categories: Category[] = [];
    
    constructor(
        private apiService: ApiService
    ) {}

    ngOnInit(): void {
        this.getCats();
    }

    getCats() {
        this.apiService.getCategories().subscribe({
            next: (data) => {
                console.log("Received:", data['data']['categories']);
                this.categories = data['data']['categories'];
            },
            error: (e) => {
                console.error(e);
                this.categories = [];
            },
            complete: () => {
                console.log("Attempted to fetch categories.");
            }
        })
    }

    onSelectCategory(category: Category) {
        console.log("selected:", category.name);
        this.openCategoryPageEvent.emit(category);
    }

}