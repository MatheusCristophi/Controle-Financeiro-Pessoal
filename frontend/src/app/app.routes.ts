import { Routes } from '@angular/router';
import { HomeLandingPage } from './pages/home-landing-page/home-landing-page';
import { Login } from './pages/login/login';
import { Register } from './pages/register/register';

export const routes: Routes = [
    {
        path: '',
        component: HomeLandingPage
    },
    {
        path: 'registrar',
        component: Register
    },
    {
        path: 'login',
        component: Login
    }
];
