package Fonctions;

import Compilation.Register;

public class SWIZ {
    private int input;
    private int mask;

    public SWIZ(int input, int mask, Register dest){
        this.input = input;
        this.mask = mask;
        this.swizzle(dest);
    }

    private void swizzle(Register dest) {
        String inputStr = Integer.toString(Math.abs(this.input));
        String maskStr = Integer.toString(this.mask);
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < maskStr.length(); i++) {
            int maskDigit = Character.getNumericValue(maskStr.charAt(i));
            if (maskDigit >= 1 && maskDigit <= 4) {
                int index = inputStr.length() - maskDigit;
                if (index >= 0 && index < inputStr.length()) {
                    result.append(inputStr.charAt(index));
                } else {
                    result.append("0");
                }
            } else {
                result.append("0");
            }
        }

        int finalResult = Integer.parseInt(result.toString());
        if (this.mask < 0) {
            finalResult *= -1;
            if (this.input > 0) {
                finalResult *= -1;
            }
        } else if (this.input < 0) {
            finalResult *= -1;
        }

        dest.setValeur(finalResult);
    }
}
