package org.example;

public class LogicaBooleana {

    public static class ResultadoLogico {
        public final boolean a;
        public final boolean b;
        public final boolean notA;
        public final boolean notB;
        public final boolean and;
        public final boolean or;
        public final boolean xor; // Bonus: O exclusivo (uno u otro, pero no ambos)

        public ResultadoLogico(boolean a, boolean b) {
            this.a = a;
            this.b = b;
            this.notA = !a;
            this.notB = !b;
            this.and = a && b;
            this.or = a || b;
            this.xor = a ^ b;
        }
    }

    public ResultadoLogico calcular(boolean a, boolean b) {
        return new ResultadoLogico(a, b);
    }
}