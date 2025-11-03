import type { Task } from './task';

export interface TaskModalProps {
  task?: Task;
  openModal: "info" | "edit" | "delete" | "add" | null;
  handleClose: () => void;
  userId?: number;
}