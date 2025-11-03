import type { Label } from "../label";
import type { Description } from "../description";


export interface Task {
  id: number;
  title: string;
  description?: Description;
  done: boolean;
  labels: Label[];
}
