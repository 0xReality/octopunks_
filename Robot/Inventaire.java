package Robot;


import java.util.HashMap;
import java.util.Map;

import UI.gameplay.InitialisedGame;

public class Inventaire {

    private Map<Integer, Object> objets;
    private int nextId;
    private InitialisedGame game;

    public Inventaire(InitialisedGame game) {
        this.game = game;
        objets = new HashMap<>();
        nextId = 0;
    }

    
    public int ajouter(Object objet) {
        if (objet != null) {
            int id = nextId++;
            objets.put(id, objet);

            game.supprimerObjetDeLaGrille((ObjetOctoPunk) objet);

            return id;
        }
        return -1;
    }

    public Object retirer(Object object) {
        objets.remove(object);
        ObjetOctoPunk o = (ObjetOctoPunk) object;
        game.ajouterObjetALaGrille(o, o.getCol(), o.getRow());
        return true;
    }


    public Object getObjet(int id) {
        return objets.get(id);
    }
    
    public boolean contientObjet(int id) {
        return objets.containsKey(id);
    }

    public int tailleInventaire() {
        return objets.size();
    }
}
