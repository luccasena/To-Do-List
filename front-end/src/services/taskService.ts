import axios from "axios";
import type { Task } from "../types/task/task";
import type { TaskCreateRequest } from "../types/task/taskcreaterequest";

const API_URL = import.meta.env.VITE_API_URL;

export const getTarefas = async (idUsuario: number): Promise<Task[]> => {

    const response = await axios.get<Task[]>(
        `${API_URL}/tasks?userId=${idUsuario}`
    );

    console.log("Tarefas obtidas do servidor:", response.data);

    return response.data;

}

export const createTask = async (task: Omit<TaskCreateRequest, "id">, idUsuario: number): Promise<Task> => {

    const response = await axios.post<Task>(
        `${API_URL}/tasks/${idUsuario}`,
        task
    );

    return response.data;
}