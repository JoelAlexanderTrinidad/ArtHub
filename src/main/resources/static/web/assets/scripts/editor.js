const { createApp } = Vue;

createApp({
    data(){
        return {
            currentIndex: 0,
            images: [],
            seleccionado: 'vista-previa__editor-producto',
            colorSeleccionado: 'BLANCO',
            medida: 'L',
            productoSeleccionado: 'REMERA',
            listaCarrito:[],
            listaProductosGenericos:[],
            listaIlustraciones:[],
            productoIlustracionActivo:null,
        }
    },
    created(){
        if(sessionStorage.getItem('logIn') == 'true' ){
            this.loginAux = sessionStorage.getItem('logIn')
        }
        this.cargarDatos();

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
        prevImage() {
            console.log("prev")
            this.currentIndex = (this.currentIndex - 1 + this.images.length) % this.images.length
            console.table([this.images[this.currentIndex],this.productoSeleccionado,this.colorSeleccionado,this.medida]);
          },
        nextImage() {
            console.log("next")
            this.currentIndex = (this.currentIndex + 1) % this.images.length
            console.table([this.images[this.currentIndex],this.productoSeleccionado,this.colorSeleccionado,this.medida]);
        },
        llavero(){

        },
        cambiarColor(color){
            console.log(color)
            this.colorSeleccionado = color
            console.table([this.images[this.currentIndex],this.productoSeleccionado,this.colorSeleccionado,this.medida]);
        },
        seleccion(icono, producto){
            this.seleccionado = icono
            this.productoSeleccionado = producto
            if(producto=="REMERA"){
                this.medida="L"
                this.colorSeleccionado="BLANCO"
            }else if(producto=="PRINT"){
                this.medida="MEDIANO"
            }else{
                this.medida=""
            }
            console.log(this.medida)
            if(icono == 'vista-previa__editor-producto-llavero' || icono == 'vista-previa__editor-producto-cuaderno'  || icono == 'vista-previa__editor-producto-retrato' || icono == 'vista-previa__editor-producto-taza'){
                this.colorSeleccionado = ''
            }
            console.table([this.images[this.currentIndex],this.productoSeleccionado,this.colorSeleccionado,this.medida]);
        },
        talle(medida){
            this.medida = medida
            console.table([this.images[this.currentIndex],this.productoSeleccionado,this.colorSeleccionado,this.medida]);
        },
        cargarDatos(){
            axios.get("https://arthub-an6d.onrender.com/api/productos")
            .then(res=>{
                this.listaProductosGenericos=res.data;
            })
            const parameterSearch= new URLSearchParams(location.search);
            const id=parameterSearch.get("id");
            axios.get(`https://arthub-an6d.onrender.com/api/ilustracion/${id}`)
            .then(res=>{
                this.listaIlustraciones=res.data;
                this.images=this.listaIlustraciones.map(ilustracion=> ilustracion.imgURL)
                this.currentIndex=this.listaIlustraciones.findIndex(ilu=>ilu.id==id)
                if(localStorage.getItem("Carrito")){
                    this.listaCarrito= JSON.parse(localStorage.getItem("Carrito"));
                }
            })

        },
        obtenerProducto(){
            if (this.productoSeleccionado=="LLAVERO" || this.productoSeleccionado=="LIBRETA" || this.productoSeleccionado=="TAZA"){
                return this.listaProductosGenericos.find(prod=> prod.tipoProducto==this.productoSeleccionado);
            }else if (this.productoSeleccionado=="PRINT"){
                return this.listaProductosGenericos.find(prod=> prod.tipoProducto==this.productoSeleccionado && prod.tamaño==this.medida);
            }else{
                return this.listaProductosGenericos.find(prod=> prod.tipoProducto==this.productoSeleccionado && prod.talla==this.medida && prod.color==this.colorSeleccionado);
            }
        },
        agregarAlCarrito(){
            let ilustracion=this.listaIlustraciones[this.currentIndex];
            let producto= this.obtenerProducto();
            let index= this.listaCarrito.findIndex(prod=>prod.productoId===producto.id && prod.ilustracionId===ilustracion.id);
            if (index<0){
                console.log("pusheado")
                let nuevoProducto={
                    productoId:producto.id,
                    ilustracionId:ilustracion.id,
                    cantidad:1
                }
                this.productoIlustracionActivo=nuevoProducto;
                this.listaCarrito.push(nuevoProducto);
            }else{
                console.log("agregado")
                this.listaCarrito[index].cantidad++;
                this.productoIlustracionActivo=this.listaCarrito[index];
            }
            localStorage.setItem("Carrito", JSON.stringify(this.listaCarrito));
        },
        restarAlCarrito(){
            let ilustracion=this.listaIlustraciones[this.currentIndex];
            let producto= this.obtenerProducto();
            let index= this.listaCarrito.findIndex(prod=>prod.productoId===producto.id && prod.ilustracionId===ilustracion.id);
            this.listaCarrito[index].cantidad--;
            this.productoIlustracionActivo=this.listaCarrito[index];
            if(this.listaCarrito[index].cantidad<=0){
                console.log("eliminado")
                this.listaCarrito.splice(index,1);
            }else{
                console.log("restado")
            }
            localStorage.setItem("Carrito", JSON.stringify(this.listaCarrito));
        },
        logOut(){
            sessionStorage.setItem('logIn', false)
            this.loginAux = false
            axios.post('https://arthub-an6d.onrender.com/api/logout')
        },
    },
    computed: {
        currentImage() {
          return this.images[this.currentIndex]
        },
        estaEnCarrito(){
            // if(this.productoIlustracionActivo){
                // if(localStorage.getItem("Carrito")){
                if(this.listaCarrito){
                    // let carrito= JSON.parse(localStorage.getItem("Carrito"));
                    let ilustracion=this.listaIlustraciones[this.currentIndex];
                    let producto;
                    if (this.productoSeleccionado=="LLAVERO" || this.productoSeleccionado=="LIBRETA" || this.productoSeleccionado=="TAZA"){
                        producto=this.listaProductosGenericos.find(prod=> prod.tipoProducto==this.productoSeleccionado);
                    }else if (this.productoSeleccionado=="PRINT"){
                        producto=this.listaProductosGenericos.find(prod=> prod.tipoProducto==this.productoSeleccionado);
                        // producto=this.listaProductosGenericos.find(prod=> prod.tipoProducto==this.productoSeleccionado && prod.tamaño==this.medida);
                    }else{
                        producto=this.listaProductosGenericos.find(prod=> prod.tipoProducto==this.productoSeleccionado && prod.talla==this.medida && prod.color==this.colorSeleccionado);
                    }
                    let index= this.listaCarrito.findIndex(prod=>prod.productoId===producto.id && prod.ilustracionId===ilustracion.id);
                    if (index>=0){
                        this.productoIlustracionActivo=this.listaCarrito[index];
                        return this.productoIlustracionActivo.cantidad;
                    }
                }
            // }
            return 0;
        }
    }
  
}).mount("#app")