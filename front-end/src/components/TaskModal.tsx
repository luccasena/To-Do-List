import {Box, Typography} from '@mui/material';
import Modal from '@mui/material/Modal';
import Button from '@mui/material/Button';
import { TextField } from '@mui/material';

export type TaskDTO = {
    id: number;
    title: string;
    description?: { id: number; text: string };
    done?: boolean;
    labels?: { id: number; text: string }[];
}

interface TaskModalProps {
  task?: TaskDTO;
  openModal: "info" | "edit" | "delete" | "add" | null;
  handleClose: () => void;
}

function TaskModal({ task, openModal, handleClose }: TaskModalProps
){
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

    return(
        <Modal open={Boolean(openModal)} onClose={handleClose}>
        <Box sx={card_style}>
          {openModal === "info" && (
            <>
              <Typography variant="h6"><strong>Título:</strong> {task?.title}</Typography>
              <Typography variant="body1"><strong>Descrição:</strong> {task?.description?.text || "Sem descrição disponível."}</Typography>
              <Typography variant="body1"><strong>Status:</strong> {task?.done ? "Concluída" : "Pendente"}</Typography>
              <Typography variant="body1"><strong>Labels:</strong> {task?.labels?.map(l => l.text).join(", ") || "Sem labels"}</Typography>
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