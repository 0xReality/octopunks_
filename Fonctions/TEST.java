package Fonctions;

import java.util.ArrayList;

import Compilation.Register;


public class TEST{

    private boolean retval;
    private ArrayList<Register> r;
    public TEST(int val1, int val2, String ope, ArrayList<Register> r){
        this.r = r;
        switch (ope) {
            case ">":
                retval = (val1 > val2);
                break;
            case "<":
                retval = (val1 < val2);
                break;
            case "==":
                retval = (val1 == val2);
                break;
            case "!=":
                retval = (val1 != val2);
                break;
            case ">=":
                retval = (val1 >= val2);
                break;
            case "<=":
                retval = (val1 <= val2);
                break;
            default:
                break;
        }
        updateReg();
    }
    
    public void updateReg(){
        if(retval == true){
            r.get(2).setValeur(1);
        }
    }

}
