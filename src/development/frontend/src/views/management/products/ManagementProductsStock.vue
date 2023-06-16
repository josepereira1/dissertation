<template>
    <div class="row">
        <div>
            <NavBar />
        </div>
    </div>
    <div class="row">
        <div class="col-6">
            <h3 class="d-flex align-content-start">Search Product:</h3>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="basic-addon1">#ID:</span>
                </div>
                <input type="text" class="form-control" placeholder="" aria-label="" aria-describedby="basic-addon1" v-model="productId">
            </div>

            <button v-on:click="getProduct" type="button" class="mx-1 mb-3 btn btn-primary">SEARCH PRODUCT</button>

            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="basic-addon1">Unit:</span>
                </div>
                <input v-if="product.id == null" type="text" class="form-control" placeholder="" aria-label="" aria-describedby="basic-addon1" v-model="stockUnit" disabled>
                <input v-else type="text" class="form-control" placeholder="" aria-label="" aria-describedby="basic-addon1" v-model="stockUnit">
            </div>

            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="basic-addon1">Stock:</span>
                </div>
                <input v-if="product.id == null" type="number" class="form-control" placeholder="" aria-label="" aria-describedby="basic-addon1" v-model="stockQuantity" disabled>
                <input v-else type="number" class="form-control" placeholder="" aria-label="" aria-describedby="basic-addon1" v-model="stockQuantity">
            </div>

            <button v-if="product.id == null" v-on:click="updateProduct" type="button" class="mx-1 mb-3 btn btn-primary" disabled>UPDATE PRODUCT</button>
            <button v-else v-on:click="updateProduct" type="button" class="mx-1 mb-3 btn btn-primary">UPDATE PRODUCT</button>
        </div>
        <div v-if="product.id != null" class="col-6">
            <h3 class="d-flex align-content-start">Product info:</h3>
            <table class="table table-hover">
                <thead>
                </thead>
                <tbody v-if="product && product != null">
                    <tr>
                        <th scope="row">#ID:</th>
                        <td>{{  product.id }}</td>
                    </tr>
                    <tr>
                        <th scope="row">Unit:</th>
                        <td>{{  product.stockUnit }}</td>
                    </tr>
                    <tr>
                        <th scope="row">Stock:</th>
                        <td>{{  product.stockQuantity  }}</td>
                    </tr>
                    <tr>
                        <th scope="row">Status:</th>
                        <td v-if="product.stockStatus == 'HAS_STOCK'"><b><span style="color: green">IN STOCK</span></b></td>
                        <td v-else-if="product.stockStatus == 'NO_STOCK'"><b><span style="color: red">NO STOCK</span></b></td>
                        <td v-else-if="product.stockStatus == 'LOW_STOCK'"><b><span style="color: orange">LOW STOCK</span></b></td>
                        <td v-else><b><span style="color: black">NO INFO</span></b></td>
                    </tr>
                    <tr>
                        <th scope="row">SKU:</th>
                        <td>{{  product.sku  }}</td>
                    </tr>
                    <tr>
                        <th scope="row">EAN:</th>
                        <td>{{  product.ean  }}</td>
                    </tr>
                    <tr>
                        <th scope="row">PN:</th>
                        <td>{{  product.pn  }}</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</template>

<script>

import axios from "axios"
import auth from '@/js/auth.js'

import NavBar from '@/components/NavBar.vue'

export default {
    name: 'ManagementProductsStock',
    components: {NavBar},
    data(){
        return {
            accessToken: localStorage.getItem('accessToken'),
            role: localStorage.getItem('role'),
            userId: localStorage.getItem('userId'),
            authorizedRoles: ['manager'],
            nonAuthRedirect: 'ManagementLogin',
            product: {},
            productId: '',
            stockUnit: null,
            stockQuantity: null
        }
    },
    methods: {
        async getProduct(){
            auth(this)

            this.product.id = this.productId;

            const options = {
                url: process.env.VUE_APP_BACKEND_URL + '/api/products/' + this.product.id + '/stock',
                method: 'GET',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json;charset=UTF-8',
                    'Authorization': 'Bearer ' + localStorage.getItem('accessToken')
                }
            };
            await axios(options)
                .then(response => this.product = response.data)
                .catch(err => console.log(err))
        },
        async updateProduct(){
            auth(this)
            this.product.id = this.productId;
            let data = {}
            if(this.stockUnit && this.stockUnit != null && this.stockUnit != ''){
                //  UNIT, KG, G, MG, KL, L, ML
                switch(this.stockUnit.toLowerCase()){
                    case 'unit':
                        this.stockUnit = 'UNIT'
                        data.stockUnit = this.stockUnit
                        break
                    case 'kg':
                        console.log('teste')
                        this.stockUnit = 'KG'
                        data.stockUnit = this.stockUnit
                        break;
                    case 'g':
                        this.stockUnit = 'G'
                        data.stockUnit = this.stockUnit
                        break;
                    case 'mg':
                        this.stockUnit = 'MG'
                        data.stockUnit = this.stockUnit
                        break;
                    case 'kl':
                        this.stockUnit = 'KL'
                        data.stockUnit = this.stockUnit
                        break;
                    case 'l':
                        this.stockUnit = 'L'
                        data.stockUnit = this.stockUnit
                        break;
                    case 'ml':
                        this.stockUnit = 'ML'
                        data.stockUnit = this.stockUnit
                        break;
                    default:
                        break
                }
            }

            if(this.stockQuantity && this.stockQuantity != null){
                data.stockQuantity = this.stockQuantity
            }

            if(data && data != null && (data.stockUnit != null || data.stockQuantity != null)){

                const options = {
                    url: process.env.VUE_APP_BACKEND_URL + '/api/products/' + this.product.id + '/stock',
                    method: 'PATCH',
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json;charset=UTF-8',
                        'Authorization': 'Bearer ' + localStorage.getItem('accessToken')
                    },
                    data: data
                };
                await axios(options)
                    .then(response => this.product = response.data)
                    .catch(err => console.log(err))
            }

            this.stockUnit = null
            this.stockQuantity = null
        }
    }
}
</script>

<style>

</style>