import Box from '@mui/material/Box';
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
  task: TaskDTO;
  openModal: "info" | "edit" | "delete" | null;
  handleClose: () => void;
}

function TaskModal({ task, openModal, handleClose }: TaskModalProps
){
    const card_style = {
            position: "absolute",
            top: "50%",
            left: "50%",
            transform: "translate(-50%, -50%)",
            width: 400,
            bgcolor: "#FFFFFF",
            border: "1px solid #e0e0e0",
            boxShadow: 24,
            borderRadius: "10px",
            p: 4,
    };

    return(
        <Modal open={Boolean(openModal)} onClose={handleClose}>
        <Box sx={card_style}>
          {openModal === "info" && (
            <>
              <h3>{task.title}</h3>
              <p>{task.description?.text || "Sem descrição disponível."}</p>
              <p><strong>Status:</strong> {task.done ? "Concluída" : "Pendente"}</p>
              <p><strong>Labels:</strong> {task.labels?.map(l => l.text).join(", ") || "Sem labels"}</p>
            </>
          )}

          {openModal === "edit" && (
            <>
              <h3>Editar tarefa</h3>
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
              <h3>Excluir tarefa</h3>
              <p>Tem certeza que deseja excluir <strong>{task.title}</strong>?</p>
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
        </Box>
      </Modal>
    );
}

export default TaskModal;