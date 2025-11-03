import React, { useEffect } from "react";
import { Box, Typography } from "@mui/material";
import { useLocation } from "react-router-dom";
import List from '@mui/material/List';
import Fab from '@mui/material/Fab';
import AddIcon from '@mui/icons-material/Add';

import TaskItem from "./components/TaskItem";
import TaskModal from "./components/TaskModal";

import type { Task } from "./types/task/task";
import type { User } from "./types/user/user";

import { getTarefas } from "./services/taskService";

const Home: React.FC = () => {
    const location = useLocation();

    const [openModal, setOpenModal] = React.useState<"add" | null>(null);
    const [tasks, setTasks] = React.useState<Task[]>([]);
    const [user] = React.useState<User>(location.state?.user);
    
    const handleClose = () => setOpenModal(null);

    const handleOpenModal = (type: "add") => () => {
        setOpenModal(type);

    };

    const obterTarefas = async (id: number) => {
        const tarefas = await getTarefas(id);
        setTasks(tarefas);
        
    }

    useEffect(() => {
        obterTarefas(user.id);

    }, []);



    return(
        <>
            <Box className="home-center">
                <Typography variant="h1" sx={{fontSize: "50px"}}>Bem vindo, {user.name}!</Typography>
                    <Fab color="primary" aria-label="add" sx={{ margin: "10px"}} onClick={handleOpenModal("add")}>
                        <AddIcon />
                    </Fab>
                <List dense sx={{ width: '100%', maxWidth: 450}}>
                    <Box className="tasks">
                        <Typography variant="h2" style={{textAlign: "center", fontSize: "25px"}}>Tarefas</Typography>
                        {tasks.map((value) => {
                            return (
                                <>
                                    <TaskItem key={value.id} task={value} userId={user.id} />
                                </>
                            );
                        })}
                    </Box>
                    <TaskModal openModal={openModal} handleClose={handleClose} />
                </List>
            </Box>
        </>
    );
};

export default Home;