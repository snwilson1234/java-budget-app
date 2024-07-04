import { Component, Input, OnInit } from "@angular/core";
import { Category } from "../../interface/category";
import { CommonModule } from "@angular/common";

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

    ngOnInit(): void {
        
    }
}