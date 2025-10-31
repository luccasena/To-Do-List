import React from "react";
import ListItem from '@mui/material/ListItem';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import Checkbox from '@mui/material/Checkbox';
import IconButton from '@mui/material/IconButton';
import InfoIcon from '@mui/icons-material/Info';
import DeleteIcon from '@mui/icons-material/Delete';
import EditIcon from '@mui/icons-material/Edit';
import Box from '@mui/material/Box';
import TaskModal from './TaskModal.tsx';

export type TaskDTO = {
    id: number;
    title: string;
    description?: { id: number; text: string };
    done?: boolean;
    labels?: { id: number; text: string }[];
}

function TaskItem({ task }: { task: TaskDTO }) {

    const [checked, setChecked] = React.useState([1]);
    const [openModal, setOpenModal] = React.useState<"info" | "edit" | "delete" | null>(null);

    const handleToggle = (value: number) => () => {
        const currentIndex = checked.indexOf(value);
        const newChecked = [...checked];

        if (currentIndex === -1) {
            newChecked.push(value);
        } else {
            newChecked.splice(currentIndex, 1);
        }

        setChecked(newChecked);
    };

    const handleOpenModal = (type: "info" | "edit" | "delete") => () => {
        setOpenModal(type);
    };
    
    const handleClose = () => setOpenModal(null);

    return(
        <>
            <ListItem
                key={task.id}
                secondaryAction={
                    <Box sx={{ display: 'flex', gap: 1 }}>
                        <IconButton edge="end" aria-label="info" onClick={handleOpenModal("info")}>
                            <InfoIcon/>
                        </IconButton>

                        <IconButton edge="end" aria-label="delete" onClick={handleOpenModal("delete")}>
                            <DeleteIcon/>
                        </IconButton>

                        <IconButton edge="end" aria-label="edit" onClick={handleOpenModal("edit")}>
                            <EditIcon/>
                        </IconButton>
                    </Box>
                    }
                disablePadding>
                <ListItemButton 
                    sx={{padding: "10px", margin: "15px"}} 
                    onClick={handleToggle(task.id)} 
                    dense>
                    <ListItemIcon>
                        <Checkbox
                            edge="start"
                            checked={checked.includes(task.id)}
                            tabIndex={-1}
                            disableRipple/>
                    </ListItemIcon>
                    <ListItemText 
                        primary={`${task.title}`}/>
                </ListItemButton>
            </ListItem>
            <TaskModal task={task} openModal={openModal} handleClose={handleClose}/>
        </>
    );
}

export default TaskItem;