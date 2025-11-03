import axios from "axios";
import type { User } from "../types/user";
import type { Authlog } from "../types/user/authlog";

const API_URL = import.meta.env.VITE_API_URL;

export const realizaLogin = async (authlog: Authlog) : Promise<User> => {

    const response = await axios.post<User>(
        `${API_URL}/auth/login`, authlog
    );

    return response.data;

}
 