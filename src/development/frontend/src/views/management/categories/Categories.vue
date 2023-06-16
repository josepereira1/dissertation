<template>
    <div class="row">
        <div>
            <NavBar />
        </div>
    </div>

    <div class="row">
        <div class="col-6 my-5">
            <ul v-if="parentCategory && parentCategory != null" style="list-style-type: none;">
                <div class="input-group mb-3 col-12">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="basic-addon1">#ID: {{ parentCategory.id }}</span>
                    </div>
                    <input type="text" class="form-control" v-bind:placeholder="parentCategory.name" aria-label="" aria-describedby="basic-addon1" disabled>
                    <button v-on:click="deleteCategory(parentCategory.id)" type="button" class="mx-1 btn btn-danger" disabled>-</button>
                </div>
                <div v-for="category in parentCategory.childCategories" v-bind:key="category.id">
                    <div class="input-group mb-3 col-12">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="basic-addon1">#ID: {{ category.id  }}</span>
                        </div>
                        <input type="text" class="form-control" v-bind:placeholder="category.name" aria-label="" aria-describedby="basic-addon1" disabled>
                        <button v-on:click="deleteCategory(category.id)" type="button" class="mx-1 btn btn-danger">-</button>
                    </div>
                    <div v-if="category.childCategories.length">
                        <ul class="list-group" style="list-style-type: none;">
                            <div v-for="childCategory in category.childCategories" v-bind:key="childCategory.id">
                                <li> 
                                    <div class="input-group mb-3 col-12">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text" id="basic-addon1">|--> #ID: {{ childCategory.id  }}</span>
                                        </div>
                                        <input type="text" class="form-control" v-bind:placeholder="childCategory.name" aria-label="" aria-describedby="basic-addon1" disabled>
                                        <button v-on:click="deleteCategory(childCategory.id)" type="button" class="mx-1 btn btn-danger">-</button>
                                    </div>
                                </li>
                            </div>
                        </ul>
                    </div>
                </div>
            </ul>
        </div>
        <div class="col-6 my-5">
            <h3 class="mb-4 d-flex align-content-start">MANAGEMENT CATEGORIES:</h3>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="basic-addon1">#ID:</span>
                </div>
                <input type="number" class="form-control" placeholder="" aria-label="" aria-describedby="basic-addon1" v-model="parentCategoryId">
            </div>

            <h4 class="d-flex align-content-start">Change name:</h4>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="basic-addon1">Name:</span>
                </div>
                <input type="text" class="form-control" placeholder="" aria-label="" aria-describedby="basic-addon1" v-model="categoryName">
            </div>

            <button v-on:click="updateCategoryName" type="button" class="my-2 btn btn-primary">UPDATE NAME</button>

            <h4 class="d-flex align-content-start">Add subcategory:</h4>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="basic-addon1">Subcategory name:</span>
                </div>
                <input type="text" class="form-control" placeholder="" aria-label="" aria-describedby="basic-addon1" v-model="childCategoryName">
                <button v-on:click="addChildCategoryToParentCategory" type="button" class="mx-1 btn btn-primary">+</button>
            </div>

            <button v-if="childCategoriesNames.length > 0" v-on:click="updateSubCategories" type="button" class="my-2 btn btn-primary">UPDATE SUBCATEGORIES</button>
            <button v-else-if="!childCategoriesNames || childCategoriesNames == null || (childCategoriesNames != null && childCategoriesNames.length == 0)" v-on:click="updateSubCategories" type="button" class="my-2 btn btn-primary" disabled>UPDATE SUBCATEGORIES</button>
            <br>
            <span>List of subcategories to be added</span>
            <div v-if="childCategoriesNames.length != 0">
                <div class="mb-3 d-flex align-content-start" v-for="name in childCategoriesNames" v-bind:key="name">
                    <ul class="list-group" style="width: 100%;list-style-type: none;">
                        <li class="list-group-item">{{  name  }}<button v-on:click="removeChildCategoryFromChildCategoriesNames(name)" type="button" class="mx-3 btn btn-danger">-</button></li>
                    </ul>
                </div>
            </div>
            <div v-else>
                <ul class="list-group" style="width: 100%;list-style-type: none;">
                    <li class="list-group-item"><span><b>Empty</b></span></li>
                </ul>
            </div>
        </div>
    </div>

    <div class="line2 my-3"></div>

    <h3 class="mb-3 d-flex align-content-start">PRODUCT CATEGORIES MANAGEMENT</h3>

    <h3 class="d-flex align-content-start">Search Product:</h3>
    <div class="input-group mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="basic-addon1">#ID:</span>
        </div>
        <input type="text" class="form-control" placeholder="" aria-label="" aria-describedby="basic-addon1" v-model="productId">
    </div>

    <button v-on:click="getProductCategories" type="button" class="mx-1 mb-3 btn btn-primary">SEARCH PRODUCT</button>

    <div v-if="productCurrentCategories && productCurrentCategories != null">
        <h3 class="mb-3 d-flex align-content-start">Current categories: </h3>
        <div v-if="productCurrentCategories && productCurrentCategories.length > 0">
            <div class="mb-3 d-flex align-content-start" v-for="item in productCurrentCategories" v-bind:key="item">
                <ul class="list-group" style="width: 100%;">
                    <li class="list-group-item">{{  item.id + ' - ' + item.name }} <button v-on:click="removeCategory(item)" type="button" class="mx-3 btn btn-danger">DEL</button></li>
                </ul>
            </div>
        </div>
        <div v-else>
            <ul class="list-group" style="width: 100%;list-style-type: none;">
                <li class="list-group-item"><span><b>Empty</b></span></li>
            </ul>
        </div>
    </div>

    <h3 class="mt-5">Add categories:</h3>
    <div class="row">
        <div class="col-11">
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="basic-addon1">Category Id:</span>
                </div>
                    <input type="number" class="form-control" placeholder="1" aria-label="" aria-describedby="basic-addon1" v-model="addtoAddCategoryId">
            </div>
        </div>
        <div class="col-1">
            <button v-on:click="addToAddCategories" type="button" class=" btn btn-primary">ADD</button>
        </div>
    </div>

    <h3 class="mb-3 d-flex align-content-start">List of categories ids to be added: </h3>
    <div v-if="addCategories && addCategories.length > 0">
        <div class="mb-3 d-flex align-content-start" v-for="item in addCategories" v-bind:key="item">
            <ul class="list-group" style="width: 100%;">
                <li class="list-group-item">{{  item  }} <button v-on:click="removeFromAddCategories(item)" type="button" class="mx-3 btn btn-danger">DEL</button></li>
            </ul>
        </div>
    </div>
    <div v-else>
        <ul class="list-group" style="width: 100%;list-style-type: none;">
            <li class="list-group-item"><span><b>Empty</b></span></li>
        </ul>
    </div>

    <button v-on:click="addCategoriesToProduct()" type="button" class="my-3 mx-3 btn btn-primary">ADD CATEGORIES</button>

    <div class="my-5"></div>

