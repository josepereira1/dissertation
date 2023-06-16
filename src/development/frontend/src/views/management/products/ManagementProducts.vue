<template>
    <div class="row">
        <NavBar />
    </div>
    <div class="row">
        <div class="col-2">
            <Sidebar />
        </div>
        <div class="col-10">
            <div v-if="!category">
                <h3>Loading products ...</h3>
            </div>
            <div v-else-if="category.products && category.products.length == 0">
                <h3>There are no products in {{ category.name }} category.</h3>
            </div>
            <div v-else-if="category && category.products && category.products.length">    
                <div v-for="i in lines" v-bind:key="i">
                    <div v-if="category.products.length >= 1" class="d-flex justify-content-center" style="display: flex; width: 100%">
                        <div id="product" v-for="product in category.products.slice(productsPerLine*(i-1),productsPerLine*i)" v-bind:key="product.id" class="my-card">
                            <router-link v-bind:to="{ name: 'ManagementUpdateProduct', params: { id: product.id}}">
                                <div class="card card-body" style="width: 18rem; height: 100%; display: flex; margin: 0;">
                                    <img v-bind:src="product.links.thumbnails" class="card-img-top" alt="..." style="height: 100%">
                                    <h5 class="card-title" style="height: 100%;">{{ product.name }} </h5>
                                    <p class="card-text" style="height: 100%;">{{ product.shortDetails }}</p>
                                    <li v-if="product.discountPercentage != null && product.discountPercentage > 0" style="background: green; color: white" class="list-group-item"><b>{{ product.discountPercentage.toFixed(0) + ' %' }}</b></li>
                                    <li v-else-if="product.discountPercentage != null && product.discountPercentage <= 0" style="background: grey; color: white" class="list-group-item"><b>No promotion</b></li>
                                    <li v-if="product.discountPercentage > 0 && product.finalPrice && product.finalPrice != null && product.currency && product.currency != null" style="color: black" class="list-group-item"><b><span style="color:red;"><span style="text-decoration: line-through">{{  (product.finalPrice + product.amountInDiscount).toFixed(2)  }}</span> {{  product.currency  }}</span> - <span style="color: green;">{{ product.finalPrice.toFixed(2) + ' ' + product.currency  }}</span></b></li>
                                    <li v-else-if="product.discountPercentage <= 0 && product.finalPrice && product.finalPrice != null && product.currency && product.currency != null" style="color: black" class="list-group-item"><b>{{ product.finalPrice.toFixed(2) + ' ' + product.currency  }}</b></li>
                                    <li v-if="product.stockStatus && product.stockStatus == 'HAS_STOCK'" style="color: green" class="list-group-item"><b>IN STOCK</b></li>
                                    <li v-else-if="product.stockStatus && product.stockStatus == 'LOW_STOCK'" style="color: orange" class="list-group-item"><b>LOW STOCK</b></li>
                                    <li v-else style="color: red" class="list-group-item"><b>NO STOCK</b></li>
                                </div>
                            </router-link>
                        </div>
                    </div>
                </div>

                <nav class="my-3 d-flex justify-content-center" aria-label="Page navigation example">
                    <ul class="pagination">
                        <li v-for="i in pages" v-bind:key="i" class="page-item">
                            <div v-if="i == page">
                                <router-link v-bind:to="{ name: 'ManagementProducts', params: {id: category.id}, query: {page: i}}"><div class="page-link" style="background: grey;color: white;">{{  i  }}</div></router-link>
                            </div>
                            <div v-else>
                                <router-link v-bind:to="{ name: 'ManagementProducts', params: {id: category.id}, query: {page: i}}"><div class="page-link">{{  i  }}</div></router-link>
                            </div>
                        </li>
                    </ul>
                </nav>
            </div>
        </div>
    </div>
</template>

<script>

import auth from '@/js/auth.js'

import NavBar from '@/components/NavBar.vue'
import Sidebar from '@/components/Sidebar.vue'

export default {
    name: 'ManagementProducts',
    props: ['id'],
    components: {Sidebar, NavBar},
    data(){
        let currentPage = 1;
        if(this.$route.query.page){
            currentPage = this.$route.query.page
        }

        return {
            accessToken: localStorage.getItem('accessToken'),
            role: localStorage.getItem('role'),
            userId: localStorage.getItem('userId'),
            authorizedRoles: ['manager'],
            nonAuthRedirect: 'ManagementLogin',
            productsPerLine: 3,
            lines: 10,
            categoryId: 1,
            page: currentPage,
            category: null,
            pages: 1
        }
    },
    async mounted(){
        auth(this)

        if(this.$route.query.page){
            this.page = this.$route.query.page
        }else{
            this.page = 1
        }

        this.category = {
            products: []
        }
        this.categoryId = this.id;

        if(this.categoryId 
            && this.categoryId != null 
            && this.categoryId > 0 
            && this.page > 0){
            const options = {
                url: process.env.VUE_APP_BACKEND_URL + '/api/categories/' + this.categoryId + '/products?page=' + this.page + '&products_per_page=' + (this.productsPerLine * this.lines) + '&visibility=all',
                method: 'GET',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json;charset=UTF-8',
                    'Authorization': 'Bearer ' + localStorage.getItem('accessToken')
                }
            };
            await axios(options).then(response => this.category = response.data).catch(err => console.log(err))

            let calc = (parseInt(this.category.size)/(this.productsPerLine * this.lines))
            if(calc > parseInt(calc)){
                this.pages = parseInt(calc) + 1;
            }else{
                this.pages = calc
            }
        }
    },
    watch: {
        async $route(to, from) {
            auth(this)

            if(this.$route.query.page){
                this.page = this.$route.query.page
            }else{
                this.page = 1
            }

            if(to.name == 'ManagementProducts' 
                && localStorage.getItem('role') 
                && localStorage.getItem('role') != null 
                && localStorage.getItem('role') == 'manager' 
                && localStorage.getItem('accessToken') 
                && localStorage.getItem('accessToken') != null 
                && localStorage.getItem('accessToken') != '' 
                && this.categoryId 
                && this.categoryId != null 
                && this.categoryId > 0 
                && this.page > 0){
                this.category = {
                    products: []
                }
                this.categoryId = to.params.id

                const options = {
                    url: process.env.VUE_APP_BACKEND_URL + '/api/categories/' + this.categoryId + '/products?page=' + this.page + '&products_per_page=' + (this.productsPerLine * this.lines) + '&visibility=all',
                    method: 'GET',
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json;charset=UTF-8',
                        'Authorization': 'Bearer ' + localStorage.getItem('accessToken')
                    }
                };
                await axios(options)
                    .then(response => this.category = response.data)
                    .catch(err => console.log(err))

                let calc = (parseInt(this.category.size)/(this.productsPerLine * this.lines))
                if(calc > parseInt(calc)){
                    this.pages = parseInt(calc) + 1;
                }else{
                    this.pages = calc
                }
            }
        }
    }
}
</script>

<style scoped>

.my-card {
    margin: 5px;
}

</style>