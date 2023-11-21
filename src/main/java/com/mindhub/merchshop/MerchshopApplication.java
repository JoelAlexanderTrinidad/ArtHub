package com.mindhub.merchshop;

import com.mindhub.merchshop.models.*;
import com.mindhub.merchshop.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Set;


@SpringBootApplication
public class MerchshopApplication {
	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(MerchshopApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(UsuarioRepository usuarioRepository, CompraRepository compraRepository, PaqueteDeProductosRepository paqueteDeProductosRepository, ProductoRepository productoRepository, IlustradorRepository ilustradorRepository, IlustracionRepository ilustracionRepository, ProductoRepository productoIlustracionRepository, DireccionRepository direccionRepository) {
		return (args) -> {

			//Ilustrador1 y sus ilustraciones
			Ilustrador ilustrador1 = new Ilustrador("izuu@gmail.com", "Joel Trinidad", "Izuu", "Buenos Aires","https://firebasestorage.googleapis.com/v0/b/arthub-102d1.appspot.com/o/305033956_1263199574444982_6802776079643692029_n_494110.png?alt=media&token=70a0f9bf-ae73-488f-a9eb-d092b65b9baf", "000000", Set.of("http://instagram.com", "http://twitter.com"),List.of());

			Ilustracion ilustracion1 = new Ilustracion("A", "https://firebasestorage.googleapis.com/v0/b/arthub-102d1.appspot.com/o/109086572_165094608472609_1619852278073271916_n_493205.png?alt=media&token=b48e8969-6b2d-427c-95e8-ae474b390948", ilustrador1, List.of());
			Ilustracion ilustracion2 = new Ilustracion("B", "https://firebasestorage.googleapis.com/v0/b/arthub-102d1.appspot.com/o/117811586_171719334476803_1337973533433460834_n_493253.png?alt=media&token=a3e0a217-12dc-4c59-8d6d-9a8ea24f7678", ilustrador1, List.of());
			Ilustracion ilustracion3 = new Ilustracion("C", "https://firebasestorage.googleapis.com/v0/b/arthub-102d1.appspot.com/o/119031130_180659546916115_5991594656918939829_n_493257.png?alt=media&token=a9f7881e-be79-447d-9c8a-7ce191a4c64d", ilustrador1, List.of());
			Ilustracion ilustracion4 = new Ilustracion("D", "https://firebasestorage.googleapis.com/v0/b/arthub-102d1.appspot.com/o/hero-color-sketch.png?alt=media&token=da1ce51e-71f4-4c69-9574-0dbd4371371a", ilustrador1, List.of());

			//Ilustrador2 y sus ilustraciones
			Ilustrador ilustrador2 = new Ilustrador("niett@gmail.com", "Cameron Velasquez", "Niett", "Santiago","", "000000", Set.of("http://instagram.com", "http://twitter.com"), List.of());

			Ilustracion ilustracion5 = new Ilustracion("A2", "https://firebasestorage.googleapis.com/v0/b/arthub-102d1.appspot.com/o/CameronG.png?alt=media&token=7eab0746-09dc-4382-bceb-89cc2366db45", ilustrador2, List.of());
			Ilustracion ilustracion6 = new Ilustracion("B2", "https://firebasestorage.googleapis.com/v0/b/arthub-102d1.appspot.com/o/CameronF.PNG?alt=media&token=47749f76-8ffb-4a82-bd44-c677fcd9ca5b", ilustrador2, List.of());
			Ilustracion ilustracion7 = new Ilustracion("C2", "https://firebasestorage.googleapis.com/v0/b/arthub-102d1.appspot.com/o/CameronD.PNG?alt=media&token=857bc98f-7784-46d2-b8fd-e822c24b659a", ilustrador2, List.of());
			Ilustracion ilustracion8 = new Ilustracion("D2", "https://firebasestorage.googleapis.com/v0/b/arthub-102d1.appspot.com/o/CameronA.JPG?alt=media&token=e2fed519-dbae-4c2e-bba0-43a7bdd8e392", ilustrador2, List.of());

			//Ilustradora3 y sus ilustraciones
			Ilustrador ilustrador3 = new Ilustrador("toffitafoffita@gmail.com", "Sofia Gomelsky", "ToffaG", "Cordoba","https://firebasestorage.googleapis.com/v0/b/arthub-102d1.appspot.com/o/panaretrato.png?alt=media&token=c8734b0c-61a3-4c9e-afe6-9f29982e21b0", "123", Set.of("http://instagram.com", "http://twitter.com"), List.of());

			Ilustracion ilustracion9 = new Ilustracion("BTS pelo rojo", "https://firebasestorage.googleapis.com/v0/b/arthub-102d1.appspot.com/o/pana5.png?alt=media&token=ae0ce714-3307-49e4-a410-0a98b1b466e8", ilustrador3, List.of());
			Ilustracion ilustracion10 = new Ilustracion("Hobbi", "https://firebasestorage.googleapis.com/v0/b/arthub-102d1.appspot.com/o/pana6.png?alt=media&token=752989be-b0c1-470f-8914-325f142c6583", ilustrador3, List.of());
			Ilustracion ilustracion11 = new Ilustracion("Rin sapito", "https://firebasestorage.googleapis.com/v0/b/arthub-102d1.appspot.com/o/pana4.png?alt=media&token=3e91f417-b7bb-4bd7-9215-e493ee25d4d8", ilustrador3, List.of());
			Ilustracion ilustracion12 = new Ilustracion("Bennet pompompurin", "https://firebasestorage.googleapis.com/v0/b/arthub-102d1.appspot.com/o/pana3.png?alt=media&token=6e10713a-74f4-432f-acc5-ee2f33c6b75a", ilustrador3, List.of());
			Ilustracion ilustracion13 = new Ilustracion("Otro bts", "https://firebasestorage.googleapis.com/v0/b/arthub-102d1.appspot.com/o/pana2.png?alt=media&token=12efda3b-e850-4f8d-963b-db0ff38d4cbd", ilustrador3, List.of());
			Ilustracion ilustracion14 = new Ilustracion("Piba de twice", "https://firebasestorage.googleapis.com/v0/b/arthub-102d1.appspot.com/o/pana1.png?alt=media&token=e0ddab29-ce4e-4806-b54e-4cfe2afacf21", ilustrador3, List.of());

			ilustradorRepository.save(ilustrador1);
			ilustradorRepository.save(ilustrador2);
			ilustradorRepository.save(ilustrador3);

			ilustracionRepository.save(ilustracion1);
			ilustracionRepository.save(ilustracion2);
			ilustracionRepository.save(ilustracion3);
			ilustracionRepository.save(ilustracion4);
			ilustracionRepository.save(ilustracion5);
			ilustracionRepository.save(ilustracion6);
			ilustracionRepository.save(ilustracion7);
			ilustracionRepository.save(ilustracion8);
			ilustracionRepository.save(ilustracion9);
			ilustracionRepository.save(ilustracion10);
			ilustracionRepository.save(ilustracion11);
			ilustracionRepository.save(ilustracion12);
			ilustracionRepository.save(ilustracion13);
			ilustracionRepository.save(ilustracion14);

			//Productos equis(stock del ilustrador1)
			//il1 RAMERA
			Producto ramera1 = new Producto( 24, "stock marzo", 2500.0, TipoProducto.REMERA, TallaProducto.XS, ColorProducto.AMARILLO);
			Producto ramera2 = new Producto(30, "stock marzo", 2500.0, TipoProducto.REMERA, TallaProducto.S, ColorProducto.AMARILLO);
			Producto ramera3 = new Producto( 40, "stock marzo", 3500.0, TipoProducto.REMERA, TallaProducto.M, ColorProducto.AMARILLO);
			Producto ramera4 = new Producto( 14, "stock marzo", 4500.0, TipoProducto.REMERA, TallaProducto.L, ColorProducto.AMARILLO);
			Producto ramera5 = new Producto( 38, "stock marzo", 4500.0, TipoProducto.REMERA, TallaProducto.XL, ColorProducto.AMARILLO);

			Producto ramera6 = new Producto( 15, "stock marzo", 2500.0, TipoProducto.REMERA, TallaProducto.XS, ColorProducto.AZUL);
			Producto ramera7 = new Producto(45, "stock marzo", 2500.0, TipoProducto.REMERA, TallaProducto.S, ColorProducto.AZUL);
			Producto ramera8 = new Producto(89, "stock marzo", 3500.0, TipoProducto.REMERA, TallaProducto.M, ColorProducto.AZUL);
			Producto ramera9 = new Producto( 66, "stock marzo", 4500.0, TipoProducto.REMERA, TallaProducto.L, ColorProducto.AZUL);
			Producto ramera10 = new Producto( 25, "stock marzo", 4500.0, TipoProducto.REMERA, TallaProducto.XL, ColorProducto.AZUL);

			Producto ramera11 = new Producto( 20, "stock marzo", 2500.0, TipoProducto.REMERA, TallaProducto.XS, ColorProducto.ROSA);
			Producto ramera12 = new Producto(50, "stock marzo", 2500.0, TipoProducto.REMERA, TallaProducto.S, ColorProducto.ROSA);
			Producto ramera13 = new Producto( 60, "stock marzo", 3500.0, TipoProducto.REMERA, TallaProducto.M, ColorProducto.ROSA);
			Producto ramera14 = new Producto(85, "stock marzo", 4500.0, TipoProducto.REMERA, TallaProducto.L, ColorProducto.ROSA);
			Producto ramera15 = new Producto( 25, "stock marzo", 4500.0, TipoProducto.REMERA, TallaProducto.XL, ColorProducto.ROSA);

			Producto ramera16 = new Producto( 64, "stock marzo", 2500.0, TipoProducto.REMERA, TallaProducto.XS, ColorProducto.VERDE);
			Producto ramera17 = new Producto( 28, "stock marzo", 2500.0, TipoProducto.REMERA, TallaProducto.S, ColorProducto.VERDE);
			Producto ramera18 = new Producto(53, "stock marzo", 3500.0, TipoProducto.REMERA, TallaProducto.M, ColorProducto.VERDE);
			Producto ramera19 = new Producto( 14, "stock marzo", 4500.0, TipoProducto.REMERA, TallaProducto.L, ColorProducto.VERDE);
			Producto ramera20 = new Producto( 58, "stock marzo", 4500.0, TipoProducto.REMERA, TallaProducto.XL, ColorProducto.VERDE);

			Producto ramera21 = new Producto( 37, "stock marzo", 2500.0, TipoProducto.REMERA, TallaProducto.XS, ColorProducto.BLANCO);
			Producto ramera22 = new Producto( 25, "stock marzo", 2500.0, TipoProducto.REMERA, TallaProducto.S, ColorProducto.BLANCO);
			Producto ramera23 = new Producto( 44, "stock marzo", 3500.0, TipoProducto.REMERA, TallaProducto.M, ColorProducto.BLANCO);
			Producto ramera24 = new Producto( 16, "stock marzo", 4500.0, TipoProducto.REMERA, TallaProducto.L, ColorProducto.BLANCO);
			Producto ramera25 = new Producto( 39, "stock marzo", 4500.0, TipoProducto.REMERA, TallaProducto.XL, ColorProducto.BLANCO);

			Producto ramera26 = new Producto( 50, "stock marzo", 2500.0, TipoProducto.REMERA, TallaProducto.XS, ColorProducto.ROJO);
			Producto ramera27 = new Producto( 43, "stock marzo", 2500.0, TipoProducto.REMERA, TallaProducto.S, ColorProducto.ROJO);
			Producto ramera28 = new Producto( 90, "stock marzo", 3500.0, TipoProducto.REMERA, TallaProducto.M, ColorProducto.ROJO);
			Producto ramera29 = new Producto( 16, "stock marzo", 4500.0, TipoProducto.REMERA, TallaProducto.L, ColorProducto.ROJO);
			Producto ramera30 = new Producto( 54, "stock marzo", 4500.0, TipoProducto.REMERA, TallaProducto.XL, ColorProducto.ROJO);

			productoRepository.save(ramera1);
			productoRepository.save(ramera2);
			productoRepository.save(ramera3);
			productoRepository.save(ramera4);
			productoRepository.save(ramera5);
			productoRepository.save(ramera6);
			productoRepository.save(ramera7);
			productoRepository.save(ramera8);
			productoRepository.save(ramera9);
			productoRepository.save(ramera10);
			productoRepository.save(ramera11);
			productoRepository.save(ramera12);
			productoRepository.save(ramera13);
			productoRepository.save(ramera14);
			productoRepository.save(ramera15);
			productoRepository.save(ramera16);
			productoRepository.save(ramera17);
			productoRepository.save(ramera18);
			productoRepository.save(ramera19);
			productoRepository.save(ramera20);
			productoRepository.save(ramera21);
			productoRepository.save(ramera22);
			productoRepository.save(ramera23);
			productoRepository.save(ramera24);
			productoRepository.save(ramera25);
			productoRepository.save(ramera26);
			productoRepository.save(ramera27);
			productoRepository.save(ramera28);
			productoRepository.save(ramera29);
			productoRepository.save(ramera30);

			//TAZA
			Producto taza1 = new Producto(50, "stock marzo", 1000.0, TipoProducto.TAZA, ColorProducto.BLANCO);

			productoRepository.save(taza1);


			//LLAVERO
			Producto llavero1 = new Producto( 100, "stock marzo", 800.0, TipoProducto.LLAVERO);
			productoRepository.save(llavero1);


			//LIBRETA
			Producto libreta1 = new Producto(25, "stock marzo", 400.0, TipoProducto.LIBRETA, TamañoProducto.PEQUEÑO);
			Producto libreta2 = new Producto(30, "stock marzo", 600.0, TipoProducto.LIBRETA, TamañoProducto.MEDIANO);
			Producto libreta3 = new Producto( 10, "stock marzo", 900.0, TipoProducto.LIBRETA, TamañoProducto.GRANDE);

			productoRepository.save(libreta1);
			productoRepository.save(libreta2);
			productoRepository.save(libreta3);

			//PRINT
			Producto print1 = new Producto(40, "stock marzo", 200.0, TipoProducto.PRINT, TamañoProducto.PEQUEÑO);
			Producto print2 = new Producto(70, "stock marzo", 400.0, TipoProducto.PRINT, TamañoProducto.MEDIANO);
			Producto print3 = new Producto(50, "stock marzo", 600.0, TipoProducto.PRINT, TamañoProducto.GRANDE);

			productoRepository.save(print1);
			productoRepository.save(print2);
			productoRepository.save(print3);

			Direccion dir1= new Direccion("Argentina","La Plata","Diagonal 77, 730, 1b","1900","Edificio ladrillos a la vista");
			Direccion dir2= new Direccion("Argentina","CABA","Av Belgrano 1550","1155","Departamento con puertas de vidrio");

			Usuario us1= new Usuario("oscarschwerdt@gmail.com","Oscar Schwerdt","Ranso",passwordEncoder.encode("123456"),dir2);
			us1.setAvatarUrl("https://firebasestorage.googleapis.com/v0/b/arthub-102d1.appspot.com/o/prison-mike.webp?alt=media&token=ccb59e27-26ad-4c03-89d5-34b9f70a9d11");

			// https://i.postimg.cc/rFWNbhWZ/unnamed.png
			Usuario us2= new Usuario("joel@asd.com","Joel Trinidad","Alexx",passwordEncoder.encode("123123"),dir1);
			us2.setAvatarUrl("https://i.postimg.cc/rFWNbhWZ/unnamed.png");

			direccionRepository.save(dir1);
			usuarioRepository.save(us1);

			direccionRepository.save(dir2);
			usuarioRepository.save(us2);

			//Usuario usuario1 = new Usuario("niettpls@gmail.com", "Julio Perez", "Nick", "123");

//			Compra compra1 = new Compra(usuario1, List.of(), LocalDateTime.now(), generarNumeroCompra());
//
//			PaqueteDeProductos paqueteDeProductos1 = new PaqueteDeProductos(compra1, productoIlustracion1, (byte) 2);
//			PaqueteDeProductos paqueteDeProductos2 = new PaqueteDeProductos(compra1, productoIlustracion2, (byte) 2);
//			PaqueteDeProductos paqueteDeProductos3 = new PaqueteDeProductos(compra1, productoIlustracion3, (byte) 2);





			//	usuarioRepository.save(usuario1);
//			compraRepository.save(compra1);
//			paqueteDeProductosRepository.save(paqueteDeProductos1);
//			paqueteDeProductosRepository.save(paqueteDeProductos2);
//			paqueteDeProductosRepository.save(paqueteDeProductos3);

			List<Producto> productoIlustraciones = productoIlustracionRepository.findAll();

		};
	}

}
