const { createApp } = Vue;

createApp({
    data() {
        return {

        }
    },
    created(){
        
    },
    methods:{
        openMenu() {
            let container=document.querySelector(".menu-container");
            if (container.style.width=="11rem"){
                container.style.width = "4.2rem";
            }else{
                container.style.width = "11rem";
            }
        },

    },

}).mount('#app')