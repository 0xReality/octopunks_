package UI;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class OctoPunksMenu extends Application {
    
    private Stage stage;
    private Menu mainScene;

    private static Clip bgClip; 

    @Override
    public void start(Stage OctoPunks) {
       
        stage = OctoPunks;
        stage.setTitle("OctoPunks");
        //load l'icone du jeu
        stage.getIcons().add(new Image("file:resources/icon.png"));
        //mettre le jeu en plein écran

        stage.setFullScreen(true);
        stage.setFullScreenExitHint("");
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        stage.initStyle(StageStyle.UNDECORATED);

        mainScene = new Menu(stage);
        PlayMenuMusic();

        stage.setScene(mainScene.getMainScene());
        stage.show();
    }

    public static void main(String[] args) {
        launch(args); 
    }

    public Menu getSceneMain(){
        return mainScene; 
    }

    public static void PlayMenuMusic(){
        try{
            File music = new File("resources/sounds/Octo_Menu.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(music); 
            bgClip = AudioSystem.getClip(); 
            bgClip.open(audioStream);
            bgClip.start();
            bgClip.loop(Clip.LOOP_CONTINUOUSLY);
        }
        catch(UnsupportedAudioFileException | IOException | LineUnavailableException e){
            e.printStackTrace(); 
        }
    }

    public static void stopMenuMusic(){
        if(bgClip != null && bgClip.isRunning()){
            bgClip.stop();
            bgClip.close();
        }
    }
}
