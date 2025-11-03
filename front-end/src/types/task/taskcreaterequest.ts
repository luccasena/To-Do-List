

export interface TaskCreateRequest {
    title: string;
    description: string;
    done: boolean;
    labels: number[]; 
}