import { Routes } from "@angular/router";
import { RegisterHeader } from "../../components/register-header/register-header";
import { RegisterFooter } from "../../components/register-footer/register-footer";

const routes:Routes = [
    {
        path: "/registrar",
        component: RegisterHeader
    },
    {
        path: "/registrar",
        component: RegisterFooter
    }
]