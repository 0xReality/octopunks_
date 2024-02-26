package Robot;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Objects; 

public class EXA {
    private static final int MAX_INVENTAIRE = 3; // limite d'objet dans l'inventaire. 
    private ArrayList<ObjetOctopunk> inventaire; 
    private String name; /* Options qu'on pourrait rajouter qui serait + fun (personnnaliser son Exa) */
    private Queue<String> messageQueue; /* Les messages pour intéragir avec les deux EXA */
    private ArrayList<String> script; 
    private int memory; 
    private int position; 
    private ObjetOctopunk Crypto; 
    private String lastMessage; 

    /* Constructeur EXA */
    public EXA(int maxMemory, String name)
    {
        this.name = name; 
        memory = maxMemory; 
        lastMessage = null; 
        script = new ArrayList<String>(); 
        inventaire = new ArrayList<ObjetOctopunk>(); 
        this.script = new ArrayList<>(); 
        this.messageQueue = new LinkedList<>(); 
    }

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

    public String getLastMessage(){
        return lastMessage; 
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

    public int getPosition()
    {
        return position; 
    }

    public void setPositon(int pos)
    {
        position = pos; 
    }

    public Queue<String> getMessage(){
        return messageQueue;
    }

    public void setLastMessage(String message){
        lastMessage = message; 
    }

    public void addObjet(ObjetOctopunk obj)
    {
        if(inventaire.size() < MAX_INVENTAIRE && obj != null)
        {
            inventaire.add(obj); 
            trierListe();
        }
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

    /* Trie l'inventaire de sorte à ce que les objets soit afficher par ordre alphabétique. */
    public void trierListe()
    {
        Collections.sort(inventaire, (obj1, obj2) -> obj1.getName().compareToIgnoreCase(obj2.getName())); 
    }


    public void addMessage(String message)
    {
        if(message != null)
        {
            messageQueue.add(message); 
        }
    }

    public void receiveMessage(String message)
    {
        messageQueue.add(message); 
    }

    public String readMessage(){
        return messageQueue.poll();
    }

    public boolean equals(Object o)
    {
        if(!(o instanceof EXA)){
            return false;
        }

        if(o == null ||  getClass() != o.getClass()){
            return false;
        }

        EXA exa = (EXA) o; 
        return Objects.equals(name,exa.name); 
    }

    public int hashCode()
    {
        return Objects.hash(name); 
    }

    public String toString(){
        return "EXA{" +
                "\n name : " + getName() +
                "\n memory : " + getMemory()+
                "\n position : " + getPosition() +
                "\n inventaire : " + getInventaire() +
                "\n portefeuille crypto : " + Crypto + "bitcoin" +
                "\n script = " + getScript() + 
                '}'; 
    }
    
}