package com.tuempresa.facturacion.modelo;
import lombok.*;

@Getter @Setter
public class Incidencia {
	// Propiedad persistente
	@Getter @Setter // Tiene getter y setter
	int cantidad; // Tiene un campo, por tanto es persistente
	 
	@Getter @Setter
    int precio;
    String descripcion;
	
    public int getImporte() { // No tiene campo, ni setter, solo un getter
        return cantidad * precio; // con un cálculo
    }

}