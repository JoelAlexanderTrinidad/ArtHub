const { createApp } = Vue

createApp( {
    data(){
        return {
            productos: [],
            nombres: "",
            tallas: "",
            medidas: "",
            colores: "",
            stock: 0, 
            loginAux: false,
            productoTalla: '',
            productoMedidaLibreta: '',
            productoMedidaPrint: '',
            productoColor: '',
            remeras: "",
            tazas: "",
            libretas:"",
            prints: "",
            llaveros:"",
            ilustradores: [],
            editar: '',
            nombreProducto: "",
            stockProducto: "",
            descripcionProducto: "",
            precioProducto: undefined,
            ilustrador: "",
            nickIlustrador: "",
            ciudadIlustrador: "",
            avatarIlustrador: ""
        }
    },
    created(){
        if(sessionStorage.getItem('logIn') == 'true' ){
            this.loginAux = sessionStorage.getItem('logIn')
        }
        this.informacion()
    },
    methods: {
        informacion(){
            axios.get(`https://arthub-an6d.onrender.com/api/productos`)
                .then(res=> {
                    console.log(res.data)
                    this.productos = res.data
                    this.nombres = new Set(res.data.map(producto => producto.nombre.split(",")[0]).sort((a, b)=> a.charCodeAt(0) - b.charCodeAt(0)))
                    this.tallas = new Set(res.data.filter(producto => producto.nombre.split(",")[0].includes('REMERA')).map(producto => producto.nombre.split(",")[1]))
                    this.medidas = new Set(res.data.filter(producto => producto.nombre.split(",")[0].includes('PRINT')).map(producto => producto.nombre.split(",")[1]))
                    this.colores = new Set(res.data.filter(producto => producto.nombre.split(",")[0] == 'REMERA').map(producto => producto.nombre.split(",")[2] ))
                    this.remeras = {stock: '-', descripcion: '-', precio: '-'}
                    this.tazas = res.data.filter(producto => producto.nombre.split(",")[0].includes('TAZA'))[0]
                    this.libretas = {stock: '-', descripcion: '-', precio: '-'}
                    this.prints = {stock: '-', descripcion: '-', precio: '-'}
                    this.llaveros = res.data.filter(producto => producto.nombre.split(",")[0].includes('LLAVERO'))[0]
                })
                .catch(error => console.log(error))
                axios.get(`https://arthub-an6d.onrender.com/api/ilustradores`)
                .then(res=> {
                    this.ilustradores = res.data
                })
                .catch(error => console.log(error))
        },
        modificarIlustrador(email){
            document.getElementById('tablaIlustradores').classList.add('ocultar')
            this.ilustrador= this.ilustradores.filter(ilustrador => ilustrador.email == email)
            this.nickIlustrador = this.ilustrador[0].nick
            this.ciudadIlustrador = this.ilustrador[0].ciudad
            this.avatarIlustrador = this.ilustrador[0].avatarURL
        },
        actualizarInformacion(producto){
            if(producto == 'REMERA' && this.productoTalla != '' && this.productoColor !=''){
                this.remeras = this.productos.filter(producto => producto.nombre.split(",")[0].includes('REMERA') && producto.nombre.split(",")[1].includes(this.productoTalla) && producto.nombre.split(",")[2].includes(this.productoColor))[0]
            }else if(producto == 'PRINT' && this.productoMedidaPrint != ''){
                this.prints = this.productos.filter(producto => producto.nombre.split(",")[0].includes('PRINT') && producto.nombre.split(",")[1].includes(this.productoMedidaPrint))[0]           
            }else if(producto == 'LIBRETA' && this.productoMedidaLibreta != ''){
                this.libretas = this.productos.filter(producto => producto.nombre.split(",")[0].includes('LIBRETA')&& producto.nombre.split(",")[1].includes(this.productoMedidaLibreta))[0]
            }
        },
        modificarProducto(producto, id){
            if(id == null){
                document.getElementById('mensaje').classList.remove('texto-mensaje')
                document.getElementById('inicioSesionRegistro').classList.remove('contenedor-despedida')
                document.getElementById('inicioSesionRegistro').classList.toggle('ocultar')
                document.getElementById('mensaje').innerText = 'Tienes que llenar los campos'
                setTimeout(()=>{
                    document.getElementById('inicioSesionRegistro').classList.toggle('contenedor-despedida')
                    setTimeout(()=>{
                        document.getElementById('inicioSesionRegistro').classList.toggle('ocultar')
                    }, 1500)
                }, 2000)
            }else{
                document.getElementById('tabla-productos').classList.add('ocultar')
                this.editar = this.productos.filter(producto => producto.id == id)
                this.nombreProducto = this.editar[0].nombre
                this.stockProducto = this.editar[0].stock
                this.descripcionProducto = this.editar[0].descripcion
                this.precioProducto = this.editar[0].precio.toLocaleString('en-US', {style:"currency", currency:"USD"})
            }
        },
        mensaje(){
            document.getElementById('mensaje').classList.add('texto-mensaje')
            document.getElementById('inicioSesionRegistro').classList.remove('contenedor-despedida')
            document.getElementById('inicioSesionRegistro').classList.toggle('ocultar')
            document.getElementById('mensaje').innerText = 'Los datos fueron actualizados'
            setTimeout(()=>{
                document.getElementById('inicioSesionRegistro').classList.toggle('contenedor-despedida')
                setTimeout(()=>{
                    document.getElementById('inicioSesionRegistro').classList.toggle('ocultar')
                }, 1500)
            }, 2000)
        },
        mostrarModal(idActivado, idDesactivado, idBotonActivo, idBotonDesactivado){
            document.getElementById(idActivado).classList.remove('ocultar')
            document.getElementById(idDesactivado).classList.add('ocultar')
            document.getElementById(idBotonActivo).classList.remove('boton-desactivado')
            document.getElementById(idBotonDesactivado).classList.add('boton-desactivado')
        },
        actualizar(){
            axios.patch(`https://arthub-an6d.onrender.com/api/usuario/modificar`,`nombre=${this.nombre}&apellido=${this.apellido}&nick=${this.nick}&direccion=${this.direccion}
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
            axios.post('https://arthub-an6d.onrender.com/api/logout')
        }
    }
}).mount("#app")
