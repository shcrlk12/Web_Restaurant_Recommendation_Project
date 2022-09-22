import {createWebHistory, createRouter} from 'vue-router'
import HomePage from '../components/page/HomePage'
import FoodDetailpage from '../components/page/FoodDetailpage'

const routes = [
    {
        path : '/',
        alias : ['/', '/foodOverviewList'],
        name : 'HomePage',
        component : HomePage
    },
    {
        path : '/foodDetail/:id',
        name : 'FoodDetailpage',
        component : FoodDetailpage
    }
]

const router = createRouter({
    history : createWebHistory(),
    routes
})

export default router;