</template>

<script>

import auth from '@/js/auth.js'

import NavBar from '@/components/NavBar.vue'

export default {
    name: 'Categories',
    components: {NavBar},
    data(){
        return {
            accessToken: localStorage.getItem('accessToken'),
            role: localStorage.getItem('role'),
            userId: localStorage.getItem('userId'),
            authorizedRoles: ['manager'],
            nonAuthRedirect: 'ManagementLogin',
            parentCategory: null,
            categoryName: '',
            parentCategoryId: 1,
            childCategoryName: '',
            childCategoriesNames: [],
            productCurrentCategories: null,
            productId: '',
            addtoAddCategoryId: '',
            removeFromAddCategoryId: '',
            addToRemoveCategoryId: '',
            removeFromRemoveCategoryId: '',
            addCategories: [],
            removeCategories: [],
        }
    },
    mounted(){
        auth(this)
        fetch(process.env.VUE_APP_BACKEND_URL + '/api/categories/tree')
        .then(res => res.json())
        .then(data => this.parentCategory = data)
        .catch(err => console.log(err.message))
    },
    methods: {
        addChildCategoryToParentCategory (){
            auth(this)
            if(this.childCategoryName && this.childCategoryName != null && this.childCategoryName != '' && !this.childCategoriesNames.includes(this.childCategoryName)){
                this.childCategoriesNames.push(this.childCategoryName)
                this.childCategoryName = ''
            }
        },
        removeChildCategoryFromChildCategoriesNames(name){
            auth(this)
            if(name && name != null && name != '' && this.childCategoriesNames.includes(name)){
                for( var i = 0; i < this.childCategoriesNames.length; i++){ 
                    if (this.childCategoriesNames[i] === name) { 
                        this.childCategoriesNames.splice(i, 1); 
                    }
                }
            }
        },
        async updateCategoryName(){
            if(this.parentCategoryId && this.parentCategoryId != null && this.parentCategoryId > 1 && this.categoryName && this.categoryName != null && this.categoryName != ''){
                auth(this)

                let data = {
                    'name': this.categoryName
                }

                const options = {
                    url: process.env.VUE_APP_BACKEND_URL + '/api/categories/' + this.parentCategoryId + '/name',
                    method: 'PATCH',
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json;charset=UTF-8',
                        'Authorization': 'Bearer ' + localStorage.getItem('accessToken')
                    },
                    data: data
                };
                await axios(options).then(response => res = response.data)
                    .catch(function (error) {
                        if (error.response) {
                            // Request made and server responded
                            //console.log(error.response.data);
                            //console.log(error.response.status);
                            //console.log(error.response.headers);
                            
                            switch(error.response.status){
                                case 404:
                                    alert('Category not exists!')
                                    break;
                                case 409:
                                    alert('Category already exists!')
                                    break;
                                default:
                                    alert('Internal error. Contact Support')
                                    break;
                            }

                        } else if (error.request) {
                            // The request was made but no response was received
                            console.log(error.request);
                        } else {
                            // Something happened in setting up the request that triggered an Error
                            console.log('Error', error.message);
                        }

                    });
                this.updateTree()
            }
        },
        async updateSubCategories(){
            auth(this)
            if(this.childCategoriesNames && this.childCategoriesNames != null && this.childCategoriesNames.length > 0){
                let res
                const options = {
                    url: process.env.VUE_APP_BACKEND_URL + '/api/categories/' + this.parentCategoryId + '/subcategories',
                    method: 'PATCH',
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json;charset=UTF-8',
                        'Authorization': 'Bearer ' + localStorage.getItem('accessToken')
                    },
                    data: this.childCategoriesNames
                    };
                await axios(options).then(response => res = response.data)
                    .catch(function (error) {
                        if (error.response) {
                            // Request made and server responded
                            //console.log(error.response.data);
                            //console.log(error.response.status);
                            //console.log(error.response.headers);
                            
                            switch(error.response.status){
                                case 404:
                                    alert('Category not exists.')
                                    break;
                                case 409:
                                    alert('Subcategory already exists.')
                                    break;
                                case 400:
                                    alert('Category name is invalid.')
                                    break;
                                default:
                                    alert('Internal error. Contact Support.')
                                    break;
                            }

                        } else if (error.request) {
                            // The request was made but no response was received
                            console.log(error.request);
                        } else {
                            // Something happened in setting up the request that triggered an Error
                            console.log('Error', error.message);
                        }
                    });
                this.updateTree()
                this.childCategoriesNames = []
            }

        },
        async deleteCategory(categoryId){
            auth(this)
            if(categoryId && categoryId != null && categoryId > 0){
                let res
                const options = {
                    url: process.env.VUE_APP_BACKEND_URL + '/api/categories/' + categoryId ,
                    method: 'DELETE',
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json;charset=UTF-8',
                        'Authorization': 'Bearer ' + localStorage.getItem('accessToken')
                    },
                };
                await axios(options).then(response => res = response.data)
                    .catch(function (error) {
                        if (error.response) {
                            // Request made and server responded
                            //console.log(error.response.data);
                            //console.log(error.response.status);
                            //console.log(error.response.headers);
                            
                            switch(error.response.status){
                                case 404:
                                    alert('Category not exists.')
                                    break;
                                default:
                                    alert('Internal error. Contact Support.')
                                    break;
                            }

                        } else if (error.request) {
                            // The request was made but no response was received
                            console.log(error.request);
                        } else {
                            // Something happened in setting up the request that triggered an Error
                            console.log('Error', error.message);
                        }
                        });
                this.updateTree()
            }
        },
        async updateTree(){
            auth(this)
            const options = {
                url: process.env.VUE_APP_BACKEND_URL + '/api/categories/tree',
                method: 'GET',
            };
            await axios(options).then(response => this.parentCategory = response.data)
                .catch(function (error) {
                    if (error.response) {
                        // Request made and server responded
                        //console.log(error.response.data);
                        //console.log(error.response.status);
                        //console.log(error.response.headers);
                        
                        switch(error.response.status){
                            default:
                                alert('Internal error. Contact Support.')
                                break;
                        }

                    } else if (error.request) {
                        // The request was made but no response was received
                        console.log(error.request);
                    } else {
                        // Something happened in setting up the request that triggered an Error
                        console.log('Error', error.message);
                    }

                });
        },
        async getProductCategories(){
            auth(this)

            if(this.productId && this.productId != ''){

                const options = {
                    url: process.env.VUE_APP_BACKEND_URL + '/api/products/' + this.productId + '/categories',
                    method: 'GET',
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json;charset=UTF-8',
                        'Authorization': 'Bearer ' + localStorage.getItem('accessToken')
                    }
                };
                await axios(options)
                    .then(response => this.productCurrentCategories = response.data)
                    .catch(err => console.log(err))
            }
        },
        addToAddCategories(){
            if(this.addtoAddCategoryId && this.addtoAddCategoryId != null && !this.addCategories.includes(this.addtoAddCategoryId)){
                this.addCategories.push(this.addtoAddCategoryId)
            }
        },
        removeFromAddCategories(categoryId){
            if(categoryId && categoryId != null && this.addCategories.includes(categoryId)){
                for( var i = 0; i < this.addCategories.length; i++){ 
                    if (this.addCategories[i] === categoryId) { 
                        this.addCategories.splice(i, 1); 
                    }
                }
            }
        },
        addToRemoveCategories(){
            if(this.addToRemoveCategoryId && this.addToRemoveCategoryId != null && !this.removeCategories.includes(this.addToRemoveCategoryId)){
                this.removeCategories.push(this.addToRemoveCategoryId)
            }
        },
        removeFromRemoveCategories(){
            if(this.removeFromRemoveCategoryId && this.removeFromRemoveCategoryId != null && this.removeCategories.includes(this.removeFromRemoveCategoryId)){
                for( var i = 0; i < this.removeCategories.length; i++){ 
                    if (this.removeCategories[i] === this.removeFromRemoveCategoryId) { 
                        this.removeCategories.splice(i, 1); 
                    }
                }
            }
        },
        async removeCategory(category){
            auth(this)

            if(category && category != null){

                let categoriesIds = []

                categoriesIds.push(category.id)

                const options = {
                    url: process.env.VUE_APP_BACKEND_URL + '/api/products/' + this.productId + '/categories',
                    method: 'DELETE',
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json;charset=UTF-8',
                        'Authorization': 'Bearer ' + localStorage.getItem('accessToken')
                    },
                    data: categoriesIds
                };
                await axios(options)
                    .then(response => this.productCurrentCategories = response.data)
                    .catch(err => console.log(err))
            }
        },
        async addCategoriesToProduct(){
            auth(this)

            if(this.addCategories  && this.addCategories.length > 0 && this.productId && this.productId != null && this.productId != ''){

                this.product = {}

                const options = {
                    url: process.env.VUE_APP_BACKEND_URL + '/api/products/' + this.productId + '/categories',
                    method: 'PATCH',
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json;charset=UTF-8',
                        'Authorization': 'Bearer ' + localStorage.getItem('accessToken')
                    },
                    data: this.addCategories
                };
                await axios(options)
                    .then(response => this.productCurrentCategories = response.data)
                    .catch(err => console.log(err))
                
                this.addCategories = []
            }
        }
    }
}
</script>

<style>

</style>