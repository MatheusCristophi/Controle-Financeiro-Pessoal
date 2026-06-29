import { Routes } from "@angular/router";
import { LoginHeader } from "../../components/login-header/login-header";
import { LoginFooter } from "../../components/login-footer/login-footer";

export const routes: Routes = [
{
    path: '/login',
    component: LoginHeader
},
{
    path: '/login',
    component: LoginFooter
}
]