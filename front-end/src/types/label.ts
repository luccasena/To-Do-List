import type { Task } from "./task/task";


export interface Label {
    id: number;
    name: string;
    tasks?: Task[];
}