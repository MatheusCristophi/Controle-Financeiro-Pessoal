import { Routes } from '@angular/router';
import { HeaderHome } from '../../components/home-header/header';
import { FooterHome } from '../../components/home-footer/footer';

export const routes: Routes = [
    {
        path: '',
        component: HeaderHome
    },
    {
        path: '',
        component: FooterHome
    }
]