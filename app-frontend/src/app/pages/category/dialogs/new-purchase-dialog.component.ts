import { ChangeDetectionStrategy, Component, Input, inject } from "@angular/core";
import { FormControl, FormGroup, ReactiveFormsModule } from "@angular/forms";
import { MatButtonModule } from "@angular/material/button";
import { MatDialogActions, MatDialogClose, MatDialogTitle, MatDialogContent, MatDialogRef } from "@angular/material/dialog"

@Component({
    selector: 'new-purchase-dialog',
    templateUrl: 'new-purchase-dialog.component.html',
    styleUrl: 'new-purchase-dialog.component.scss',
    standalone: true,
    imports: [
        MatDialogActions,
        MatDialogClose,
        MatDialogTitle,
        MatDialogContent,
        ReactiveFormsModule,
        MatButtonModule,
    ],
    changeDetection: ChangeDetectionStrategy.OnPush,
})
export class NewPurchaseDialogComponent {
    @Input() category_id!: number;

    readonly dialogRef = inject(MatDialogRef<NewPurchaseDialogComponent>);

    newPurchaseForm = new FormGroup({
        description: new FormControl(''),
        amount: new FormControl(''),
        date: new FormControl(''),
    })

    makeNewPurchase() {
        console.log("clicked submit");
        this.dialogRef.close({
            description: this.newPurchaseForm.get('description')?.value,
            amount: this.newPurchaseForm.get('amount')?.value,
            date: this.newPurchaseForm.get('date')?.value
        });   
    }
}