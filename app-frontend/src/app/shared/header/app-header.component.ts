import { Component, EventEmitter, Input, OnInit, Output } from "@angular/core";
import { Page } from "../../enum/page.enum";
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { faSackDollar } from '@fortawesome/free-solid-svg-icons';
import { CommonModule } from "@angular/common";
import { MatButtonModule } from "@angular/material/button";
import { RouterLink, RouterLinkActive, RouterOutlet } from "@angular/router";

@Component({
    selector: 'app-header',
    standalone: true,
    imports: [
        CommonModule,
        FontAwesomeModule,
        MatButtonModule,
        RouterOutlet,
        RouterLink,
        RouterLinkActive
    ],
    templateUrl: 'app-header.component.html',
    styleUrl: 'app-header.component.scss',
    providers: []
})
export class AppHeaderComponent implements OnInit {
    readonly Page = Page;

    faSackDollar = faSackDollar;

    ngOnInit(): void {

    }

}