<template>
    <div v-if="parentCategory">
        <ul v-if="role && role == 'manager'" class="list-group">
            <router-link v-bind:to="{ name: 'ManagementProducts', params: { id: parentCategory.id}, query: {page: 1}}" v-bind:key="$route.fullPath">
                <!--<li class="list-group-item d-flex justify-content-start">{{ parentCategory.name }}</li>-->
                <li class="list-group-item d-flex justify-content-start">HOME</li>
            </router-link>
            <div v-for="category in parentCategory.childCategories" v-bind:key="category.id">
                <router-link v-bind:to="{ name: 'ManagementProducts', params: { id: category.id}, query: {page: 1}}" v-bind:key="$route.fullPath">
                    <li class="list-group-item d-flex justify-content-start" style="background: #ebebeb">{{ category.name }}</li>
                </router-link>
                <div v-if="category.childCategories.length">
                    <ul class="list-group">
                        <div v-for="childCategory in category.childCategories" v-bind:key="childCategory.id">
                            <router-link v-bind:to="{ name: 'ManagementProducts', params: { id: childCategory.id}, query: {page: 1}}" v-bind:key="$route.fullPath">
                                <li class="list-group-item d-flex justify-content-start"> >>> {{ childCategory.name }}</li>
                            </router-link>
                        </div>
                    </ul>
                </div>
            </div>
        </ul>
        <ul v-else class="list-group">
            <router-link v-bind:to="{ name: 'Products', params: { id: parentCategory.id}, query: {page: 1}}" v-bind:key="$route.fullPath">
                <!--<li class="list-group-item d-flex justify-content-start">{{ parentCategory.name }}</li>-->
                <li class="list-group-item d-flex justify-content-start">HOME</li>
            </router-link>
            <div v-for="category in parentCategory.childCategories" v-bind:key="category.id">
                <router-link v-bind:to="{ name: 'Products', params: { id: category.id}, query: {page: 1}}" v-bind:key="$route.fullPath">
                    <li class="list-group-item d-flex justify-content-start" style="background: #ebebeb">{{ category.name }}</li>
                </router-link>
                <div v-if="category.childCategories.length">
                    <ul class="list-group">
                        <div v-for="childCategory in category.childCategories" v-bind:key="childCategory.id">
                            <router-link v-bind:to="{ name: 'Products', params: { id: childCategory.id}, query: {page: 1}}" v-bind:key="$route.fullPath">
                                <li class="list-group-item d-flex justify-content-start"> >>> {{ childCategory.name }}</li>
                            </router-link>
                        </div>
                    </ul>
                </div>
            </div>
        </ul>
    </div>
</template>

<script>
export default {
    name: 'Sidebar',
    components: {},
    data(){
        return {
            role: localStorage.getItem('role'),
            parentCategory: null
        }
    },
    mounted(){
        fetch(process.env.VUE_APP_BACKEND_URL + '/api/categories/tree')
        .then(res => res.json())
        .then(data => this.parentCategory = data)
        .catch(err => console.log(err.message))
    }
}
</script>

<style>

</style>