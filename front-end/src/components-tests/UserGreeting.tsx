import Card from "./Card";

type User = {
    name: string;
    role: string;
    age: number;
    description?: string;
};

type UserGreetingProps = {
    isLoggedIn: boolean;
    user: User;
};

function UserGreeting({isLoggedIn, user}: UserGreetingProps) {
    if(isLoggedIn){
            return (
                <>
                    <div className="main-content">
                        <h1 className="welcome-message">Welcome back! {user.name}</h1>
                        <Card name={user.name} role={user.role} age={user.age} description={user.description}></Card>
                    </div>
                </>
            );
    }
    return <h1 className="login-prompt">Please log in to continue.</h1>;
}

export default UserGreeting;
