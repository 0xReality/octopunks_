package UI.gameplay;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import Compilation.Compilator;
import Compilation.DoubleCompilator;
import Data.CompletedLevels;
import Data.LevelData;
import UI.Loader;
import UI.OctoPunksMenu;
import UI.SceneSwitch;
import UI.ShowsLevels;
import UI.Utils;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import Robot.*;


public class Game {

    private Stage stage;
    private Scene sceneGame;


    private int level;
    private NewExa exa;
    private SetButtons setButtons = new SetButtons();

    private boolean isMusicStopped = false;

    private Terminal terminal;
    private Terminal helpTerminal;

    private ExaInfo exaInfo = new ExaInfo();
    private Loader loadMenu = new Loader("file:resources/editor/bg.png", "file:resources/editor/bg_panel.png"); 

    private LevelData data;

    private Compilator compilator;
    private DoubleCompilator doubleCompilator; 

    private AnchorPane root;


    private Clip clip; 
    private Clip bgClip; 

    private int currentTrackNumber = 0;
    private File[] musicTracks = {
        new File("resources/sounds/octo1.wav"),
        new File("resources/sounds/octo2.wav"),
        new File("resources/sounds/octo3.wav"),
        new File("resources/sounds/octo4.wav"),
    };

    private InitialisedGame game; 

    // private Image image = new Image("file:resources/cursor/cursor.png");
    // private ImageCursor cursor = new ImageCursor(image,76.5,76.5);

