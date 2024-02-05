package UI.gameplay;
package UI; 

import java.util.ArrayList;
import java.util.Queue;
import java.util.Collections;

public class EXA extends NewExa {
    private ArrayList<ObjetOctopunk> inventaire; 
    private String name; /* Options qu'on pourrait rajouter qui serait + fun (personnnaliser son Exa) */
    private Queue<String> messageQueue; /* Les messages pour intéragir avec les deux EXA */
    private ArrayList<String> script; 
    private int memory; 

    /* Constructeur EXA */
    public EXA(int maxMemory, String name)
    {
        this.name = name; 
        memory = maxMemory; 
        script = new ArrayList<String>(); 
        inventaire = new ArrayList<ObjetOctopunk>(); 
    }

    /*Un inventaire ne peut contenir que 3 objets*/
    public String getInventaire()
    {
        StringBuilder tmp = new StringBuilder(); 
        for(ObjetOctopunk o : inventaire)
        {
            if(o != null)
            {
                tmp.append(o.toString()).append("\n"); 
            }
        }
        return tmp.toString(); 
    }

    public String getName(){
        return this.name;
    }

    public void addObjet(ObjetOctopunk obj)
    {
        inventaire.add(obj); 
        trierListe();
    }

    public void removeObjet(ObjetOctopunk obj){
        inventaire.remove(obj); 
        trierListe();
    }

    public void removeObjet(ObjetOctopunk obj, int x)
    {
        while(x > 0)
        {
            inventaire.remove(obj); 
            x--; 
        }
        trierListe();
    }

    /*La mémoire designe le nombre d'instructions maximale qu'un Exa peut supportés 
     * 
     * La fonction getMemory retourne le nombre maximale d'instructions que l'Exa peut supportés. 
    */
    public int getMemory()
    {
        return this.memory; 
    }

    /* Le script est une sauvegarde de toute les commandes entrée par l'utilisateur durant le niveau sans que celui ci ne dépasse 
        stockage de la mémoire de l'Exa.
        
        Retourne toute les commandes entrées par l'utilisateur. 
     */
    public String getScript(){
        StringBuilder scriptContent = new StringBuilder(); 
        for(String instruction : script)
        {
            if(instruction != null)
            {
                scriptContent.append(instruction).append("\n"); 
            }
        }
        return scriptContent.toString(); 
    }

    /* Trie l'inventaire de sorte à ce que les objets soit afficher par ordre alphabétique. */
    public void trierListe()
    {
        Collections.sort(inventaire, (obj1, obj2) -> obj1.getName().compareToIgnoreCase(obj2.getName())); 
    }

    

    
    
    




    
}