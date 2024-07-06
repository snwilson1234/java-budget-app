import { ChangeDetectionStrategy, Component, EventEmitter, OnInit, Output, inject } from "@angular/core";
import { MatListModule } from '@angular/material/list';
import { CommonModule } from "@angular/common";
import { Category } from "../../interface/category";
import { ApiService } from "../../service/api.service";
import { MatButtonModule } from "@angular/material/button"
import { MatDialog } from "@angular/material/dialog";
import { NewCategoryDialogComponent } from "./dialogs/new-category-dialog.component";

@Component({
    selector: 'home-page',
    standalone: true,
    imports: [
        CommonModule,
        MatListModule,
        MatButtonModule,
    ],
    templateUrl: './home-page.component.html',
    styleUrl: './home-page.component.scss',
    providers: [
        ApiService
    ],
})
export class HomePageComponent implements OnInit {

    @Output() openCategoryPageEvent = new EventEmitter<Category>();

    readonly dialog = inject(MatDialog);

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

    onCreateNewCategory() {
        console.log("Attempting to open new category dialog...");
        this.dialog.open(NewCategoryDialogComponent, {
            width: '50%',
            height: '50%',
        })
    }

}