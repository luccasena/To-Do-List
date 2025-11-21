import * as React from 'react';
import { Box, Typography } from '@mui/material';
import Modal from '@mui/material/Modal';
import Button from '@mui/material/Button';
import { TextField } from '@mui/material';
import {
  FormControl,
  FormControlLabel,
  Checkbox,
} from "@mui/material";
import Autocomplete from "@mui/material/Autocomplete";

import { createTask } from "../services/taskService"
import { deleteTask } from "../services/taskService"
import { updateTask } from "../services/taskService"

import type { TaskModalProps } from '../types/task/taskmodalprop';
import type { TaskCreateRequest } from '../types/task/taskcreaterequest';

const TaskModal: React.FC<TaskModalProps> = ({ task, openModal, handleClose, userId , labelAvailable}: TaskModalProps) => {
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

    // Informações que o Usuário vai passar:
    const [titulo, setTitulo] = React.useState<string>();
    const [descricao, setDescricao] = React.useState<string>();
    const [labels, setLabels] = React.useState<number[]>();
    const [done, setDone] = React.useState<boolean>();

    const handleSubmit = async(event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        handleClose();

        if (openModal === "add") {
            // Lógica para adicionar a tarefa
            const task: TaskCreateRequest = {
                title: titulo || "", 
                description: descricao || "", 
                done: false, 
                labelIds: labels || []
            };

            try{
                await createTask(task, userId);
                alert("Tarefa criada com sucesso!");

            }catch{
                alert("Erro ao criar a tarefa");

            }
        }

        else if (openModal === "edit") {
            // Lógica para editar a tarefa

            const updatedTask: TaskCreateRequest = {
                title: titulo || "", 
                description: descricao || "", 
                done: done || false, 
                labelIds: labels || []
            };
            
            try{      
                await updateTask(updatedTask, task!.id);

                alert("Tarefa atualizada com sucesso!");

            }catch{
              alert("Erro ao atualizar a tarefa");

            }
        } 

        else if (openModal === "delete") {
            // Lógica para editar a tarefa

            try{
               await deleteTask(task!.id);
                alert("Tarefa deletada com sucesso!");

            }catch{
              alert("Erro ao deletar a tarefa");

            }
        } 

      };

    React.useEffect(() => {
        if (openModal === "edit" && task) {
            setTitulo(task.title);
            setDescricao(task.description?.text);
            setDone(task.done);
            setLabels(task.labels.map(l => l.id));
        }
    },[openModal, task]);

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
                        defaultValue={task?.title}
                        onChange={(e) => setTitulo(e.target.value)}
                    />
                    <TextField
                        label="Descrição"
                        type="descricao"
                        fullWidth
                        margin="normal"
                        defaultValue={task?.description?.text}
                        onChange={(e) => setDescricao(e.target.value)}
                    />   
                    <FormControlLabel 
                        control={
                          <Checkbox 
                            checked={done} 
                            onChange={e => setDone(e.target.checked)} 
                            />
                        } 
                        label="Status" />

                    <FormControl component="fieldset" variant="standard">
                      <Autocomplete
                          multiple
                          disableCloseOnSelect
                          defaultValue={task?.labels.map(label => label)}
                          options={labelAvailable}
                          getOptionLabel={(option) => option.name}
                          onChange={(_, newValue) => setLabels(newValue.map(label => label.id))}
                          renderInput={(params) => (
                            <TextField
                              {...params}
                              label="Labels"
                              placeholder="Selecione uma ou mais"
                              sx={{ width: "320px" }}
                            />
                          )}
                        />
                    </FormControl>
                  </Box>
              <Button type="submit" variant="contained" color="primary">
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
                <Button type="submit" variant="contained" color="error">
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
                      onChange={(e) => setTitulo(e.target.value)}
                  />
                  <TextField
                      label="Descrição"
                      type="descricao"
                      fullWidth
                      margin="normal"
                      onChange={(e) => setDescricao(e.target.value)}
                  />
                  <FormControl component="fieldset" variant="standard">
                    <Autocomplete
                        multiple
                        disableCloseOnSelect
                        options={labelAvailable}
                        getOptionLabel={(option) => option.name}
                        onChange={(_, newValue) => setLabels(newValue.map(label => label.id))}
                        renderInput={(params) => (
                          <TextField
                            {...params}
                            label="Labels"
                            placeholder="Selecione uma ou mais"
                            sx={{ width: "320px" }}
                          />
                        )}
                      />
                  </FormControl>
                </Box>
                <Button type="submit" variant="contained" color="primary">
                  Adicionar Tarefa
                </Button>
             </>
          )}

        </Box>
      </Modal>
    );
}
export default TaskModal;