package com.tuempresa.facturacion.calculadores;
 
import javax.persistence.*;

import org.openxava.calculators.*;
import org.openxava.jpa.*;

import lombok.*;
 
public class CalculadorSiguienteNumeroParaAnio
    implements ICalculator { // Un calculador tiene que implementar ICalculator
 
    @Getter @Setter     
    int anio; // Este valor se inyectar� antes de calcular
 
    public Object calculate() throws Exception { // Hace el c�lculo
        Query query = XPersistence.getManager() // Una consulta JPA
            .createQuery("select max(f.numero) from Factura f where f.anio = :anio"); // La consulta devuelve
                                                              // el n�mero de factura m�ximo del a�o indicado
        query.setParameter("anio", anio); // Ponemos el a�o inyectado como par�metro de la consulta
        Integer ultimoNumero = (Integer) query.getSingleResult();
        return ultimoNumero == null ? 1 : ultimoNumero + 1; // Devuelve el �ltimo n�mero
                                            // de factura del a�o + 1 o 1 si no hay �ltimo n�mero
    }
 
}
