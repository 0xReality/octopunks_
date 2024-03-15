package Robot;

import java.util.Random;

public class Crypto extends ObjetOctopunk{
    
    public int euro; 
    public int dollard;
    public int pesos; 
    Random rand = new Random();

    public Crypto(){
        super("Crypto",2,"p"); 
        binance(); 
    }

    public void binance(){
        euro = rand.nextInt(100);
        dollard = rand.nextInt(1000); 
        pesos = rand.nextInt(10000);  

    }

    public int getEuro(){
        return this.euro; 
    }

    public int getDollard()
    {
        return this.dollard; 
    }

    public int getPesos()
    {
        return this.pesos; 
    }
}
