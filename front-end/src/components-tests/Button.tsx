

function Button(){
    const styles = {
            backgroundColor: "hsl(210, 100%, 50%)",
            color: "white",
            border: "none",
            borderRadius: "5px",
            padding: "10px 20px",
            cursor: "pointer",
            fontSize: "16px",
            transition:" background-color 0.3s ease",
    }

    return(
        <button style={styles}>Ver mais</button>
    );
}

export default Button;