    public Game(int level,Stage stage) {

       // super(new AnchorPane(),800,600); 
        //this.mainMenu = MainMenu;
        
        this.stage = stage;
        this.level = level;
        // this.root = (AnchorPane) this.getRoot();
        root = new AnchorPane();
        OctoPunksMenu.stopMenuMusic();
        startMusicLoop();
        
        
        
        data = new LevelData(level);
        exa = new NewExa(data, exaInfo, setButtons);
        helpTerminal = new Terminal(855, 158, data);
        terminal = new Terminal(435, 158, null);

  

        //initialisation grille de jeu 
        game = new InitialisedGame(stage); 
        game.InitializeGameGrille(); 
        // game.positionGameGrille(); 
        // root.getChildren().add(game.getGrille()); 


        try {
            File audioFile = new File("resources/sounds/fanfare_solving1.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            clip = AudioSystem.getClip();
            clip.open(audioStream);


            

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }

        



        drawLevel();

       // exa.getTextAreaContainer().getChildren().get(0);
        exa.getTextAreaContainer().get(0);
    //    List<CodeArea> allCodeAreas = exa.getTextAreaContainer();
    //     if (!allCodeAreas.isEmpty()) {
    //         CodeArea firstCodeArea = allCodeAreas.get(0);
    //     }
        AnchorPane.setLeftAnchor(exa,30.0);
        AnchorPane.setBottomAnchor(terminal,70.0);
        AnchorPane.setRightAnchor(terminal,60.0);

        AnchorPane.setBottomAnchor(helpTerminal,70.0);
        AnchorPane.setLeftAnchor(helpTerminal, 564.0);


        AnchorPane.setBottomAnchor(exaInfo, 245.0);
        AnchorPane.setRightAnchor(exaInfo, 350.0);


        root.getChildren().addAll( loadMenu, terminal, helpTerminal, setButtons, exa,/*exa,*/exaInfo,game.getGrille());

        // victoire mission réussi 
        setButtons.getBtnRun().setOnAction(e -> {
            if (!exa.getTextAreaContainer().isEmpty()) {
                CodeArea codeArea1 = (CodeArea) exa.getTextAreaContainer().get(0);
                CodeArea codeArea2 = null;
                if (exa.getTextAreaContainer().size() > 1) {
                    codeArea2 = (CodeArea) exa.getTextAreaContainer().get(1);
                }
                if(callCompiler(codeArea1, codeArea2, 0)){
                    stopBackgroundMusic();
                    CompletedLevels cl = new CompletedLevels();
                    cl.setCompletedLevel(level);
                    helpTerminal.remove();   
                    helpTerminal.print("BRAVO TA REUSSI", "green");
                    clip.setMicrosecondPosition(0);
                    clip.start();
                    popUpWin();
                }
            }
        });


        // victoire mission réussi 
        setButtons.getBtnStep().setOnAction(e -> {
            if (!exa.getTextAreaContainer().isEmpty()) {
                CodeArea codeArea1 = (CodeArea) exa.getTextAreaContainer().get(0);
                CodeArea codeArea2 = null;
                if (exa.getTextAreaContainer().size() > 1) {
                    codeArea2 = (CodeArea) exa.getTextAreaContainer().get(1);
                }
                if(callCompiler(codeArea1, codeArea2, 1)){
                    stopBackgroundMusic();
                    CompletedLevels cl = new CompletedLevels();
                    cl.setCompletedLevel(level);
                    helpTerminal.remove();
                    helpTerminal.print("BRAVO TA REUSSI", "green");
                    clip.setMicrosecondPosition(0);
                    clip.start();                                               // LANCEMENT DE LA MUSIQUE 
                    popUpWin();                                                 // POPUP FELICITATIONS DE FIN DE MISSIONS
                }
            }
        });

        setButtons.getBtnStop().setOnAction(e -> {
            exa.updateExaInfoSize();
            exaInfo.updateValues(null, 0, 0);
            terminal.remove();
        });

        setButtons.getBtnExit().setOnAction(e -> {
            stopBackgroundMusic();
            OctoPunksMenu.PlayMenuMusic();
            ShowsLevels a = new ShowsLevels(stage); 
            new SceneSwitch(stage, a.getScene2()); 
        });


        root.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.F4) {
                setButtons.getBtnRun().fire();
            } else if (event.getCode() == KeyCode.F5) {
                setButtons.getBtnStep().fire();
            } else if (event.getCode() == KeyCode.ESCAPE) {
                setButtons.getBtnStop().fire();
            }
        });

        Utils.setCustomCursor(root);
        sceneGame = new Scene(root,1920,1080);
        Utils.setCustomCursor(sceneGame);


       
    }

    public void drawLevel(){
        Label helloLabel = new Label("Level: " + level);
        helloLabel.setFont(new Font("Arial", 24));
        helloLabel.setLayoutX(1920 / 2 - 30);
        helloLabel.setLayoutY(10);

        root.getChildren().add(helloLabel);
    }

    /*
     * 0 pour compiler tout et 1 pour
     */
    public boolean callCompiler(CodeArea ca1, CodeArea ca2, int mode){
        if(ca1 == null) return false;
        boolean returnCall;

        String exa1 = ca1.getTextArea().getText();
        EXA exaInstance = new EXA(0, "exa1", game);


        if(ca2 == null){
            //il faut bloquer le code area quand le code est en compilation par pas
            switch (mode) {
                case 0:
                    compilator = new Compilator(exa1, terminal, exa.getRegisters1(), exaInfo, level,game,exaInstance);   
                    compilator.compileAll();
                    return compilator.correctAnswer(null);
                case 1:
                    if(compilator == null){
                        compilator = new Compilator(exa1, terminal, exa.getRegisters1(), exaInfo, level,game,exaInstance);   
                        if(compilator.compileNextLine() == 1){
                            returnCall = compilator.correctAnswer(null);
                            compilator = null;
                            return returnCall;
                        }
                        return false;
                    }else{
                        if(compilator.compileNextLine() == 1){
                            returnCall = compilator.correctAnswer(null);
                            compilator = null;
                            return returnCall;
                        }
                        return false;
                    }
            }
    
        }else{
            String exa2 = ca2.getTextArea().getText();
            switch (mode) {
                case 0:
                    doubleCompilator = new DoubleCompilator(exa1, exa2, terminal,exa.getRegisters1(), exa.getRegisters2(), exaInfo, level,game,exaInstance);   
                    doubleCompilator.compileAll();
                    return doubleCompilator.correctAnswer();
                case 1:
                    if(doubleCompilator == null){
                        doubleCompilator = new DoubleCompilator(exa1, exa2, terminal, exa.getRegisters1(), exa.getRegisters2(), exaInfo, level,game,exaInstance);   
                        if(doubleCompilator.compileNextLine() == 1){
                            returnCall = doubleCompilator.correctAnswer();
                            doubleCompilator = null;
                            return returnCall;
                        }
                        return false;
                    }else{
                        if(doubleCompilator.compileNextLine() == 1){
                            returnCall = doubleCompilator.correctAnswer();
                            doubleCompilator = null;
                            return returnCall;
                        }
                        return false;
                    }
            }
        }
       
        return false;
    }

    public Scene getSceneGame() {
        return sceneGame;
    }
    public SetButtons getSetButtons() {
        return setButtons;
    }

    private void popUpWin(){

        Stage popup = new Stage();
        
        //popup.initModality(Modality.APPLICATION_MODAL);
        //popup.setTitle("FÉLICITATIONS !");
        //popup.initStyle(StageStyle.UNDECORATED);

        Label label1 = new Label("FÉLICITATIONS !");
        label1.setStyle(
                    "-fx-text-fill: limegreen; " +
                    "-fx-font-weight: bold;"+
                    "-fx-font-size: 40px; " 
                    
                    );

        VBox textExaInfo = new VBox();
        Label cycle = new Label("Cycles : "+ exaInfo.getCycles());
        Label size = new Label("Size : "+ exaInfo.getSize()+" / 50");
        Label activity = new Label("Activity : " + exaInfo.getActivity());

        cycle.setStyle("-fx-text-fill: white; "+
                        "-fx-font-size: 16px; " );
        size.setStyle("-fx-text-fill: white; "+
                        "-fx-font-size: 16px; " );
        activity.setStyle("-fx-text-fill: white; "+
                        "-fx-font-size: 16px; " );
        textExaInfo.setAlignment(Pos.CENTER);
        textExaInfo.getChildren().addAll(cycle,size,activity);

        Text label = new Text("Bien jouer !");
        Font font = Font.font("Verdana", FontWeight.BOLD,30);
        label.setFont(font);
        label.setStyle("-fx-text-fill: white; ");
        label.setFill(Color.WHITE);
        

        Button closeButton = new Button("Fermer"); 
        closeButton.setOnAction(e->{popup.close();
            clip.stop();
            bgClip = null;
            ShowsLevels a = new ShowsLevels(stage); 
            new SceneSwitch(stage, a.getScene2()); 
        });

        
        
        VBox layout = new VBox(20); 
        layout.getChildren().addAll(label1,textExaInfo,label,closeButton); 
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: rgba(10,10,10);"
                        + "-fx-background-radius: 25;"
                     /*    + "-fx-border-style: solid inside;"*/
                        + "-fx-border-width: 2;"
                        + "-fx-border-insets: 5;"
                        + "-fx-border-radius: 20;"
                        + "-fx-border-color: white;" );
        // Utils.applyImageCursor(layout,cursor);
        Scene popupScene = new Scene(layout, 550,450);
        popup.initStyle(StageStyle.TRANSPARENT);
        popupScene.setFill(Color.TRANSPARENT);
        
        popup.setScene(popupScene);
        popup.showAndWait();

    }


    public void playBackgroundMusic(File musicFile) {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicFile);
            bgClip = AudioSystem.getClip();
            bgClip.open(audioStream);

            FloatControl gainControl = (FloatControl) bgClip.getControl(FloatControl.Type.MASTER_GAIN);
            float dB = (float) (Math.log(0.2) / Math.log(10.0) * 20.0);
            gainControl.setValue(dB);

            bgClip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP && !isMusicStopped) {
                    playNextTrack();
                }
            });
            

            bgClip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void stopBackgroundMusic() {
        if (bgClip != null) {
            isMusicStopped = true; 
            bgClip.stop();          
            bgClip.close();        
            bgClip = null;          
        }
    }
    

    public void playNextTrack() {
        currentTrackNumber = (currentTrackNumber + 1) % musicTracks.length;
        playBackgroundMusic(musicTracks[currentTrackNumber]);
    }

    public void startMusicLoop() {
        playNextTrack(); 
    }

}