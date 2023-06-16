<template>
    <div class="row">
        <div v-if="shoppingCartSize">
            <NavBar v-bind:size="shoppingCartSize"/>
        </div>
        <div v-else>
            <NavBar />
        </div>
    </div>
    <div v-if="product">
        <div class="container">
            <div class="row">
                <div class="col-7">
                    <Carousel :links="product.links"/>
                </div>
                <div class="col-5 d-flex align-items-center">
                    <div class="mb-3">
                        <h4 class="mb-3">{{ product.name }}</h4>
                        <ul class="list-group" style="width: 100%;">
                            <li v-if="product.discountPercentage > 0" lass="list-group-item" style="background: green; color: white">PROMOTION: <span>{{  product.discountPercentage  }} %</span></li>
                            <li class="list-group-item"><span class="">ID: {{  product.id  }} | EAN: {{  product.ean  }} | PN: {{  product.pn  }}</span></li>
                            <li class="list-group-item"></li>
                            <li class="list-group-item">{{  product.shortDetails  }}</li>
                            <li v-if="product.discountPercentage > 0" class="list-group-item">Before: <b><span style="text-decoration: line-through">{{  (product.finalPrice + product.amountInDiscount).toFixed(2)  }}</span> {{  product.currency  }}</b> Now: <span><b style="color: green">{{  product.finalPrice.toFixed(2)  }} {{  product.currency  }}</b> (<span style="color: green">-{{  product.amountInDiscount.toFixed(2)  }} {{  product.currency  }}</span>)</span></li>
                            <li v-else class="list-group-item"><span><b>{{  product.finalPrice.toFixed(2)  }} {{  product.currency  }}</b></span></li>
                            <li class="list-group-item">
                                <div v-if="product.stockStatus">
                                    <div v-if="product.stockStatus == 'HAS_STOCK'">
                                        <span class="mt-3"><span style="color: green">IN STOCK</span></span>
                                    </div>
                                    <div v-else-if="product.stockStatus == 'LOW_STOCK'">
                                        <span class="mt-3"><span style="color: orange">LOW STOCK</span></span>
                                    </div>
                                    <div v-else>
                                        <span><span style="color: red">NO STOCK</span></span>
                                    </div>
                                </div>
                                <div v-else>
                                    <span><span style="color: red">NO STOCK</span></span>
                                </div>
                            </li>
                            <li class="list-group-item">
                                <div v-if="accessToken && accessToken != ''">
                                    <div v-if="productAdded && productAdded == 'yes'" class="">
                                        Quantity: <input type="number" required v-model="quantity" class="mx-1" style="width: 15%; height: 35px" min="1" max="9999"><button v-on:click="addToCart" type="button" class="btn btn-success">ADDED</button>
                                    </div>
                                    <div v-else-if="productAdded && productAdded == 'no'" class="">
                                        Quantity: <input type="number" required v-model="quantity" class="mx-1" style="width: 15%; height: 35px" min="1" max="9999"><button v-on:click="addToCart" type="button" class="btn btn-primary">ADD TO CART</button>
                                    </div>
                                </div>
                                <div v-else>
                                    <div v-if="productAdded && productAdded == 'yes'" class="">
                                        Quantity: <input type="number" required v-model="quantity" class="mx-1" style="width: 15%; height: 35px" min="1" max="9999"><button v-on:click="addToCart" type="button" class="btn btn-success" disabled>ADDED</button>
                                    </div>
                                    <div v-else-if="productAdded && productAdded == 'no'" class="">
                                        Quantity: <input type="number" required v-model="quantity" class="mx-1" style="width: 15%; height: 35px" min="1" max="9999"><button v-on:click="addToCart" type="button" class="btn btn-primary" disabled>ADD TO CART</button>
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>

            <div class="line2 my-3"></div>

            <h3 class="mb-3 d-flex align-content-start">Specs: </h3>
            <div class="mb-3 d-flex align-content-start" v-for="item in product.longDetails.specs" v-bind:key="item">
                <ul class="list-group" style="width: 100%;">
                    <li class="list-group-item"><b>{{ item.split(':')[0] + ": " }}</b>{{ item.split(':')[1] }}</li>
                </ul>
            </div>

            <div class="line2 my-3"></div>

            <h3 class="mb-3 d-flex align-content-start">Details: </h3>
            <div v-for="paragraph in product.longDetails.paragraphs" v-bind:key="paragraph">
                <div class="card my-3" style="width: 100%;">
                    <div class="card-body">
                        <h5 class="card-title">{{ paragraph.title }}</h5>
                        <p class="card-text">{{ paragraph.text }}</p>
                    </div>
                </div>
            </div>
            <div class="mb-5"></div>
        </div>
    </div>
</template>
<script>

import axios from "axios"
import NavBar from '@/components/NavBar.vue'
import Carousel from '@/components/Carousel.vue'

export default {
    name: 'ProductDetails',
    props: ['id'],
    components: {Carousel, NavBar},
    data(){
        return {
            accessToken: localStorage.getItem('accessToken'),
            product: null,
            productAdded: 'no',
            shoppingCartSize: null,
            quantity: 1
        }
    },
    mounted(){
        fetch(process.env.VUE_APP_BACKEND_URL + '/api/products/' + this.id + '/visible')
            .then(res => res.json())
            .then(data => this.product = data)
            .catch(err => console.log(err.message))
    },
    methods: {
        async addToCart(){
            if(this.product){
                const options = {
                    url: process.env.VUE_APP_BACKEND_URL + '/api/shoppingcarts/' + localStorage.getItem('userId'),
                    method: 'PATCH',
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json;charset=UTF-8',
                        'Authorization': 'Bearer ' + localStorage.getItem('accessToken')
                    },
                    data: [
                            {
                                "productId": this.product.id,
                                "quantity": this.quantity
                            }
                        ]
                    };
                await axios(options).then(response => this.shoppingCartSize = response.data.size)
                this.productAdded = 'yes'
            }
        }
    }
}
</script>

<style>

</style>