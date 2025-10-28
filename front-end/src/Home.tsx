import React from "react";
import {} from "@mui/material";
import { useLocation } from "react-router-dom";

const Home: React.FC = () => {
    const location = useLocation();
    const user = location.state?.user;

    return(
        <>
            <h2>Bem vindo {user.name}</h2>

        </>
    );
};


export default Home;
