package Robot;

public class ObjetOctopunk {

        private String name; 
        private int poids; 
        private boolean estTransportable;
    
        public ObjetOctopunk(String name, int Poids)
        {
            this.name = name; 
            this.poids = Poids; 
            estTransportable = true; 
        }
    
        public int getPoids()
        {
            return poids; 
        }
    
        public String getName()
        {
            return name; 
        }
    
        public boolean getTransportable()
        {
            return estTransportable; 
        }

        /* Pour changer le fait qu'un objet soit transportable ou non */
        public void setTransportable(boolean estTransportable)
        {
            this.estTransportable = estTransportable; 
        }

        @Override
        public String toString(){
            return "ObjetOctopunk{" + "Name : '" + name +
                    "\n Poids : " + poids + 
                    "\n Transportabilité :" + estTransportable + "}"; 
        }

    
}
