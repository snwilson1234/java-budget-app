import { ChangeDetectionStrategy, Component, inject } from "@angular/core";
import { FormControl, FormGroup, ReactiveFormsModule } from "@angular/forms";
import { MatButtonModule } from "@angular/material/button";
import { MatDialogActions, MatDialogClose, MatDialogTitle, MatDialogContent, MatDialogRef } from "@angular/material/dialog"

@Component({
    selector: 'new-category-dialog',
    templateUrl: 'new-category-dialog.component.html',
    styleUrl: 'new-category-dialog.component.scss',
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
export class NewCategoryDialogComponent {
    readonly dialogRef = inject(MatDialogRef<NewCategoryDialogComponent>);

    newCategoryForm = new FormGroup({
        name: new FormControl(''),
        budget: new FormControl(''),
    })

    makeNewCategory() {
        console.log("clicked submit");
        
    }
}