<template>
    <div class="align-items-center">
        <img class="mt-3" v-bind:src="links.images[position]" alt="..." width="300" height="300">
        <br><br>
        <button v-on:click="back" class="mt-2"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-left" viewBox="0 0 16 16"><path fill-rule="evenodd" d="M15 8a.5.5 0 0 0-.5-.5H2.707l3.147-3.146a.5.5 0 1 0-.708-.708l-4 4a.5.5 0 0 0 0 .708l4 4a.5.5 0 0 0 .708-.708L2.707 8.5H14.5A.5.5 0 0 0 15 8z"/></svg></button>
        <button v-on:click="next" class="mt-2"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-right" viewBox="0 0 16 16"><path fill-rule="evenodd" d="M1 8a.5.5 0 0 1 .5-.5h11.793l-3.147-3.146a.5.5 0 0 1 .708-.708l4 4a.5.5 0 0 1 0 .708l-4 4a.5.5 0 0 1-.708-.708L13.293 8.5H1.5A.5.5 0 0 1 1 8z"/></svg></button>
    </div>
</template>

<script>

export default {
    props: ['links'],
    data(){
        return {
            position: 0
        }
    },
    methods: {
        next() {
            if(this.position + 1 >= 0 && this.position + 1 < this.links.images.length)this.position++
        },
        back() {
            if(this.position - 1 >= 0 && this.position - 1 < this.links.images.length) this.position--
        }
    }
}
  
</script>