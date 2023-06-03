import { Role } from "./role";
import { User } from "./user";

export interface Account {
    id: number;
    userName: string;
    email: string;
    password: string;
    roles: Role[];
    user: User;
}