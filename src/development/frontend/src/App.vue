<template>
    <div id="app">
        <div class="container">
            <router-view/>
        </div>
        <!--<footer class="text-center py-3 mt-5" style="background: grey; color: white"> Copyright ecommerce</footer>-->
    </div>
</template>

<script>
export default {
    name: 'App',
    data(){
        return {
            accessToken: localStorage.getItem('accessToken'),
            role: localStorage.getItem('role'),
            userId: localStorage.getItem('userId'),
            authorizedRoles: ['manager'],
            nonAuthRedirect: 'ManagementLogin'
        }
    },
    async mounted(){

        if(localStorage.getItem('accessToken') && localStorage.getItem('accessToken') != null && localStorage.getItem('accessToken') != '' && localStorage.getItem('role') && localStorage.getItem('role') != null && localStorage.getItem('role') != ''){

            const options = {
                url: process.env.VUE_APP_BACKEND_URL + '/api/' + localStorage.getItem('role') + '/bearer/auth',
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json;charset=UTF-8',
                    'Authorization': 'Bearer ' + localStorage.getItem('accessToken')
                }
            };
            await axios(options)
                    .then()
                    .catch(function (error) {
                        if (error.response) {
                            // Request made and server responded
                            //console.log(error.response.data);
                            //console.log(error.response.status);
                            //console.log(error.response.headers);

                            switch(error.response.status){
                                case 401:
                                    localStorage.setItem('accessToken' , '')
                                    localStorage.setItem('userId' , '')
                                    localStorage.setItem('role' , '')
                                    return;
                                default:
                                    localStorage.setItem('accessToken' , '')
                                    localStorage.setItem('userId' , '')
                                    localStorage.setItem('role' , '')
                                    return;
                            }

                        } else if (error.request) {
                            // The request was made but no response was received
                            localStorage.setItem('accessToken' , '')
                            localStorage.setItem('userId' , '')
                            localStorage.setItem('role' , '')
                            alert('Internal error. Contact Support.')
                            console.log(error.request);
                            return
                        } else {
                            // Something happened in setting up the request that triggered an Error
                            localStorage.setItem('accessToken' , '')
                            localStorage.setItem('userId' , '')
                            localStorage.setItem('role' , '')
                            alert('Internal error. Contact Support.')
                            console.log('Error', error.message);
                            return
                        }
                    });
        }
    }
}
</script>


<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}*/

#nav {
  padding: 30px;
  background: lightgrey;
}

#nav a {
  font-weight: bold;
  color: #000000;
  width: 10px;
  text-decoration: none;
}

#nav a.router-link-exact-active {
  /*background-color: rgb(199, 199, 199);
  padding: 10px;
  border-radius: 10px;*/
  text-decoration: underline;
  background-color: greenyellow;
}

.navigation-button {
  background: rgb(221, 221, 221);
  padding: 10px;
  color: rgb(0, 0, 0);
  border: 3px;
  border-radius: 5px;
  text-decoration: none;
}

 .line1{
  width: 100%;
  height: 47px;
  border-bottom: 1px solid grey;
}

.line2{
  width: 100%;
  height: 47px;
  border-bottom: 2px solid grey;
}

.line3{
  width: 100%;
  height: 47px;
  border-bottom: 3px solid grey;
}

.line4{
  width: 100%;
  height: 47px;
  border-bottom: 4px solid grey;
}

.line5{
  width: 100%;
  height: 47px;
  border-bottom: 5px solid grey;
}

a {
    text-decoration: none;
}
</style>
