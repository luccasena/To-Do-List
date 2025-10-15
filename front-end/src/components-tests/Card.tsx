import Button from "./Button.tsx"

function Card(user: { name: string, role: string, age: number, description?: string }) {
    return (
        <div className="card">
            <img className="card-image" alt="Profile Picture" width={200}></img>
            <h2 className="card-title">{user.name} - {user.role} - {user.age} years old</h2>
            <p className="card-text">{user.description}</p>
            <Button></Button>
        </div>
    );
}
Card.defaultProps = {
    name: " ",
    role: " ",
    age: 0,
    description: " "
};

export default Card;