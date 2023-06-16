<template>
    <div class="row">
        <div>
            <NavBar />
        </div>
    </div>
    <!--<h2 id='top' class="d-flex align-content-start">CREATE PRODUCT MANAGEMENT:</h2>-->
    <div>
        <div class="container">
            <button v-on:click="createProduct" type="button" class="btn btn-primary">CREATE PRODUCT</button>
            <a class="mx-3 navigation-button" href="#preview">PREVIEW CONSUMER VIEW</a>

            <div class="line4 mb-3"></div>

            <h3 class="mb-3 d-flex align-content-start">DEFINITION MANAGEMENT</h3>
            <div class="form-floating">
                <textarea class="my-2 form-control" placeholder="" id="floatingTextarea" v-model="product.id"></textarea>
                <label for="floatingTextarea">Id</label>
            </div>
            <div class="form-floating">
                <textarea class="my-2 form-control" placeholder="" id="floatingTextarea" v-model="product.sku"></textarea>
                <label for="floatingTextarea">SKU</label>
            </div>
            <div class="form-floating">
                <textarea class="my-2 form-control" placeholder="" id="floatingTextarea" v-model="product.ean"></textarea>
                <label for="floatingTextarea">EAN</label>
            </div>
            <div class="form-floating">
                <textarea class="my-2 form-control" placeholder="" id="floatingTextarea" v-model="product.pn"></textarea>
                <label for="floatingTextarea">PN</label>
            </div>
            <div class="form-floating">
                <textarea class="my-2 form-control" placeholder="" id="floatingTextarea" v-model="product.name"></textarea>
                <label for="floatingTextarea">Name</label>
            </div>
            <div class="form-floating">
                <textarea class="my-2 form-control" placeholder="" id="floatingTextarea" v-model="product.shortDetails"></textarea>
                <label for="floatingTextarea">Short details</label>
            </div>

            <div class="line4 my-3"></div>

            <h3 class="mb-3 d-flex align-content-start">VALUES MANAGEMENT</h3>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="basic-addon1">Currency:</span>
                </div>
                <input type="text" class="form-control" placeholder="EUR" aria-label="" aria-describedby="basic-addon1" v-model="product.currency">
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span v-if="product.currency" class="input-group-text" id="basic-addon1">Initial price ({{  product.currency }}):</span>
                    <span v-else class="input-group-text" id="basic-addon1">Initial price:</span>
                </div>
                <input type="number" class="form-control" placeholder="Initial price" aria-label="" aria-describedby="basic-addon1" v-model="product.initialPrice">
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="basic-addon1">Discount (%):</span>
                </div>
                <input type="number" class="form-control" placeholder="Discount" aria-label="" aria-describedby="basic-addon1" v-model="product.discountPercentage">
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="basic-addon1">Vat (%):</span>
                </div>
                <input type="number" class="form-control" placeholder="Vat" aria-label="" aria-describedby="basic-addon1" v-model="product.vatPercentage">
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="basic-addon1">Shipping:</span>
                </div>
                <input type="number" class="form-control" placeholder="Shipping" aria-label="" aria-describedby="basic-addon1" v-model="product.shipping">
            </div>

            <div class="line4 my-3"></div>

            <h3 class="mb-3 d-flex align-content-start">IMAGES MANAGEMENT</h3>

            <div class="form-floating">
                <textarea class="my-2 form-control" placeholder="" id="floatingTextarea" v-model="thumbnailFilename"></textarea>
                <label for="floatingTextarea">Card thumbnail image filename</label>
            </div>
            <button v-on:click="addThumbnailImageLink" type="button" class="my-3 btn btn-primary">UPDATE CARD THUMBNAIL IMAGE</button>
            
            <h4 class="mb-3 d-flex align-content-start">Card Thumbnail (filename):</h4>
            <ul class="list-group" style="width: 100%;">
                <li v-if="product.links.thumbnails && product.links.thumbnails != null && product.links.thumbnails != ''" class="list-group-item">{{ product.links.thumbnails.split('/').at(-1).split('?')[0] }}</li>
            </ul>

            <div class="line2 my-3"></div>
            
            <div class="form-floating">
                <textarea class="my-2 form-control" placeholder="" id="floatingTextarea" v-model="carouselFilename"></textarea>
                <label for="floatingTextarea">Carousel image filename</label>
            </div>
            <button v-on:click="addCarouselImageLink" type="button" class="my-3 btn btn-primary">ADD CAROUSEL IMAGE</button>
            <h4 class="mb-3 d-flex align-content-start">Carousel (filenames):</h4>
            <ul v-for="link in product.links.images" v-bind:key="link" class="list-group" style="width: 100%;">
                <li class="list-group-item">{{ link.split('/').at(-1).split('?')[0] }}<button v-on:click="removeCarouselImageLink(link)" type="button" class="mx-3 btn btn-danger">DEL</button></li>
            </ul>

            <div class="line2 my-3"></div>

            <h3>Preview:</h3>
            <div class="row">
                <div class="col-7">
                    <Carousel :links="product.links"/>
                </div>
                <div class="col-5">
                    <div class="card card-body" style="width: 18rem; display: flex; margin: 0;">
                        <img v-bind:src="product.links.thumbnails" class="card-img-top" alt="..." style="height: 100%">
                        <h5 class="card-title">{{ product.name }} </h5>
                        <p class="card-text">{{ product.shortDetails }}</p>
                        <li v-if="product.discountPercentage != null && product.discountPercentage > 0" style="background: green; color: white" class="list-group-item"><b>{{ product.discountPercentage.toFixed(0) + ' %' }}</b></li>
                        <li v-else-if="product.discountPercentage != null && product.discountPercentage <= 0" style="background: grey; color: white" class="list-group-item"><b>No promotion</b></li>
                        <li v-if="product.discountPercentage > 0 && product.finalPrice && product.finalPrice != null && product.currency && product.currency != null" style="color: black" class="list-group-item"><b><span style="color:red;"><span style="text-decoration: line-through">{{  (product.finalPrice + product.amountInDiscount).toFixed(2)  }}</span> {{  product.currency  }}</span> - <span style="color: green;">{{ product.finalPrice.toFixed(2) + ' ' + product.currency  }}</span></b></li>
                        <li v-else-if="product.discountPercentage <= 0 && product.finalPrice && product.finalPrice != null && product.currency && product.currency != null" style="color: black" class="list-group-item"><b>{{ product.finalPrice.toFixed(2) + ' ' + product.currency  }}</b></li>
                        <li v-if="product.stockStatus && product.stockStatus == 'HAS_STOCK'" style="color: green" class="list-group-item"><b>IN STOCK</b></li>
                        <li v-else-if="product.stockStatus && product.stockStatus == 'LOW_STOCK'" style="color: orange" class="list-group-item"><b>LOW STOCK</b></li>
                        <li v-else style="color: red" class="list-group-item"><b>NO STOCK</b></li>
                    </div>
                </div>
            </div>

            <div class="line4 my-3"></div>

            <h3 class="mb-3 d-flex align-content-start">STATUS MANAGEMENT</h3>
            <div v-if="product.visibility">
                <div v-if="product.visibility == 'VISIBLE'">
                    <h4 class="mt-3">Visibility status (*): <span style="color: green">Visible</span></h4>
                </div>
                <div v-if="product.visibility == 'NOT_VISIBLE'">
                    <h4 class="mt-3">Visibility status (*): <span style="color: orange">Not visible</span></h4>
                </div>
                <button v-on:click="changeVisibility" type="button" class="btn btn-primary">EXCHANGE</button>
                <br>
                <span>* Visibility is a status where you define whether the product is visible to the customer</span>
            </div>

            <div class="line4 my-3"></div>

            <h3 class="mb-3 d-flex align-content-start">SPECS MANAGEMENT</h3>

            <h6>ADD NEW SPEC:</h6>
            <div class="form-floating">
                <textarea class="form-control" placeholder="" id="floatingTextarea" v-model="specName"></textarea>
                <label for="floatingTextarea">Label Spec:</label>
            </div>
            <div class="form-floating">
                <textarea class="form-control" placeholder="" id="floatingTextarea" v-model="specText"></textarea>
                <label for="floatingTextarea">Spec text:</label>
            </div>

            <button v-on:click="addSpec" type="button" class="my-2 btn btn-primary">ADD SPEC</button>

            <h3>Preview:</h3>
            <div class="mb-3 d-flex align-content-start" v-for="item in product.longDetails.specs" v-bind:key="item">
                <ul class="list-group" style="width: 100%;">
                    <li class="list-group-item"><b>{{ item.split(':')[0] + ": " }}</b>{{ item.split(':')[1] }}<button v-on:click="removeSpec(item)" type="button" class="mx-3 btn btn-danger">DEL</button></li>
                </ul>
            </div>

            <div class="line4 my-3"></div>

            <h3 class="mb-3 d-flex align-content-start">DETAILS MANAGEMENT</h3>
            
            <h6>ADD NEW DETAIL:</h6>
            <div class="form-floating">
                <textarea class="form-control" placeholder="" id="floatingTextarea" v-model="detailTitle"></textarea>
                <label for="floatingTextarea">Detail title:</label>
            </div>
            <div class="form-floating">
                <textarea class="form-control" placeholder="" id="floatingTextarea" v-model="detailText"></textarea>
                <label for="floatingTextarea">Detail text:</label>
            </div>

            <button v-on:click="addDetail" type="button" class="my-2 btn btn-primary">ADD DETAIL</button>

            <h3>Preview:</h3>
            <div class="card my-3" style="width: 100%;">
                <div class="card-body">
                    <h5 class="card-title">{{ detailTitle }}</h5>
                    <p class="card-text">{{ detailText }}</p>
                </div>
            </div>

            <div class="line3 my-3"></div>

            <h6>UPDATE AND REMOVE DETAILS:</h6>
            <div v-for="paragraph in product.longDetails.paragraphs" v-bind:key="paragraph">

                <div class="line2 my-3"></div>

                <div class="row">
                    <div class="col-11 form-floating">
                        <textarea class="form-control" placeholder="" id="floatingTextarea" v-model="paragraph.title"></textarea>
                        <label for="floatingTextarea">Title</label>
                    </div>
                    <div class="col-1">
                        <button v-on:click="removeDetail(paragraph)" type="button" class="mx-3 btn btn-danger">DEL</button>
                    </div>
                </div>
                <div class="form-floating">
                    <textarea class="form-control" placeholder="" id="floatingTextarea2" style="height: 100px" v-model="paragraph.text"></textarea>
                    <label for="floatingTextarea2">Details</label>
                </div>
                <h3>Preview:</h3>
                <div class="card my-3" style="width: 100%;">
                    <div class="card-body">
                        <h5 class="card-title">{{ paragraph.title }}</h5>
                        <p class="card-text">{{ paragraph.text }}</p>
                    </div>
                </div>
            </div>
            <div class="mb-5"></div>
        </div>
        
        <div class="line5"></div>
        <div class="line5"></div>

        <h1 id="preview" class="my-3">PREVIEW CONSUMER VIEW</h1>

        <a class="navigation-button" href="#top">GO TO TOP</a>

        <!-- PREVIEW CONSUMER VIEW -->
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
                                        <div v-else>
                                            <span><span style="color: red">NO STOCK</span></span>
                                        </div>
                                    </div>
                                    <div v-else>
                                        <span><span style="color: red">NO STOCK</span></span>
                                    </div>
                                </li>
                                <li class="list-group-item">
                                    <div class="">
                                            Quantity: <input type="number" required v-model="quantity" class="mx-1" style="width: 15%; height: 35px" min="1" max="9999"><button v-on:click="addToCart" type="button" class="btn btn-primary" disabled>ADD TO CART</button>
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
    </div>
