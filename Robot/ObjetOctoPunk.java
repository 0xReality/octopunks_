package Robot;

import javafx.scene.image.ImageView;

public class ObjetOctoPunk {

        private String name; 
        private int id; 
        private boolean estTransportable;
        private ImageView image;
        private int row;
        private int col;
        private Integer contenu;
    
        public ObjetOctoPunk(String name, int id, ImageView image, int row, int col, Integer contenu)
        {
            this.name = name; 
            this.id = id; 
            estTransportable = true; 
            this.image = image;
            this.row = row;
            this.col = col;
            this.contenu = contenu;
        }
    
        public int getId()
        {
            return id; 
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
        
        public ImageView getImage() {
            return image;
        }

        public int getCol() {
            return col;
        }
        
        public int getRow() {
            return row;
        }

        public Integer getContenu() {
            return contenu;
        }

    
}
