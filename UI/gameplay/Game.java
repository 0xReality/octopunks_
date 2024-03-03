package UI.gameplay;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import Compilation.Compilator;
import Compilation.DoubleCompilator;
import Data.LevelData;
import UI.Loader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;

import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Game {

    //private Stage stage;
    private Scene sceneGame;

    private int level;
    private NewExa exa;
    private SetButtons setButtons = new SetButtons();

    private DoubleCompilator doubleCompilator; 
    private Terminal terminal;
    private Terminal helpTerminal;

    private ExaInfo exaInfo = new ExaInfo();
    private Loader loadMenu = new Loader("file:resources/editor/bg.png", "file:resources/editor/bg_panel.png"); 
    private LevelData data;
    private Compilator compilator;
    private AnchorPane root;
    private Clip clip; 

    public Game(int level,Stage stage) {

       // super(new AnchorPane(),800,600); 
        //this.mainMenu = MainMenu;
        //this.stage = stage;
        this.level = level;
        // this.root = (AnchorPane) this.getRoot();
        root = new AnchorPane();
        
        data = new LevelData(level);
        exa = new NewExa(data, exaInfo, setButtons);
        helpTerminal = new Terminal(855, 158, data);
        terminal = new Terminal(435, 158, null);
        
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


        root.getChildren().addAll( loadMenu, terminal, helpTerminal, setButtons, exa,/*exa,*/exaInfo );


        setButtons.getBtnRun().setOnAction(e -> {
            if (!exa.getTextAreaContainer().isEmpty()) {
                CodeArea codeArea1 = (CodeArea) exa.getTextAreaContainer().get(0);
                CodeArea codeArea2 = null;
                if (exa.getTextAreaContainer().size() > 1) {
                    codeArea2 = (CodeArea) exa.getTextAreaContainer().get(1);
                }
                if(callCompiler(codeArea1, codeArea2, 0)){
                    helpTerminal.remove();
                    helpTerminal.print("BRAVO TA REUSSI", "green");
                    clip.setMicrosecondPosition(0);
                    clip.start();
                    
                }
            }
        });



        setButtons.getBtnStep().setOnAction(e -> {
            if (!exa.getTextAreaContainer().isEmpty()) {
                CodeArea codeArea1 = (CodeArea) exa.getTextAreaContainer().get(0);
                CodeArea codeArea2 = null;
                if (exa.getTextAreaContainer().size() > 1) {
                    codeArea2 = (CodeArea) exa.getTextAreaContainer().get(1);
                }
                if(callCompiler(codeArea1, codeArea2, 1)){
                    helpTerminal.remove();
                    helpTerminal.print("BRAVO TA REUSSI", "green");
                    clip.setMicrosecondPosition(0);
                    clip.start();
                }
            }
        });

        setButtons.getBtnStop().setOnAction(e -> {
            exa.updateExaInfoSize();
            exaInfo.updateValues(null, 0, 0);
            terminal.remove();
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


        sceneGame = new Scene(root,1920,1080);
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
        if(ca2 == null){
            //il faut bloquer le code area quand le code est en compilation par pas
            switch (mode) {
                case 0:
                    compilator = new Compilator(exa1, terminal, exa.getRegisters1(), exaInfo, level);   
                    compilator.compileAll();
                    return compilator.correctAnswer(null);
                case 1:
                    if(compilator == null){
                        compilator = new Compilator(exa1, terminal, exa.getRegisters1(), exaInfo, level);   
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
                    doubleCompilator = new DoubleCompilator(exa1, exa2, terminal,exa.getRegisters1(), exa.getRegisters2(), exaInfo, level);   
                    doubleCompilator.compileAll();
                    return doubleCompilator.correctAnswer();
                case 1:
                    if(doubleCompilator == null){
                        doubleCompilator = new DoubleCompilator(exa1, exa2, terminal, exa.getRegisters1(), exa.getRegisters2(), exaInfo, level);   
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
}