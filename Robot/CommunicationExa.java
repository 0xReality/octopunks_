package Robot;

public class CommunicationExa {
    
    public void sendMessage(EXA sender, EXA receiver, String message)
    {
        if(sender == null || receiver == null || message.isEmpty())
        {
            System.out.println("Erreur dans l'envoi du message !");
        }

        String new_message = messageGenerator(sender, message); 
        receiver.receiveMessage(new_message); 

    }

    public String messageGenerator(EXA sender, String message)
    {
        return sender.getName() + ':' + message; 
    }

    public String NextMessage(EXA exa){
        if(!exa.getMessage().isEmpty()){
            String tmp_message = exa.getMessage().poll();
            exa.setLastMessage(tmp_message);
            return tmp_message;  
        }
        return null; 
    }
}
