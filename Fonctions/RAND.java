package Fonctions;

import java.util.Random;

import Compilation.Register;

public class RAND {

    public RAND(int lo, int hi, Register r) {
        Random random = new Random();
        r.setValeur(lo + random.nextInt(hi - lo));
    }
}
