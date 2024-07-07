import { Component, Input, OnInit } from "@angular/core";
import { Category } from "../../interface/category";
import { CommonModule } from "@angular/common";
import { ApiService } from "../../service/api.service";
import { Purchase } from "../../interface/purchase";

@Component({
    selector: 'category-page',
    standalone: true,
    imports: [
        CommonModule
    ],
    templateUrl: './category-page.component.html',
    styleUrl: './category-page.component.scss',
    providers: [],
})
export class CategoryPageComponent implements OnInit {

    @Input() category: Category | undefined;
    // @Input() purchases: Array<Purchase> | undefined;
    purchases: Array<Purchase> = [];

    constructor(private apiService: ApiService) {}

    ngOnInit(): void {
        
    }

    getPurchases(category_id: number | undefined) {
        if (category_id === undefined) {
            return;
        }
        this.apiService.getCategory(category_id).subscribe({
            next: (data) => {
                console.log("Received", data['data']['category']);
                this.purchases = data['data']['category']['purchases'];
            },
            error: (e) => {
                console.error(e);
                this.purchases = [];
            },
            complete: () => {
                console.log(`Attempted to fetch purchases for category ${category_id}.`);
            }
        })
    }
}