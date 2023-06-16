<template>
    <NavBar />
    <!--<h2 v-if="order && order != null" class="d-flex align-content-start">ORDER DETAILS: <span style="color: grey">ID#{{  order.id  }}</span></h2>
    <h2 v-else class="d-flex align-content-start">ORDER DETAILS</h2>-->
    <h2 v-if="order && order != null" class="d-flex align-content-start"><span style="color: grey">ID#{{  order.id  }}</span></h2>
    <div class="row">
        <div class="col-6">
            <table class="table table-hover">
                <thead>
                    <tr>
                    <th scope="col"></th>
                    <th scope="col"></th>
                    </tr>
                </thead>
                <tbody v-if="order && order != null">
                    <tr>
                        <th scope="row">Tin:</th>
                        <td>{{  order.tin }}</td>
                    </tr>
                    <tr>
                        <th scope="row">Initial date:</th>
                        <td>{{  order.initialDate.split('T')[0] + ' | ' + order.initialDate.split('T')[1].split('.')[0]  }}</td>
                    </tr>
                    <tr>
                        <th scope="row">End date:</th>
                        <td v-if="order.endDate && order.endDate != null">{{  order.endDate.split('T')[0] + ' | ' + order.endDate.split('T')[1].split('.')[0]  }}</td>
                        <td v-else>--</td>
                    </tr>
                    <tr>
                        <th scope="row">Tracking number:</th>
                        <td v-if="order.trackingNumber && order.trackingNumber != null">{{  order.trackingNumber }}</td>
                        <td v-else>--</td>
                    </tr>
                    <tr>
                        <th scope="row">Status:</th>
                        <td v-if="order.status == 'PENDING_PAYMENT'" style="color: orange">Pending payment</td>
                        <td v-else-if="order.status == 'FAILED'" style="color: red">Failed</td>
                        <td v-else-if="order.status == 'PROCESSING'" style="color: orange">Processing</td>
                        <td v-else-if="order.status == 'COMPLETED'" style="color: green">Completed</td>
                        <td v-else-if="order.status == 'CANCELED'" style="color: red">Canceled</td>
                    </tr>
                    <tr>
                        <th scope="row">Message:</th>
                        <td v-if="order.statusMessage != null && order.statusMessage != ''">{{  order.statusMessage  }}</td>
                        <td v-else>--</td>
                    </tr>
                </tbody>
            </table>
        </div>

        <div class="col-6">
            <table class="table table-hover">
                <thead>
                    <tr>
                    <th scope="col"></th>
                    <th scope="col"></th>
                    </tr>
                </thead>
                <tbody v-if="order && order != null">
                    <tr>
                        <th scope="row">Subtotal:</th>
                        <td>{{  order.subtotal.toFixed(2) }} {{  order.currency  }}</td>
                    </tr>
                    <tr>
                        <th scope="row">Discount:</th>
                        <td>{{  order.discount.toFixed(2) }} {{  order.currency  }}</td>
                    </tr>
                    <tr>
                        <th scope="row">Shipping:</th>
                        <td>{{  order.shipping.toFixed(2) }} {{  order.currency  }}</td>
                    </tr>
                    <tr>
                        <th scope="row">Vat:</th>
                        <td>{{  order.vat.toFixed(2) }} {{  order.currency  }}</td>
                    </tr>
                    <tr>
                        <th scope="row">Total:</th>
                        <td>{{  order.total.toFixed(2) }} {{  order.currency  }}</td>
                    </tr>
                </tbody>
            </table>
        </div>
        
    </div>
    <div v-if="order && order != null" class="row">
        <h3 class="d-flex align-content-start">Change order status</h3>
        <div class="col-4">
            <input v-if="order.status == 'PENDING_PAYMENT'" type="text" class="form-control" placeholder="Pending payment" aria-label="" aria-describedby="basic-addon1" v-model="orderStatus">
            <input v-else-if="order.status == 'FAILED'" type="text" class="form-control" placeholder="Failed" aria-label="" aria-describedby="basic-addon1" v-model="orderStatus">
            <input v-else-if="order.status == 'PROCESSING'" type="text" class="form-control" placeholder="Processing" aria-label="" aria-describedby="basic-addon1" v-model="orderStatus">
            <input v-else-if="order.status == 'COMPLETED'" type="text" class="form-control" placeholder="Completed" aria-label="" aria-describedby="basic-addon1" v-model="orderStatus">
            <input v-else-if="order.status == 'CANCELED'" type="text" class="form-control" placeholder="Canceled" aria-label="" aria-describedby="basic-addon1" v-model="orderStatus">
        </div>
        <div class="col-2">
            <button v-on:click="changeOrderStatus" type="button" class=" btn btn-primary">UPDATE STATUS</button>
        </div>
    </div>

    <h2 class="mt-5">Products</h2>
    <table class="table mb-5">
        <thead>
            <tr>
                <th>#id</th>
                <th>Name</th>
                <th>Quantity</th>
                <th>SKU</th>
                <th>EAN</th>
                <th>Discount (%)</th>
                <th>Vat (%)</th>
                <th>Price</th>
            </tr>
        </thead>
        <tbody v-if="order && order != null && order.products && order.products != null && order.products.length >= 1">
            <tr v-for="product in order.products" v-bind:key="product">
                <td><router-link v-bind:to="{ name: 'ProductDetails', params: { id: product.id}}">{{  product.id  }}</router-link></td>
                <td><router-link v-bind:to="{ name: 'ProductDetails', params: { id: product.id}}">{{  product.name  }}</router-link></td>
                <td>{{  product.quantity  }}</td>
                <td>{{  product.sku  }}</td>
                <td>{{  product.ean  }}</td>
                <td>{{  product.discountPercentage.toFixed(2)  }}</td>
                <td>{{  product.vatPercentage.toFixed(2)  }}</td>
                <td>{{  product.finalPrice.toFixed(2)  }} {{  product.currency  }}</td>
            </tr>
            <tr>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td><b>Total:</b></td>
                <td>{{  order.total.toFixed(2) }} {{  order.currency  }}</td>
            </tr>
        </tbody>
    </table>
