<template>
    <NavBar />
    <button v-on:click="orderSagasByDateAscending" type="button" class="mx-1 mb-3 btn btn-primary">START DATE ASCENDING</button>
    <button v-on:click="orderSagasByDateDescending" type="button" class="mx-1 mb-3 btn btn-primary">START DATE DESCENDING</button>
    <table class="table table-hover">
        <thead>
            <tr>
                <th>#id</th>
                <th>Action</th>
                <th>Start</th>
                <th>End</th>
                <th>Status</th>
                <th>Message</th>
                <th>#Result [entity ID]</th>
            </tr>
        </thead>
        <tbody v-if="sagas && sagas != null && sagas.length >= 1">
            <tr v-for="saga in sagas.slice(0, 15)" v-bind:key="saga">
                <!--<td><router-link v-bind:to="{ name: 'OrderDetails', params: { id: saga.id  }}">
                    {{  saga.id  }}
                </router-link></td>-->
                <td class="align-middle">{{  saga.id  }}</td>
                <td class="align-middle" v-if="saga.name == 'create.product.saga.0'">CREATE PRODUCT</td>
                <td class="align-middle" v-else-if="saga.name == 'update.product.saga.0'">UPDATE PRODUCT</td>
                <td class="align-middle" v-else-if="saga.name == 'create.order.saga.0'">CREATE ORDER</td>
                <td class="align-middle" v-if="saga.start != null">{{  saga.start.split('T')[0] + ' | ' + saga.start.split('T')[1].split('.')[0]  }}</td>
                <td v-else></td>
                <td class="align-middle" v-if="saga.end != null">{{  saga.end.split('T')[0] + ' | ' + saga.end.split('T')[1].split('.')[0]  }}</td>
                <td v-else></td>
                <td class="align-middle" v-if="saga.status == 'PENDING'" style="color: orange">Pending</td>
                <td class="align-middle" v-else-if="saga.status == 'ERROR'" style="color: red">Error</td>
                <td class="align-middle" v-else-if="saga.status == 'SUCCESS'" style="color: green">Success</td>

                <td class="align-middle" v-if="saga.status == 'PENDING'">-- No message --</td>
                <td class="align-middle" v-else-if="saga.status == 'ERROR' && saga.code != 500">{{  saga.message  }}</td>
                <td class="align-middle" v-else-if="saga.status == 'ERROR' && saga.code == 500">Internal server error (contact support)</td>
                <td class="align-middle" v-else-if="saga.status == 'SUCCESS'">{{  saga.message  }}</td>

                <td class="align-middle" v-if="saga.name == 'create.product.saga.0' && role == 'consumer' && saga.output != null && saga.output.id != null">
                    <router-link v-bind:to="{ name: 'ProductDetails', params: { id: saga.output.id  }}">
                        {{  saga.output.id  }}
                    </router-link>
                </td>
                <td class="align-middle" v-if="saga.name == 'create.product.saga.0' && role == 'manager' && saga.output != null && saga.output.id != null">
                    <router-link v-bind:to="{ name: 'ManagementUpdateProduct', params: { id: saga.output.id  }}">
                        {{  saga.output.id  }}
                    </router-link>
                </td>
                <td class="align-middle" v-else-if="saga.name == 'update.product.saga.0' && role == 'consumer' && saga.output != null && saga.output.id != null">
                    <router-link v-bind:to="{ name: 'ProductDetails', params: { id: saga.output.id  }}">
                        {{  saga.output.id  }}
                    </router-link>
                </td>
                <td class="align-middle" v-else-if="saga.name == 'update.product.saga.0' && role == 'manager' && saga.output != null && saga.output.id != null">
                    <router-link v-bind:to="{ name: 'ManagementUpdateProduct', params: { id: saga.output.id  }}">
                        {{  saga.output.id  }}
                    </router-link>
                </td>
                <td  class="align-middle" v-else-if="saga.name == 'create.order.saga.0' && saga.output != null && saga.output.id != null">
                    <router-link v-bind:to="{ name: 'OrderDetails', params: { id: saga.output.id  }}">
                        {{  saga.output.id  }}
                    </router-link>
                </td>
                <td class="align-middle" v-else>
                    --
                </td>
            </tr>
        </tbody>
    </table>
    <span v-if="sagas == null || (sagas != null && sagas.length == 0)">There are no processes</span>
</template>

<script>

import auth from '@/js/auth.js'

import NavBar from '@/components/NavBar.vue'

export default {
    name: 'Processes',
    components: {NavBar},
    data() {
        return {
            accessToken: localStorage.getItem('accessToken'),
            role: localStorage.getItem('role'),
            userId: localStorage.getItem('userId'),
            authorizedRoles: ['manager'],
            nonAuthRedirect: 'ManagementLogin',
            shoppingCartSize: null,
            sagas: null
        }
    },
    async mounted(){
        auth(this)
        const options = {
            url: process.env.VUE_APP_BACKEND_URL + '/api/sagas',
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json;charset=UTF-8',
                'Authorization': 'Bearer ' + localStorage.getItem('accessToken')
            }
        };
        await axios(options)
                .then(response => this.sagas = response.data)
                .catch(err => console.log(err))

        if(this.sagas != null){
            this.sagas.sort(function (saga1, saga2){
                if(saga1.start > saga2.start) return -1;
                else if(saga2.start > saga1.start) return 1;
                else if(saga1.start == saga2.start) return 0;
            })
        }
    },
    methods:{
        orderSagasByDateAscending(){
            this.sagas.sort(function (saga1, saga2){
                if(saga1.start > saga2.start) return 1;
                else if(saga2.start > saga1.start) return -1;
                else if(saga1.start == saga2.start) return 0;
            })
        },
        orderSagasByDateDescending(){
            this.sagas.sort(function (saga1, saga2){
                if(saga1.start > saga2.start) return -1;
                else if(saga2.start > saga1.start) return 1;
                else if(saga1.start == saga2.start) return 0;
            })
        }
    }
}
</script>

<style>

</style>