</template>

<script>

import axios from "axios"
import auth from '@/js/auth.js'

import NavBar from '@/components/NavBar.vue'
import Carousel from '@/components/Carousel.vue'

export default {
    name: 'ManagementCreateProduct',
    components: {Carousel, NavBar},
    data(){
        return {
            accessToken: localStorage.getItem('accessToken'),
            role: localStorage.getItem('role'),
            userId: localStorage.getItem('userId'),
            authorizedRoles: ['manager'],
            nonAuthRedirect: 'ManagementLogin',
            product: {
                'longDetails': {
                    'paragraphs': [
                    ],
                    'specs': [
                    ]
                },
                "initialPrice": 0,
                "discountPercentage": 0,
                "amountInDiscount": 0,
                "vatPercentage": 0,
                "amountInVat": 0,
                "finalPrice": 0,
                "shipping": 0,
                'links': {
                    'thumbnails': '',
                    'images': []
                },
                'visibility': 'NOT_VISIBLE',
                'categories': []
            },
            quantity: 0,
            unit: 'UNIT',
            specName: '',
            specText: '',
            detailTitle: '',
            detailText: '',
            thumbnailFilename: '',
            carouselFilename: '',
            addCategoryId: '',
            removeCategoryId: ''
        }
    },
    async mounted(){
        auth(this)
    },
    methods: {
        async addStock(){
            auth(this)
            if(this.product){
                const options = {
                    url: process.env.VUE_APP_BACKEND_URL + '/api/products/' + this.product.id + '/stock',
                    method: 'PATCH',
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json;charset=UTF-8',
                        'Authorization': 'Bearer ' + localStorage.getItem('accessToken')
                    },
                    data: {
                            "stockQuantity" : this.quantity,
                            "stockUnit": this.unit
                        }
                    };
                await axios(options).then(response => {
                        this.product.stockStatus = response.data.stockStatus
                        this.quantity = response.data.stockQuantity
                    }
                )
            }
        },
        addSpec(){
            if(this.specName && this.specName != null && this.specName != '' && this.specText && this.specText != null && this.specText != ''){
                this.product.longDetails.specs.push(this.specName + ':' + this.specText)
                this.specName = ''
                this.specText = ''
            }
        },
        removeSpec(spec){
            if(spec && spec != null && spec != ''){
                for( var i = 0; i < this.product.longDetails.specs.length; i++){ 
                    if ( this.product.longDetails.specs[i] === spec) { 
                        this.product.longDetails.specs.splice(i, 1); 
                    }
                }
            }
        },
        addDetail(){
            if(this.detailTitle && this.detailTitle != null && this.detailTitle != '' && this.detailText && this.detailText != null && this.detailText != ''){
                let detail = {
                    'title' : this.detailTitle,
                    'text': this.detailText
                }

                this.product.longDetails.paragraphs.push(detail)
                this.detailTitle = ''
                this.detailText = ''
            }
        },
        removeDetail(detail){
            if(detail && detail != null && detail != ''){
                for(var i = 0; i < this.product.longDetails.paragraphs.length; i++){ 
                    if ( this.product.longDetails.paragraphs[i] === detail) { 
                        this.product.longDetails.paragraphs.splice(i, 1); 
                    }
                }
            }
        },
        changeVisibility(){
            if(this.product.visibility == 'VISIBLE') this.product.visibility = 'NOT_VISIBLE'
            else if(this.product.visibility == 'NOT_VISIBLE') this.product.visibility = 'VISIBLE'
        },
        addThumbnailImageLink(){
            if(this.thumbnailFilename && this.thumbnailFilename != null && this.thumbnailFilename != ''){
                //this.product.links.thumbnails = process.env.VUE_APP_THUMBNAILS_IMAGES_REPO_URL + '/' + this.thumbnailFilename + '?raw=true'
                this.product.links.thumbnails = process.env.VUE_APP_THUMBNAILS_IMAGES_REPO_URL + '/' + this.thumbnailFilename
                this.thumbnailFilename = ''
            }
        },
        addCarouselImageLink(){
            if(this.carouselFilename && this.carouselFilename != null && this.carouselFilename != '') {
                //this.product.links.images.push(process.env.VUE_APP_CAROUSEL_IMAGES_REPO_URL + '/' + this.carouselFilename + '?raw=true')
                this.product.links.images.push(process.env.VUE_APP_CAROUSEL_IMAGES_REPO_URL + '/' + this.carouselFilename)
                this.carouselFilename = ''
            }
        },
        removeCarouselImageLink(link){
            if(link && link != null && link != '') {
                for( var i = 0; i < this.product.links.images.length; i++){ 
                    if (this.product.links.images[i] === link) { 
                        this.product.links.images.splice(i, 1); 
                    }
                }
            }
        },
        addCategory(){
            if(this.addCategoryId && this.addCategoryId != null && !this.product.categories.includes(this.addCategoryId)){
                this.product.categories.push(this.addCategoryId)
            }
        },
        removeCategory(){
            if(this.removeCategoryId && this.removeCategoryId != null && this.product.categories.includes(this.removeCategoryId)){
                for( var i = 0; i < this.product.categories.length; i++){ 
                    if (this.product.categories[i] === this.removeCategoryId) { 
                        this.product.categories.splice(i, 1); 
                    }
                }
            }
        },
        async createProduct(){
            auth(this)
            if(this.product){

                let product = {
                    'id': this.product.id,
                    'name': this.product.name,
                    'shortDetails': this.product.shortDetails,
                    'longDetails': this.product.longDetails,
                    'currency': this.product.currency,
                    'initialPrice': this.product.initialPrice,
                    'discountPercentage': this.product.discountPercentage,
                    'vatPercentage': this.product.vatPercentage,
                    'shipping': this.product.shipping,
                    'links': this.product.links,
                    'visibility': this.product.visibility,
                    'sku': this.product.sku,
                    'ean': this.product.ean,
                    'pn': this.product.pn,
                }

                let saga = {}

                const options = {
                    url: process.env.VUE_APP_BACKEND_URL + '/api/processes?id=create.product.saga.0',
                    method: 'POST',
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json;charset=UTF-8',
                        'Authorization': 'Bearer ' + localStorage.getItem('accessToken')
                    },
                    data: product
                    };
                await axios(options).then(response => saga = response.data)

                alert('The create product processe started successfully, check the processes section to check the result.')
            }
        }

    }
}
</script>

<style>

</style>