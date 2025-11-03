import * as React from 'react';
import {Box, Typography} from '@mui/material';
import Modal from '@mui/material/Modal';
import Button from '@mui/material/Button';
import { TextField } from '@mui/material';
import Radio from '@mui/material/Radio';
import RadioGroup from '@mui/material/RadioGroup';
import FormControlLabel from '@mui/material/FormControlLabel';
import FormControl from '@mui/material/FormControl';
import FormLabel from '@mui/material/FormLabel';

import { getLabels } from '../services/labelService';


import type { TaskModalProps } from '../types/task/taskmodalprop';
import { useEffect } from 'react';
import type { Label } from '../types/label';


const TaskModal: React.FC<TaskModalProps> = ({ task, openModal, handleClose, userId }: TaskModalProps) => {
    const card_style = {
            display: "flex",
            flexDirection: "column",
            gap: 2 ,
            position: "absolute",
            top: "50%",
            left: "50%",
            transform: "translate(-50%, -50%)",
            width: 400,
            bgcolor: "#F7F7F7",
            border: "1px solid #e0e0e0",
            boxShadow: 24,
            borderRadius: "10px",
            p: 4
    };

    const [idUser, setUserId] = React.useState<number | undefined>();
    const [labels, setLabels] = React.useState<Label[]>([]);

    const obterLabels = async () => {
        const labelsResponse = await getLabels();
        setLabels(labelsResponse);
        
    }


    useEffect(() => {
        obterLabels();

    }, []);

    const handleSubmit = async(event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        handleClose();

        if (openModal === "add") {
            // Lógica para adicionar a tarefa
            setUserId(userId);


        }
        else if (openModal === "edit") {
            // Lógica para editar a tarefa

        } 
        else if (openModal === "delete") {
            // Lógica para editar a tarefa

        } 
      };

    return(
        <Modal open={Boolean(openModal)} onClose={handleClose}>
        <Box component="form" sx={card_style} onSubmit={handleSubmit}>
          {openModal === "info" && (
            <>
              <Typography variant="h6"><strong>Título:</strong> {task?.title}</Typography>
              <Typography variant="body1"><strong>Descrição:</strong> {task?.description?.text}</Typography>
              <Typography variant="body1"><strong>Status:</strong> {task?.done ? "Concluída" : "Pendente"}</Typography>
              <Typography variant="body1"><strong>Labels:</strong> {task?.labels?.map(l => l.name).join(", ")}</Typography>
            </>
          )}

          {openModal === "edit" && (
            <>
              <Typography variant="h6">Editar tarefa</Typography>
                <Box>
                    <TextField
                        label="Título"
                        type="titulo"
                        fullWidth
                        margin="normal"
                    />
                    <TextField
                        label="Descrição"
                        type="descricao"
                        fullWidth
                        margin="normal"
                    />     
                    <FormControl>
                      <FormLabel id="demo-radio-buttons-group-label">Labels</FormLabel>
                      <RadioGroup
                        aria-labelledby="demo-radio-buttons-group-label"
                        name="radio-buttons-group"
                      >
                        {labels?.map((label) => (
                          <FormControlLabel key={label.id} value={label.id} control={<Radio />} label={label.name} />
                        ))}
                      </RadioGroup>
                    </FormControl>   
                </Box>

              <Button variant="contained" color="primary" onClick={handleClose}>
                Salvar alterações
              </Button>
            </>
          )}

          {openModal === "delete" && (
            <>
              <Typography variant="h5">Excluir tarefa</Typography>
              <Typography variant="body1">Tem certeza que deseja excluir <strong>{task?.title}</strong>?</Typography>
              <Box sx={{ display: "flex", justifyContent: "flex-end", gap: 2 }}>
                <Button variant="outlined" onClick={handleClose}>
                  Cancelar
                </Button>
                <Button variant="contained" color="error" onClick={() => alert("Tarefa excluída!")}>
                  Excluir
                </Button>
              </Box>
            </>
          )}

          {openModal === "add" && (
            <>
                <Typography variant="h5">Adicionar Tarefa tarefa</Typography>
                <Box>
                    <TextField
                        label="Título"
                        type="titulo"
                        fullWidth
                        margin="normal"
                    />
                    <TextField
                        label="Descrição"
                        type="descricao"
                        fullWidth
                        margin="normal"
                    />    
                    <FormControl>
                    <FormLabel id="demo-radio-buttons-group-label">Labels</FormLabel>
                      <RadioGroup
                        aria-labelledby="demo-radio-buttons-group-label"
                        defaultValue="female"
                        name="radio-buttons-group"
                      >
                        {labels?.map((label) => (
                          <FormControlLabel key={label.id} value={label.id} control={<Radio />} label={label.name} />
                        ))}
                      </RadioGroup>
                  </FormControl>    
                </Box>

              <Button variant="contained" color="primary" onClick={handleClose}>
                Adicionar Tarefa
              </Button>
            </>

          )}
        </Box>
      </Modal>
    );
}

export default TaskModal;