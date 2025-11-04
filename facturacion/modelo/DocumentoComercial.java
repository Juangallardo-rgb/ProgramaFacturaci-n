package com.tuempresa.facturacion.modelo;
 
import java.math.*;
import java.time.*;
import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.openxava.annotations.*;
import org.openxava.calculators.*;

import com.tuempresa.facturacion.calculadores.*;

import lombok.*;
 
@Entity @Getter @Setter
@View(members= // Esta vista no tiene nombre, por tanto será la vista usada por defecto
"anio, numero, fecha;" + // Separados por coma significa en la misma línea
		"datos{" +
		"cliente;" + // Punto y coma significa nueva línea
		"detalles;" +
		"observaciones" +
		"}"
)
abstract public class DocumentoComercial extends Identificable{

 
    @Column(length=4)
    @DefaultValueCalculator(CurrentYearCalculator.class) // Año actual
    int anio;
 
    @Column(length=6)
    @DefaultValueCalculator(value=CalculadorSiguienteNumeroParaAnio.class,
    properties=@PropertyValue(name="anio")) 
    int numero;
 
    @Required
    @DefaultValueCalculator(CurrentLocalDateCalculator.class) // Fecha actual
    LocalDate fecha;
    
    @ManyToOne(fetch=FetchType.LAZY, optional=false) // El cliente es obligatorio
    @ReferenceView("Simple") // La vista llamada 'Simple' se usará para visualizar esta referencia
    Cliente cliente;
    
    @ElementCollection
    @ListProperties(
            "producto.numero, producto.descripcion, cantidad, precioPorUnidad, " +
            "importe+[" + 
            	"documentoComercial.porcentajeIVA," +
            	"documentoComercial.iva," +
            	"documentoComercial.importeTotal" +
            "]" 
        )	
    Collection<Detalle> detalles;
 
    @TextArea
    String observaciones;
    
    @Digits(integer=2, fraction=0) // Para indicar su tamaño
    BigDecimal porcentajeIVA;
       
    @ReadOnly
    @Stereotype("DINERO")
    @Calculation("sum(detalles.importe) * porcentajeIVA / 100")
    BigDecimal iva;

    @ReadOnly
    @Stereotype("DINERO")
    @Calculation("sum(detalles.importe) + iva")    
    BigDecimal importeTotal;
}