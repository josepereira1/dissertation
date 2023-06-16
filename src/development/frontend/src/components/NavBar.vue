<template>
	<div v-if="accessToken && accessToken != null && accessToken != '' && role && role != null && role != ''">
            <div class="row">
                <ul v-if="role && role == 'consumer'" id="nav" class="nav d-flex justify-content-end my-3">
                    <li class="nav-item mx-3 my-2">
                        <router-link v-bind:to="{ name: 'Products', params: {id: 1}, query: {page: 1}}">Home</router-link>
                    </li>
                    <li class="nav-item mx-3 my-2">
                        <router-link to="/processes">Processes</router-link>
                    </li>
                    <li class="nav-item mx-3 my-2">
                        <router-link v-bind:to="{ name: 'Orders'}">Orders</router-link>
                    </li>
                    <li class="nav-item mx-3 my-2">|</li>
					<li class="nav-item mx-3 my-2"><b>{{ role  }}:</b> {{ userId.split('@')[1] }}</li>
                    <li class="nav-item mx-3 my-2">|</li>
                    <div v-if="changed">
                        <router-link v-bind:to="{ name: 'ShoppingCart'}">
                            <li class="nav-item mx-3 my-2">
                                <span class="shoppingCart shoppingCartChanged">SHOPPINGCART: {{ shoppingCartSize }}</span>
                            </li>
                        </router-link>
                    </div>
                    <div v-else>
                        <router-link v-bind:to="{ name: 'ShoppingCart'}">
                            <li class="nav-item mx-3 my-2">
                                <span class="shoppingCart shoppingCartNotChanged">SHOPPINGCART: {{ shoppingCartSize }}</span>
                            </li>
                        </router-link>
                    </div>
                    <li class="nav-item mx-3 my-2">|</li>
                    <li class="nav-item mx-3 my-2">
                        <router-link v-bind:to="{ name: 'Login'}">Logout</router-link>
                    </li>
                </ul>
                <ul v-else-if="role && role == 'manager'" id="nav" class="nav d-flex justify-content-end my-3">
                    <li class="nav-item mx-3 my-2">
                        <router-link v-bind:to="{ name: 'ManagementProducts', params: {id: 1}, query: {page: 1}}">Home</router-link>
                    </li>
                    <li class="nav-item mx-3 my-2">
                        <router-link to="/management/processes">Processes</router-link>
                    </li>
                    <li class="nav-item mx-3 my-2">
                        <router-link v-bind:to="{ name: 'ManagementOrders'}">Orders</router-link>
                    </li>
                    <li class="nav-item mx-3 my-2">
                        <router-link v-bind:to="{ name: 'Categories'}">Categories</router-link>
                    </li>
                    <li class="nav-item mx-3 my-2">
                        <router-link v-bind:to="{ name: 'ManagementCreateProduct'}">Create Product</router-link>
                    </li>
                    <li class="nav-item mx-3 my-2">
                        <router-link v-bind:to="{ name: 'ManagementProductsStock'}">Stock</router-link>
                    </li>
                    <!--<li class="nav-item mx-3 my-2">
                        <router-link v-bind:to="{ name: 'Orders'}">Orders</router-link>
                    </li>-->
                    <li class="nav-item mx-3 my-2">|</li>
					<li class="nav-item mx-3 my-2"><b>{{ role  }}:</b> {{ userId.split('@')[1] }}</li>
                    <li class="nav-item mx-3 my-2">|</li>
                    <li class="nav-item mx-3 my-2">
                        <router-link v-bind:to="{ name: 'ManagementLogin'}">Logout</router-link>
                    </li>
                </ul>
            </div>
        </div>
        <div v-else>
            <div class="row">
                <ul id="nav" class="nav d-flex justify-content-end my-3">
                    <li class="nav-item mx-3 my-2">
                        <router-link v-bind:to="{ name: 'Products', params: {id: 1}, query: {page: 1}}">Home</router-link>
                    </li>
                    <li class="nav-item mx-3 my-2">
                        <router-link v-bind:to="{ name: 'Login'}">Consumer Login</router-link>
                    </li>
                    <li class="nav-item mx-3 my-2">
                        <router-link v-bind:to="{ name: 'ManagementLogin'}">Manager Login</router-link>
                    </li>
                </ul>
            </div>
        </div>
</template>

<script>

import axios from "axios"

export default {
    name: 'NavBar',
    props: ['size'],
	data(){

		return{
			accessToken: localStorage.getItem('accessToken'),
			userId: localStorage.getItem('userId'),
            role: localStorage.getItem('role'),
            shoppingCartSize: 0,
            changed: false
		}
	},
    async mounted(){
        if(this.size) {
            this.shoppingCartSize = this.size
            this.changed = true;
        }else if (localStorage.getItem('role') && localStorage.getItem('role') == 'consumer' && localStorage.getItem('accessToken') && localStorage.getItem('accessToken') != null && localStorage.getItem('accessToken') != ''){
            const options = {
                url: process.env.VUE_APP_BACKEND_URL + '/api/shoppingcarts/' + localStorage.getItem('userId') + '/size',
                method: 'GET',
                validateStatus: () => true,
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json;charset=UTF-8',
                    'Authorization': 'Bearer ' + localStorage.getItem('accessToken')
                }
                };
            await axios(options).then(response => { 
                    if(response.status == 200)
                        this.shoppingCartSize = response.data
                    else if(response.status == 404)
                        this.shoppingCartSize = 0
                }
            )
        }
    }
}
</script>

<style>

.shoppingCart {
    padding: 10px;
    border-radius: 3px;
}

.shoppingCartNotChanged {
    background: rgb(173, 173, 173);
}

.shoppingCartChanged {
    background: rgb(94, 255, 134);
}

</style>