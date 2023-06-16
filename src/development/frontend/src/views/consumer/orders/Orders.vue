<template>
    <div v-if="shoppingCartSize">
        <NavBar v-bind:size="shoppingCartSize"/>
    </div>
    <div v-else>
        <NavBar />
    </div>
    <!--<h2 class="d-flex align-content-start">ORDERS</h2>-->
    <button v-on:click="sortOrdersByDateAscending" type="button" class="mx-1 mb-3 btn btn-primary">DATE ASCENDING</button>
    <button v-on:click="sortOrdersByDateDescending" type="button" class="mx-1 mb-3 btn btn-primary">DATE DESCENDING</button>
    <table class="table table-hover">
        <thead>
            <tr>
                <th>#id</th>
                <th>Date</th>
                <th>Status</th>
                <th>Total</th>
            </tr>
        </thead>
        <tbody v-if="orders && orders != null && orders.length >= 1">
            <tr v-for="order in orders.slice(0, 15)" v-bind:key="order">
                <td><router-link v-bind:to="{ name: 'OrderDetails', params: { id: order.id  }}">
                    {{  order.id  }}
                </router-link></td>
                <td v-if="order.initialDate != null && order.initialDate != ''">{{  order.initialDate.split('T')[0] + ' | ' + order.initialDate.split('T')[1].split('.')[0]  }}</td>
                <td v-else>--</td>
                <td v-if="order.status == 'PENDING_PAYMENT'" style="color: orange">Pending payment</td>
                <td v-else-if="order.status == 'FAILED'" style="color: red">Failed</td>
                <td v-else-if="order.status == 'PROCESSING'" style="color: orange">Processing</td>
                <td v-else-if="order.status == 'COMPLETED'" style="color: green">Completed</td>
                <td v-else-if="order.status == 'CANCELED'" style="color: red">Canceled</td>
                <td>{{  order.total.toFixed(2)  }} {{  order.currency  }}</td>
            </tr>
        </tbody>
    </table>
</template>

<script>

import NavBar from '@/components/NavBar.vue'

export default {
    name: 'Orders',
    components: {NavBar},
    data() {
        return {
            shoppingCartSize: null,
            orders: null
        }
    },
    async mounted(){
        if(localStorage.getItem('userId')){
            const options = {
                url: process.env.VUE_APP_BACKEND_URL + '/api/orders?clientId=' + localStorage.getItem('userId'),
                method: 'GET',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json;charset=UTF-8',
                    'Authorization': 'Bearer ' + localStorage.getItem('accessToken')
                }
            };
            await axios(options)
                .then(response => this.orders = response.data)
                .catch(err => console.log(err))

            this.orders.sort(function (order1, order2){
                if(order1.initialDate > order2.initialDate) return -1;
                else if(order2.initialDate > order1.initialDate) return 1;
                else if(order1.initialDate == order2.initialDate) return 0;
            })
        }
    },
    methods:{
        sortOrdersByDateAscending(){
            this.orders.sort(function (order1, order2){
                if(order1.initialDate > order2.initialDate) return 1;
                else if(order2.initialDate > order1.initialDate) return -1;
                else if(order1.initialDate == order2.initialDate) return 0;
            })
        },
        sortOrdersByDateDescending(){
            this.orders.sort(function (order1, order2){
                if(order1.initialDate > order2.initialDate) return -1;
                else if(order2.initialDate > order1.initialDate) return 1;
                else if(order1.initialDate == order2.initialDate) return 0;
            })
        }
    }
}
</script>

<style>

</style>