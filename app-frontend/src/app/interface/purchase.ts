export interface Purchase {
    id: number,
    description: string,
    amount: number,
    date: string,
    category_id: number,
    overBudget: boolean
}