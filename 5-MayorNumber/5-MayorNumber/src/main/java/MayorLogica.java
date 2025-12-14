package org.example;

import java.math.BigInteger;

public class MayorLogica {

    // Método puro: Recibe 3 números y devuelve el mayor
    public BigInteger encontrarMayor(BigInteger n1, BigInteger n2, BigInteger n3) {
        // Usamos la función max() de BigInteger encadenada.
        // Es más limpio y seguro que escribir muchos "if/else".
        return n1.max(n2).max(n3);
    }
}