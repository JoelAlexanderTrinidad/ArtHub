const { createApp } = Vue

createApp( {
    data(){
        return {
            nickBusquedaIlustrados: "",
            nickTitulo: "",
            ciudadIlustrador: "",
            ilustraciones: [],
            ilustracionesCantidad: undefined,
            imagenIlustrador: "",
            redes: [],
            nombreIlustracion: "",
            auxCambiarDatos: false,
            nick: "",
            nombre: "",
            emailONick: "",
            apellido: "",
            email: "",
            contraseñaRegistro: "",
            contraseña: "",
            direccion: "",
            codigoPostal: "",
            ciudadRegistro: "",
            pais: "",
            descripcionExtra: "",
            loginAux: false,
            error: ""
        }
    },
    created(){
        this.paramLocation = location.search
        this.params = new URLSearchParams(this.paramLocation)
        this.nickBusquedaIlustrados = this.params.get("nick")
        this.informacion()
        if(sessionStorage.getItem('logIn') == 'true' ){
            this.loginAux = sessionStorage.getItem('logIn')
        }
    },
    methods: {
        informacion(){
            axios.get(`/api/ilustradores/${this.nickBusquedaIlustrados}`)
                .then(res=> {
                    this.nickTitulo = res.data.nick
                    this.imagenIlustrador = res.data.avatarURL
                    this.ilustracionesCantidad = res.data.ilustraciones.length
                    this.ilustraciones = res.data.ilustraciones
                    /*this.ciudadIlustrador = res.data.ciudad
                    this.redes = res.data.redes*/
                })
                .catch(error => console.log(error))
        },
        cerrarModal() {
            document.getElementById('inicioSesionRegistro').classList.toggle('ocultar-modal')
        },
        mostrarRegistro(){
            document.getElementById('inicioSesion').classList.toggle('ocultar-modal')
            document.getElementById('registro').classList.toggle('ocultar-modal')
        },
        registro(){
            axios.post('/api/usuario/registro',
                {
                    nombre: this.nombre + "-" + this.apellido,
                    email: this.email,
                    contraseña: this.contraseñaRegistro,
                    direccion: this.direccion,
                    zipCode: this.codigoPostal,
                    pais: this.pais,
                    ciudad: this.ciudad,
                    nick: this.nick,
                    descripcion:this.descripcionExtra
                })
            .then(res => {
                let mensajeTexto = res.data
                document.getElementById('registro').classList.toggle('ocultar-modal')
                let mensaje = document.getElementById('mensaje')
                mensaje.classList.toggle('ocultar-modal')
                mensaje.innerText = mensajeTexto
                
                this.password = this.contraseñaRegistro
                setTimeout(()=>{
                    axios.post('/api/login',`email=${this.nick}&password=${this.password}`,{headers:{'content-type':'application/x-www-form-urlencoded'}})
                    .then(res => { 
                        this.loginAux = true
                        mensaje.innerHTML = `${mensajeTexto} <p class="mt-2">Iniciaste sesion...</p>`

                        setTimeout(()=>{
                            document.getElementById('inicioSesionRegistro').classList.toggle('ocultar-modal')
                        },1500)
                    }) 
                    .catch(error => {this.error = error.response.data})
                }, 1500)
            })
            .catch(error => {this.error = error.response.data})
        },
        logIn(){
            axios.post('/api/login',`email=${this.emailONick}&password=${this.contraseña}`,{headers:{'content-type':'application/x-www-form-urlencoded'}})
            .then(res => {
                this.loginAux = true
                document.getElementById('inicioSesion').classList.toggle('ocultar-modal')
                let mensaje = document.getElementById('mensaje')
                mensaje.classList.toggle('ocultar-modal')
                mensaje.innerText = `Iniciaste sesion correctamente.`
                sessionStorage.setItem('logIn', true)
                this.loginAux = true
                setTimeout(()=>{
                    document.getElementById('inicioSesionRegistro').classList.toggle('ocultar-modal')
                },1500)
            }) 
            .catch(error => {
                console.log(this.emailONick)
                console.log(this.contraseña)
                if(this.emailONick == "" || this.contraseña == "" ){
                    this.error = "Completa todos los campos."
                }else if(error.code == 'ERR_BAD_REQUEST'){
                    this.error = "La contraseña no coincide con el usuario."
                }

            })
        },
        verProductos(id){
            window.location.href = `./editor-producto.html?id=${id}`
        },
        
        volver(){
            window.location.href = `./artistas.html`
        },
        logOut(){
            sessionStorage.setItem('logIn', false)
            this.loginAux = false
            axios.post('/api/logout')
        },
    }
}).mount("#app")
