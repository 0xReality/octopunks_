package Robot;

import java.util.Objects;
public class ObjetOctopunk {

        private String name; 
        private int poids; 
        private boolean estTransportable;
        private String id; 
        
    
        public ObjetOctopunk(String name, int Poids, String id)
        {
            if(name==null || name.isEmpty() || poids <= 0 || id == null || id.isEmpty()){
                throw new IllegalArgumentException("Paramétres invalides pour l'objet OctoPunks"); 
            }
            this.name = name; 
            this.poids = Poids; 
            estTransportable = true;
            this.id = id;  
        }
    
        public String getID()
        {
            return this.id; 
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
                    "\n ID :" + getID() +
                    "\n Transportabilité :" + estTransportable + "}"; 
        }

        @Override
        public boolean equals(Object o){
            if(!(o instanceof ObjetOctopunk))
            {
                return false;
            }

            if(getClass() != o.getClass() || o == null)
            {
                return false;
            }

            ObjetOctopunk obj = (ObjetOctopunk) o; 

            return id.equals(obj.getID()); 
        }

        @Override
        public int hashCode()
        {
            return Objects.hash(id); 
        }

        public boolean isValidObject()
        {
            return poids >  0 && name != null && !name.isEmpty(); 
        }

           

    
}
