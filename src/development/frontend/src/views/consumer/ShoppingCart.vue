<template>
    <div v-if="shoppingCartSize != null">
        <NavBar v-bind:size="shoppingCartSize"/>
    </div>
    <div v-else>
        <NavBar />
    </div>
    <button v-on:click="orderCart" type="button" class="mx-2 mt-3 btn btn-primary">ORDER CART</button>
    <button v-on:click="clearCart" type="button" class="mx-2 mt-3 btn btn-secondary">CLEAR CART</button>
    <table class="my-3 table">
        <thead>
            <tr>
                <th></th>
                <th>#id</th>
                <th>Name</th>
                <th>SKU</th>
                <th>Quantity</th>
                <th>Price</th>
            </tr>
        </thead>
        <tbody v-if="shoppingCart && shoppingCart != null && shoppingCart.products && shoppingCart.products != null && shoppingCart.products.length >= 1">
            <tr v-for="product in shoppingCart.products" v-bind:key="product">
                <td class="align-middle">
                    <img v-bind:src="product.product.links.thumbnails" class="card-img-top" style="height: 50px; width: 50px">
                </td>
                <td class="align-middle"><router-link v-bind:to="{ name: 'ProductDetails', params: { id: product.product.id}}">{{  product.product.id  }}</router-link></td>
                <td class="align-middle"><router-link v-bind:to="{ name: 'ProductDetails', params: { id: product.product.id}}">{{  product.product.name  }}</router-link></td>
                <td class="align-middle">{{  product.product.sku  }}</td>
                <td class="align-middle">{{  product.quantity  }}</td>
                <td class="align-middle">{{  product.product.finalPrice.toFixed(2)  }} {{  product.product.currency  }}</td>
            </tr>
            <tr>
                <th></th>
                <td class="align-middle"></td>
                <td class="align-middle"></td>
                <td class="align-middle"></td>
                <td class="align-middle"><b>{{  quantity  }}</b></td>
                <td class="align-middle"><b>{{  shoppingCart.total.toFixed(2)  }} {{  shoppingCart.products[0].product.currency  }}</b></td>
            </tr>
        </tbody>
    </table>
    <span v-if="shoppingCart == null || shoppingCart.products == null || (shoppingCart != null && shoppingCart.products != null && shoppingCart.products.length == 0)">There are no products in your cart</span>
</template>

<script>

import axios from "axios"
import NavBar from '@/components/NavBar.vue'

export default {
    name: 'ShoppingCart',
    components: {NavBar},
    data() {
        return {
            shoppingCart: null,
            shoppingCartSize: 0,
            total: 0,
            quantity: 0,
        }
    },
    async mounted(){
        if(localStorage.getItem('userId')){
            const options = {
                url: process.env.VUE_APP_BACKEND_URL + '/api/shoppingcarts/' + localStorage.getItem('userId'),
                method: 'GET',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json;charset=UTF-8',
                    'Authorization': 'Bearer ' + localStorage.getItem('accessToken')
                }
            };
            await axios(options)
                    .then(response => this.shoppingCart = response.data)
                    .catch(err => console.log(err))
            this.shoppingCartSize = this.shoppingCartSize.size
            let sum = 0
            let quantity = 0
            if(this.shoppingCart != null && this.shoppingCart.products != null){
                for (var i = 0; i < this.shoppingCart.products.length; i++) {
                    sum = sum + this.shoppingCart.products[i].product.finalPrice
                    quantity = quantity + this.shoppingCart.products[i].quantity
                }
            }
            this.total = sum
            this.quantity = quantity
        }
    },
    methods: {
        async orderCart(){
            if(localStorage.getItem('userId')){
                const options = {
                    url: process.env.VUE_APP_BACKEND_URL + '/api/processes?id=create.order.saga.0',
                    method: 'POST',
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json;charset=UTF-8',
                        'Authorization': 'Bearer ' + localStorage.getItem('accessToken')
                    },
                    data: {
                        "tin": "333444555",
                        "chargeAddress": {
                            "address": "Rua da Boa Vista, Nº500",
                            "city": "Porto",
                            "postalCode": "4555-100",
                            "country": "Portugal"
                        },
                        "deliveryAddress": {
                            "address": "Rua da Boa Vista, Nº500",
                            "city": "Porto",
                            "postalCode": "4555-100",
                            "country": "Portugal"
                        },
                        "other" : {
                            "notes": "premium consumer"
                        }
                    }
                };
                await axios(options).then(response => {
                    this.shoppingCart = null
                    this.shoppingCartSize = 0
                    this.redirect('Orders')
                })
            }
        },
        async clearCart(){
            if(localStorage.getItem('userId')){
                const options = {
                    url: process.env.VUE_APP_BACKEND_URL + '/api/shoppingcarts/' + localStorage.getItem('userId') + '/products',
                    method: 'DELETE',
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json;charset=UTF-8',
                        'Authorization': 'Bearer ' + localStorage.getItem('accessToken')
                    }
                };
                await axios(options).then(response => {
                    this.shoppingCart = null
                    this.shoppingCartSize = 0
                })
            }
        },
        redirect(to) {
            this.$router.push({ name: to})
        }
    }
}
</script>

<style>

</style>