package com.tuempresa.facturacion.modelo;
 
import javax.persistence.*;

import org.openxava.annotations.*;

import lombok.*;
 
@Entity @Getter @Setter
@View(extendsView="super.DEFAULT",
	members="factura {factura}"
)
@View(name="SinClienteNiFactura",
	members=
	"anio, numero, fecha;"
	+ "detalles;"
	+ "observaciones"
)
public class Pedido extends DocumentoComercial {
	@ManyToOne
	@ReferenceView("SinClienteNiPedidos")
    Factura factura; // Referencia a factura añadida
 
}
