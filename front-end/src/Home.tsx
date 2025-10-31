import React from "react";
import {} from "@mui/material";
import { useLocation } from "react-router-dom";
import List from '@mui/material/List';
import TaskItem from "./components/TaskItem";

interface Task {
  id: number;
  title: string;
  description?: { id: number; text: string };
  done?: boolean;
  labels?: any[];
}

const Home: React.FC = () => {
    const location = useLocation();
    const user = location.state?.user;
    const tasks: Task[] = user?.tasks ?? [];

    return(
        <>
            <div className="home-center">
                <h2 style={{textAlign: "center"}}>Bem vindo, {user.name}!</h2>
                <List dense sx={{ width: '100%', maxWidth: 360}}>
                    <div className="tasks">
                        <h2 style={{textAlign: "center"}}>Tarefas</h2>
                        {tasks.map((value) => {
                            return (
                                <TaskItem key={value.id} task={value} />
                            );
                        })}
                    </div>
                </List>
            </div>
        </>
    );
};


export default Home;