</template>

<script>

import axios from "axios"
import auth from '@/js/auth.js'

import NavBar from '@/components/NavBar.vue'

export default {
    name: 'ManagementOrderDetails',
    props: ['id'],
    components: {NavBar},
    data() {
        return {
            accessToken: localStorage.getItem('accessToken'),
            role: localStorage.getItem('role'),
            userId: localStorage.getItem('userId'),
            authorizedRoles: ['manager'],
            nonAuthRedirect: 'ManagementLogin',
            order: null,
            orderStatus: ''
        }
    },
    async mounted(){
        auth(this)
        if(localStorage.getItem('userId')){
            const options = {
                url: process.env.VUE_APP_BACKEND_URL + '/api/orders/' + this.id,
                method: 'GET',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json;charset=UTF-8',
                    'Authorization': 'Bearer ' + localStorage.getItem('accessToken')
                }
            };
            await axios(options)
                    .then(response => this.order = response.data)
                    .catch(err => console.log(err))
        }
    },
    methods: {
        async changeOrderStatus(){
            auth(this)

            switch(this.orderStatus.toLowerCase()){
                case 'pending payment':
                    this.orderStatus = 'PENDING_PAYMENT'
                    break;
                case 'failed':
                    this.orderStatus = 'FAILED'
                    break;
                case 'processing':
                    this.orderStatus = 'PROCESSING'
                    break;
                case 'completed':
                    this.orderStatus = 'COMPLETED'
                    break;
                case 'canceled':
                    this.orderStatus = 'CANCELED'
                    break;
                default: 
                    alert('Order status "' + this.orderStatus + '" not match any option. Choose one of them ["Pending payment", "Failed", "Processing", "Completed", "Canceled"]')
                    this.orderStatus = ''
                    return;
            }


            const options = {
                url: process.env.VUE_APP_BACKEND_URL + '/api/orders/' + this.id + '?status=' + this.orderStatus,
                method: 'PATCH',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json;charset=UTF-8',
                    'Authorization': 'Bearer ' + localStorage.getItem('accessToken')
                }
            };
            await axios(options)
                    .then(response => this.order = response.data)
                    .catch(err => console.log(err))

            this.orderStatus = ''
        }
    }
}
</script>

<style>

</style>