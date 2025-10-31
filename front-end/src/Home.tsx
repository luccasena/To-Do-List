import React from "react";
import { Box, Typography } from "@mui/material";
import { useLocation } from "react-router-dom";
import List from '@mui/material/List';
import Fab from '@mui/material/Fab';
import AddIcon from '@mui/icons-material/Add';
import TaskItem from "./components/TaskItem";
import TaskModal from "./components/TaskModal";

interface Task {
  id: number;
  title: string;
  description?: { id: number; text: string };
  done?: boolean;
  labels?: any[];
}

const Home: React.FC = () => {
    const location = useLocation();

    const [openModal, setOpenModal] = React.useState<"add" | null>(null);

    const handleClose = () => setOpenModal(null);

    const handleOpenModal = (type: "add") => () => {
        setOpenModal(type);
    };

    const user = location.state?.user;
    const tasks: Task[] = user?.tasks ?? [];

    return(
        <>
            <Box className="home-center">
                <Typography variant="h1" sx={{fontSize: "50px"}}>Bem vindo, {user.name}!</Typography>
                <List dense sx={{ width: '100%', maxWidth: 450}}>
                    <Box className="tasks">
                        <Typography variant="h2" style={{textAlign: "center", fontSize: "25px"}}>Tarefas</Typography>
                        {tasks.map((value) => {
                            return (
                                <TaskItem key={value.id} task={value} />
                            );
                        })}
                    </Box>
                    <Fab color="primary" aria-label="add" sx={{ margin: "10px"}} onClick={handleOpenModal("add")}>
                        <AddIcon />
                    </Fab>
                    <TaskModal openModal={openModal} handleClose={handleClose}/>
                </List>
            </Box>
        </>
    );
};


export default Home;
