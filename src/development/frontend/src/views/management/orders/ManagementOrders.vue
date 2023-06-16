<template>
    <NavBar />
    <button v-on:click="getOrdersByStatus('ALL')" type="button" class="mx-1 mb-3 btn btn-secondary">ALL</button>
    <button v-on:click="getOrdersByStatus('PENDING_PAYMENT')" type="button" class="mx-1 mb-3 btn btn-secondary">PENDING PAYMENT</button>
    <button v-on:click="getOrdersByStatus('FAILED')" type="button" class="mx-1 mb-3 btn btn-secondary">FAILED</button>
    <button v-on:click="getOrdersByStatus('PROCESSING')" type="button" class="mx-1 mb-3 btn btn-secondary">PROCESSING</button>
    <button v-on:click="getOrdersByStatus('COMPLETED')" type="button" class="mx-1 mb-3 btn btn-secondary">COMPLETED</button>
    <button v-on:click="getOrdersByStatus('CANCELED')" type="button" class="mx-1 mb-3 btn btn-secondary">CANCELED</button>
    <br>
    <button v-on:click="sortOrdersByDateAscending" type="button" class="mx-1 mb-3 btn btn-primary">DATE ASCENDING</button>
    <button v-on:click="sortOrdersByDateDescending" type="button" class="mx-1 mb-3 btn btn-primary">DATE DESCENDING</button>
    <table class="table table-hover">
        <thead>
            <tr>
                <th>#id</th>
                <th>#Consumer Id</th>
                <th>Date</th>
                <th>Status</th>
                <th>Total</th>
            </tr>
        </thead>
        <tbody v-if="orders && orders != null && orders.length >= 1">
            <tr v-for="order in orders.slice(0, 15)" v-bind:key="order">
                <td><router-link v-bind:to="{ name: 'ManagementOrderDetails', params: { id: order.id  }}">
                    {{  order.id  }}
                </router-link></td>
                <td v-if="order.clientId != null && order.clientId != ''">{{  order.clientId.split('@')[1]  }}</td>
                <td v-else>--</td>
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
    <span v-if="orders == null || (orders != null && orders.length == 0)">There are no orders to show</span>
</template>

<script>

import auth from '@/js/auth.js'

import NavBar from '@/components/NavBar.vue'

export default {
    name: 'ManagementOrders',
    components: {NavBar},
    data() {
        return {
            accessToken: localStorage.getItem('accessToken'),
            role: localStorage.getItem('role'),
            userId: localStorage.getItem('userId'),
            authorizedRoles: ['manager'],
            nonAuthRedirect: 'ManagementLogin',
            orders: null
        }
    },
    async mounted(){
        auth(this)
        const options = {
            url: process.env.VUE_APP_BACKEND_URL + '/api/orders?status=ALL',
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
    },
    methods: {
        async getOrdersByStatus(status){
            auth(this)
            const options = {
                url: process.env.VUE_APP_BACKEND_URL + '/api/orders?status=' + status,
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
        },
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