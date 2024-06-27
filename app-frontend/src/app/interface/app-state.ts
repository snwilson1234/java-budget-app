import { DataState } from "../enum/data-state.enum";

export interface AppState<T> {
    dataState: DataState; //determines state of app
    appData?: T; //optional 
    error?: string; //optional
}