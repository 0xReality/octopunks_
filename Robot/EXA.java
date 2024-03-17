package Robot;

import java.util.ArrayList;
import java.util.Queue;

import UI.gameplay.InitialisedGame;

import java.util.LinkedList;
import java.util.Objects; 

public class EXA {
    private Inventaire inventaire; 
    private String name; /* Options qu'on pourrait rajouter qui serait + fun (personnnaliser son Exa) */
    private Queue<String> messageQueue; /* Les messages pour intéragir avec les deux EXA */
    private ArrayList<String> script; 
    private int memory; 
    private int position; 
    private String lastMessage; 
    private boolean isActive = true; 
    private InitialisedGame game;

    /* Constructeur EXA */
    public EXA(int maxMemory, String name, InitialisedGame game)
    {  
        this.game = game;
        this.name = name; 
        memory = maxMemory; 
        lastMessage = null; 
        script = new ArrayList<String>(); 
        inventaire = new Inventaire(game);
        this.script = new ArrayList<>(); 
        this.messageQueue = new LinkedList<>(); 
    }

    public Inventaire getInventaire() {
        return inventaire;
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

    public void addObjet(ObjetOctoPunk nfile)
    {
        inventaire.ajouter(nfile);
    }

    public void removeObjet(ObjetOctoPunk obj){
        inventaire.retirer(obj);
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
                "\n script = " + getScript() + 
                '}'; 
    }

    public void setActive(boolean isActive){
        this.isActive = isActive; 
    }

    public boolean getActive(){
        return this.isActive; 
    }

    public InitialisedGame getGame() {
        return game;
    }
    
}