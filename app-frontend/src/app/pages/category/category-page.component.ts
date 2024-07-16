import { Component, Input, OnInit, inject } from "@angular/core";
import { Category } from "../../interface/category";
import { CommonModule } from "@angular/common";
import { ApiService } from "../../service/api.service";
import { Purchase } from "../../interface/purchase";
import { MatListModule } from "@angular/material/list";
import { MatButtonModule } from "@angular/material/button";
import { MatDialog } from "@angular/material/dialog";
import { FontAwesomeModule } from "@fortawesome/angular-fontawesome";
import { faCoffee, faCircleXmark } from "@fortawesome/free-solid-svg-icons";
import { NewPurchaseDialogComponent } from "./dialogs/new-purchase-dialog.component";
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { Observable } from 'rxjs';

@Component({
    selector: 'category-page',
    standalone: true,
    imports: [
        CommonModule,
        MatListModule,
        MatButtonModule,
        FontAwesomeModule
    ],
    templateUrl: './category-page.component.html',
    styleUrl: './category-page.component.scss',
    providers: [],
})
export class CategoryPageComponent implements OnInit {
    
    @Input() categoryId!: number;
    category!: Category;
    purchases: Array<Purchase> = [];
    overages: Array<number> = [];

    faCoffee = faCoffee;
    faCircleXmark = faCircleXmark;

    readonly dialog = inject(MatDialog);

    constructor(
        private apiService: ApiService,
        private route: ActivatedRoute,
        private router: Router
    ) {}

    ngOnInit(): void {
        if (this.categoryId){
            this.getPurchases(this.categoryId);
            this.getOverages(this.categoryId);
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
                this.category = data['data']['category'];
            },
            error: (e) => {
                console.error(e);
                this.purchases = [];
            },
            complete: () => {
                console.log(`Attempted to fetch purchases for category ${category_id}.`);
            }
        });
    }

    getOverages(category_id: number) {
        this.apiService.getOverages(category_id).subscribe({
            next: (data) => {
                console.log("Received", data['data']['overages']);
                this.overages = data['data']['overages'];
            },
            error: (e) => {
                console.error(e);
                this.overages = []
            },
            complete: () => {
                console.log("Attempted to fetch overages.")
            }

        })
    }

    onOpenNewPurchaseDialog() {
        console.log("Attempting to open new purchase dialog...");
        let dialogRef = this.dialog.open(NewPurchaseDialogComponent, {
            width: '50%',
            height: '50%',
            data: { category_id: this.categoryId }
        });

        dialogRef.afterClosed().subscribe(result => {
            console.log("RESULT AFTER CLOSING", result);
            this.apiService.postPurchase({
                description: result.description,
                amount: result.amount,
                date: result.date,
                categoryId: this.categoryId
            }).subscribe({
                next: (data) => {
                    console.log("Successfully created a new purchase.");
                    if (this.categoryId) {
                        this.getPurchases(this.categoryId);
                        this.getOverages(this.categoryId);
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

    onDeletePurchase(purchase: Purchase) {
       this.apiService.deletePurchase(purchase).subscribe({
        next: (data) => {
            console.log(`Successfully deleted purchase`);
            this.getPurchases(this.categoryId);
        },
        error: (e) => {
            console.error(e);
        },
        complete: () => {
            console.log(`Attempted to delete purchase ${purchase.id}`);
        }

       });
    }
}