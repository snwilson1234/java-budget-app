import { Category } from './category';

export interface CustomResponse {
    timeStamp: Date;
    statusCode: number;
    status: string;
    reason: string;
    message: string;
    developerMessage: string;
    data: { categories: Category[] } //can either be multiple categories or just one
}