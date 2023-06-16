function auth(vue){
    if(!vue.role || vue.role == null || vue.role == '' || !vue.accessToken || vue.accessToken == null || vue.accessToken == ''){
        let isAtuhorized = false
        for (var i = 0; i < vue.authorizedRoles.length; i++){
            if(vue.role == vue.authorizedRoles[i]) {
                isAtuhorized = true
                break
            }
        }
        
        if(!isAtuhorized) vue.$router.push({ name: vue.nonAuthRedirect})
    }
}

export default auth