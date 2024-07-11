import { Purchase } from "./purchase";

export interface Category {
    id : number;
    name : string;
    budget: number;
    purchases: Array<Purchase>;
}