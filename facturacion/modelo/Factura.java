package com.tuempresa.facturacion.modelo;
 
import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;

import lombok.*;
 
@Entity @Getter @Setter
@View( extendsView = "super.DEFAULT",
	members="pedidos{pedidos}"
)
@View(name="SinClienteNiPedidos",
	members=
	"anio, numero, fecha;"
	+"detalles;"
	+"observaciones"
)
public class Factura extends DocumentoComercial {
	  @OneToMany(mappedBy="factura")
	  @CollectionView("SinClienteNiFactura")
	    Collection<Pedido> pedidos; // Colección de entidades Pedido añadida
	 
 
}