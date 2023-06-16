<template>
    <div v-if="shoppingCartSize">
        <NavBar v-bind:size="shoppingCartSize"/>
    </div>
    <div v-else>
        <NavBar />
    </div>
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

    <h2>Products</h2>
    <table class="table">
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

import NavBar from '@/components/NavBar.vue'

export default {
    name: 'OrderDetails',
    props: ['id'],
    components: {NavBar},
    data() {
        return {
            shoppingCartSize: null,
            order: null
        }
    },
    async mounted(){
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
        getStatusMessage(){
            if(this.order.statusCode != null 
            && this.order.statusCode != 500 
            && this.order.statusMessage != null 
            && this.order.statusMessage != '')
                alert(this.order.statusMessage);
            else
                alert('Something went wrong, please contact support.');
        }
    }
}
</script>

<style>

</style>