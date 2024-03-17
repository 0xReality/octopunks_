package Robot;


import java.util.HashMap;
import java.util.Map;

import UI.gameplay.InitialisedGame;

public class Inventaire {

    private Map<Integer, ObjetOctoPunk> objets;
    private int nextId;
    private InitialisedGame game;

    public Inventaire(InitialisedGame game) {
        this.game = game;
        objets = new HashMap<>();
        nextId = 0;
    }

    
    public int ajouter(ObjetOctoPunk objet) {
        if (objet != null) {
            int id = nextId++;
            objets.put(id, objet);

            game.supprimerObjetDeLaGrille((ObjetOctoPunk) objet);

            return id;
        }
        return -1;
    }

    public Object retirer(Object object, int x, int y) {
        objets.remove(object);
        ObjetOctoPunk o = (ObjetOctoPunk) object;
        game.ajouterObjetALaGrille(o, x, y, null);
        return true;
    }

    public void afficherInventaire() {
        if (objets.isEmpty()) {
            System.out.println("L'inventaire est vide.");
        } else {
            System.out.println("Contenu de l'inventaire:");
            for (Map.Entry<Integer, ObjetOctoPunk> entry : objets.entrySet()) {
                System.out.println("ID: " + entry.getKey() + ", Objet: " + entry.getValue().getName());
            }
        }
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
