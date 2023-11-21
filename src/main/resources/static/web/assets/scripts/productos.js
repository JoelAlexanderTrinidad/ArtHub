const { createApp } = Vue

createApp( {
    data(){
        return {
            nombre: "",
            nickTitulo: "",
            nick: "",
            emailONick: "",
            apellido: "",
            email: "",
            contraseñaRegistro: "",
            contraseña: "",
            direccion: "",
            codigoPostal: "",
            ciudad: "",
            pais: "",
            descripcionExtra: "",
            imagenUsuario: "",
            auxCambiarDatos: false,
            error: "",
            loginAux: false,
            password: "",
            nombreIlustrador: "",
            tipoDeProducto: false, 
            productos: ["Remera", "Taza", "Cuaderno", "Llavero", "Poster"],
            productosFiltrados: [],
            ilustradores: [],
            ilustradoresFiltrados: [],
            ilustracionesAleatorias: [],
            productosAleatorios: [],
        }
    },
    created(){
        if(sessionStorage.getItem('logIn') == 'true' ){
            this.loginAux = sessionStorage.getItem('logIn')
        }
        this.informacion()

        this.productosIlustAleatorios()
    },
    methods: {
        cerrarModal() {
            document.getElementById('inicioSesionRegistro').classList.toggle('ocultar-modal')
        },
        mostrarRegistro(){
            document.getElementById('inicioSesion').classList.toggle('ocultar-modal')
            document.getElementById('registro').classList.toggle('ocultar-modal')
            this.error = ""
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
                        sessionStorage.setItem('logIn', true)
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
        logOut(){
            sessionStorage.setItem('logIn', false)
            this.loginAux = false
            axios.post('/api/logout')
        },
        informacion(){
            axios.get(`/api/ilustradores`)
            .then(res=>{
                this.ilustradores = res.data
                this.ilustradoresFiltrados = [...this.ilustradores]
            })

            
        },
        productosIlustAleatorios(){
            axios.get(`/api/ilustraciones`)
            .then(res=>{

                console.log(res.data)
               
                const ilustraciones = res.data.map(({nombre, imgURL}) => ({nombre, imgURL})) // Desestructuro las propiedades para solo traer esas
                const ilustracionesMezcladas = ilustraciones.sort(() => Math.random() - 0.5)
                this.ilustracionesAleatorias = ilustracionesMezcladas.slice(0, 18)
                this.ilustracionesAleatorias.push(...ilustracionesMezcladas.slice(0,4))

                console.log(">>>>>>" + JSON.stringify(this.ilustracionesAleatorias)) // lo parseo para poder visualizar en la consola del navegador

                // console.log(this.ilustracionesAleatorias)
            })

            axios.get(`/api/productos`)
            .then(res=>{
                const productos = res.data.map(producto => producto.nombre)
                const productosMezclados = productos.sort(() => Math.random() - 0.5)
                this.productosAleatorios = productosMezclados.slice(0, 18)

                console.log(this.productosAleatorios)
            })

        },
        filtroNombreIlustrador(){
            let filtro = this.ilustradores.filter(ilustrador => ilustrador.nick.toLowerCase().includes(this.nombreIlustrador.toLowerCase()))
            console.log(filtro)
            this.ilustradoresFiltrados = filtro
        },
        
    },
    computed: {
     
    },
    mounted() {
        AOS.init();
    },
}).mount("#app")
