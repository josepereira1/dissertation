import { createRouter, createWebHistory } from 'vue-router'

//  consumer
import Login from '@/views/consumer/Login.vue'
import Products from '@/views/consumer/products/Products.vue'
import ProductDetails from '@/views/consumer/products/ProductDetails.vue'
import ShoppingCart from '@/views/consumer/ShoppingCart.vue'
import Orders from '@/views/consumer/orders/Orders.vue'
import OrderDetails from '@/views/consumer/orders/OrderDetails.vue'

//  management
import ManagementLogin from '@/views/management/ManagementLogin.vue'
import ManagementProducts from '@/views/management/products/ManagementProducts.vue'
import Categories from '@/views/management/categories/Categories.vue'
import ManagementCreateProduct from '@/views/management/products/ManagementCreateProduct.vue'
import ManagementUpdateProduct from '@/views/management/products/ManagementUpdateProduct.vue'
import ManagementProductsStock from '@/views/management/products/ManagementProductsStock.vue'
import ManagementOrders from '@/views/management/orders/ManagementOrders.vue'
import ManagementOrderDetails from '@/views/management/orders/ManagementOrderDetails.vue'

//  generic
import Processes from '@/views/com/Processes.vue'
import NotFound from '@/views/NotFound.vue'

const routes = [
  {
    path: '/',
    name: 'Home',
    redirect: '/categories/1/products'
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/categories/:id/products',
    name: 'Products',
    component: Products,
    props: true
  },
  {
    path: '/products/:id',
    name: 'ProductDetails',
    component: ProductDetails,
    props: true
  },
  {
    path: '/shoppingcart',
    name: 'ShoppingCart',
    component: ShoppingCart,
  },
  {
    path: '/orders',
    name: 'Orders',
    component: Orders
  },
  {
    path: '/orders/:id',
    name: 'OrderDetails',
    component: OrderDetails,
    props: true
  },
  {
    path: '/management/login',
    name: 'ManagementLogin',
    component: ManagementLogin
  },
  {
    path: '/',
    name: 'ManagementHome',
    redirect: '/management/categories/1/products'
  },
  {
    path: '/management/categories/:id/products',
    name: 'ManagementProducts',
    component: ManagementProducts,
    props: true
  },
  {
    path: '/management/products',
    name: 'ManagementCreateProduct',
    component: ManagementCreateProduct,
    props: true
  },
  {
    path: '/management/products/:id',
    name: 'ManagementUpdateProduct',
    component: ManagementUpdateProduct,
    props: true
  },
  {
    path: '/management/products/stock',
    name: 'ManagementProductsStock',
    component: ManagementProductsStock,
    props: true
  },
  {
    path: '/processes',
    name: 'ConsumerProcesses',
    component: Processes,
  },
  {
    path: '/management/processes',
    name: 'ManagementProcesses',
    component: Processes,
  },
  {
    path: '/management/categories',
    name: 'Categories',
    component: Categories,
  },
  {
    path: '/management/orders',
    name: 'ManagementOrders',
    component: ManagementOrders,
  },
  {
    path: '/management/orders/:id',
    name: 'ManagementOrderDetails',
    component: ManagementOrderDetails,
    props: true
  },
  {
    path: '/:catchAll(.*)',
    name: 'NotFound',
    component: NotFound
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router