package Fonctions;

import Compilation.Compilator;

public class JUMP {

    public JUMP(int index, Compilator k)
    {
        k.setLine(index + k.getCurrentLine());
    }
}
