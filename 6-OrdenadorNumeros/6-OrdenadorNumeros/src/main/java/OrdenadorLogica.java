package org.example;

import java.math.BigInteger;
import java.util.stream.Stream;

public class OrdenadorLogica {

    // Método puro: Recibe dos números y devuelve un String ya formateado "Mayor > Menor"
    public String ordenarDescendente(BigInteger n1, BigInteger n2) {
        // Creamos un stream con los números, ordenamos al revés (reverseOrder)
        // y los unimos con un separador bonito.
        return Stream.of(n1, n2)
                .sorted((a, b) -> b.compareTo(a)) // Orden descendente
                .map(BigInteger::toString)
                .reduce((a, b) -> a + "  >  " + b)
                .orElse("");
    }
}