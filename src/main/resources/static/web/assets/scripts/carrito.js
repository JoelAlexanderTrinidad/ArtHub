const { createApp } = Vue

createApp( {
    data(){
        return {
            nombre: "",
            nickTitulo: "",
            nick: "",
            apellido: "",
            email: "",
            direccion: "",
            codigoPostal: "",
            ciudad: "",
            pais: "",
            descripcionExtra: "",
            imagenUsuario: "",
            cvvTarjeta: 857,
            numeroTarjeta: "2315-1535-5567-6548",
            nombreTarjeta: "Melba Morel",
            expiracionTarjeta: "30/07",
            auxCambiarDatos: false,
            productos: [],
            error: "",
            loginAux: false,
            pagoExitoso: false,
            listaProductosGenericos:[],
            listaIlustraciones:[],
            listaCarrito: []
        }
    },
    created(){
        if(sessionStorage.getItem('logIn') == 'true' ){
            this.loginAux = sessionStorage.getItem('logIn')
        }
        this.cargarDatos()
        this.informacion()
    },
    methods: {
        formatCurrency(amount){
            // let options = { style: 'currency', currency: 'MXN' };
            let numberFormat = new Intl.NumberFormat('es-AR');
            return numberFormat.format(amount);
        },
        cargarDatos(){
            axios.get("/api/productos")
            .then(res=>{
                this.listaProductosGenericos=res.data;
            })
            axios.get(`/api/ilustraciones`)
            .then(res=>{
                this.listaIlustraciones=res.data;
            })
            if(localStorage.getItem("Carrito")){
                this.listaCarrito= JSON.parse(localStorage.getItem("Carrito"));
            }

        },
        
        ilustracionURL(id){
            return this.listaIlustraciones.find(ilu=>ilu.id==id).imgURL;
        },
        productoURL(id){
            let producto=this.listaProductosGenericos.find(prod=> prod.id==id);
            console.log(producto)
            if (producto.tipoProducto=="REMERA"){
                switch (producto.color) {
                    case "ROJO":
                        return "./assets/images/productos/remera_roja.png"
                    case "AMARILLO":
                        return "./assets/images/productos/remera_amarilla.png"
                    case "VERDE":
                        return "./assets/images/productos/remera_verde.png"
                    case "ROSA":
                        return "./assets/images/productos/remera_rosa.png"
                    case "AZUL":
                        return "./assets/images/productos/remera_azul.png"               
                    default:
                        return "./assets/images/productos/remera_blanca.png"
                }
            }else if(producto.tipoProducto=="LIBRETA"){
                return `./assets/images/productos/cuaderno.png`
            }
            else if(producto.tipoProducto=="TAZA"){
                return `./assets/images/productos/taza-12001-15d1df27529361ea9316093719791002-1024-1024.png`
            }
            else if(producto.tipoProducto=="LLAVERO"){
                return `./assets/images/productos/llavero-plastico-cuadrado.png`
            }
            return `./assets/images/productos/Poster-Mockup.png`

        },
        calcularTotal(){
            return this.listaCarrito.reduce((acu,prod)=>{
                return acu + (this.listaProductosGenericos.find(p=>p.id==prod.productoId).precio*prod.cantidad);
            },0)
        },
        eliminarProducto(producto){
            let index= this.listaCarrito.findIndex(p=> p.productoId==producto.productoId);
            this.listaCarrito.splice(index,1);
            localStorage.setItem("Carrito", JSON.stringify(this.listaCarrito));
            this.cargarDatos()
        },
        agregarAlCarrito(producto){
            let index= this.listaCarrito.findIndex(prod=>prod.productoId===producto.productoId && prod.ilustracionId===producto.ilustracionId);    
            this.listaCarrito[index].cantidad++;
            localStorage.setItem("Carrito", JSON.stringify(this.listaCarrito));
            this.cargarDatos()
        },
        restarAlCarrito(producto){
            let index= this.listaCarrito.findIndex(prod=>prod.productoId===producto.productoId && prod.ilustracionId===producto.ilustracionId);  
            this.listaCarrito[index].cantidad--;
            if(this.listaCarrito[index].cantidad<=0){
                this.listaCarrito.splice(index,1);
            }
            localStorage.setItem("Carrito", JSON.stringify(this.listaCarrito));
            this.cargarDatos()
        },
        informacion(){
            axios.get(`/api/usuario/actual`)
                .then(res=> {
                    console.log(res)
                    this.nombre = res.data.nombre
                    this.nickTitulo = res.data.nick
                    this.apellido = res.data.nombre
                    this.email = res.data.email
                    this.imagenUsuario = res.data.imagenUsuario
                    this.ciudad = res.data.direcciones.ciudad
                    this.pais = res.data.direcciones.pais
                    this.direccion = res.data.direcciones.direccion
                    this.codigoPostal = res.data.direcciones.zipCode
                    this.descripcionExtra = res.data.direcciones.descripcionExtra
                    this.productos = res.data.productos
                })
                .catch(error => console.log(error))
        },
        pagar(){
            axios.post('https://arthub-an6d.onrender.com/api/cards/transaction',{
                number : this.numeroTarjeta,
                cvv: this.cvvTarjeta,
                description : "Items bought on Arthub",
                amount: 1
            }).then(response => {
                    this.cerrarModal()
                    this.pagoExitoso = true
                    setTimeout(() => {
                        this.pagoExitoso = false
                    }, 3000)
                    console.log(response)
                    axios.post(`/api/email/pdf`,this.listaCarrito)
                    .then(res=>{
                        console.log("Correcto")
                    })
                })
                .catch(error => {
                    this.error = error.response.data
                })
        },
        activarFormulario(){
            document.getElementById('actualizarDatosBoton').classList.remove('ocultar-modal')
            for(let i = 0; i <= 9; i++){
                let input = document.getElementsByTagName("input")[i]
                input.removeAttribute("readonly")
                input.classList.add('formulario-input-bordebottom')
            }
        },
        actualizar(){
            axios.patch(`/api/usuario/modificar`,`nombre=${this.nombre}&apellido=${this.apellido}&nick=${this.nick}&direccion=${this.direccion}
            &codigoPostal=${this.codigoPostal}&ciudad=${this.ciudad}&pais=${this.pais}&descripcionExtra=${this.descripcionExtra}`,
                {headers:{'content-type':'application/x-www-form-urlencoded'}})
                .then(res=> {
                    this.informacion()
                })
                .catch(error => {
                    this.error = error.data
            })
            document.getElementById('actualizarDatosBoton').classList.add('ocultar-modal')
            for(let i = 0; i <= 9; i++){
                let input = document.getElementsByTagName("input")[i]
                input.setAttribute("readonly", "")
                input.classList.remove('formulario-input-bordebottom')
            }
        },
        logOut(){
            sessionStorage.setItem('logIn', false)
            this.loginAux = false
            axios.post('/api/logout')
        },
        mostrarDatos(idMostrar, idOcultar, idTextoActivo,idTextoDesactivado){
            document.getElementById(idMostrar).classList.remove('ocultar-capa')
            document.getElementById(idOcultar).classList.add('ocultar-capa')
            document.getElementById(idTextoActivo).classList.add('informacion-navegar-activo')
            document.getElementById(idTextoDesactivado).classList.remove('informacion-navegar-activo')
            document.getElementById('iconoEditar').classList.toggle('ocultar-capa')
        },
        cerrarModal() {
            document.getElementById('modalCarrito').classList.toggle('ocultar-modal')
            this.error="";
        },
    }
}).mount("#app")
