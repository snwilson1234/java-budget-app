import { Component, Input, OnInit, inject } from "@angular/core";
import { Category } from "../../interface/category";
import { CommonModule } from "@angular/common";
import { ApiService } from "../../service/api.service";
import { Purchase } from "../../interface/purchase";
import { MatListModule } from "@angular/material/list";
import { MatButtonModule } from "@angular/material/button";
import { MatDialog } from "@angular/material/dialog";
import { NewPurchaseDialogComponent } from "./dialogs/new-purchase-dialog.component";

@Component({
    selector: 'category-page',
    standalone: true,
    imports: [
        CommonModule,
        MatListModule,
        MatButtonModule
    ],
    templateUrl: './category-page.component.html',
    styleUrl: './category-page.component.scss',
    providers: [],
})
export class CategoryPageComponent implements OnInit {

    @Input() category!: Category;
    purchases: Array<Purchase> = [];

    readonly dialog = inject(MatDialog);

    constructor(private apiService: ApiService) {}

    ngOnInit(): void {
        if (this.category){
            this.getPurchases(this.category.id);
        }
    }

    getPurchases(category_id: number | undefined) {
        if (category_id === undefined) {
            return;
        }
        this.apiService.getCategory(category_id).subscribe({
            next: (data) => {
                console.log("Received", data['data']['category']['purchases']);
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

    onOpenNewPurchaseDialog() {
        console.log("Attempting to open new purchase dialog...");
        let dialogRef = this.dialog.open(NewPurchaseDialogComponent, {
            width: '50%',
            height: '50%',
            data: { category_id: this.category.id }
        });

        dialogRef.afterClosed().subscribe(result => {
            console.log("RESULT AFTER CLOSING", result);
            this.apiService.postPurchase({
                description: result.description,
                amount: result.amount,
                date: result.date,
                categoryId: this.category.id
            }).subscribe({
                next: (data) => {
                    console.log("Successfully created a new purchase.");
                    if (this.category) {
                        this.getPurchases(this.category.id);
                    }
                },
                error: (e) => {
                    console.error(e);
                },
                complete: () => {
                    console.log("Attempted to post new purchase.")
                }
            })
        })
    }
}