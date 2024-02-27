package UI.gameplay;

import Compilation.Compilator;
import Compilation.DoubleCompilator;
import Data.LevelData;
import UI.Loader;
import UI.Menu;
import UI.SceneSwitch;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

public class Game extends Scene {
    private int level;
    private NewExa exa;
    private SetButtons setButtons = new SetButtons();

    private DoubleCompilator doubleCompilator; 
    private Terminal terminal;
    private Terminal helpTerminal;
    private Menu mainMenu;
    private ExaInfo exaInfo = new ExaInfo();
    private Loader loadMenu = new Loader("file:resources/editor/bg.png", "file:resources/editor/bg_panel.png"); 

    private Compilator compilator;
    private AnchorPane root; 

    public Game(int level, Menu MainMenu) {
        super(new AnchorPane(),800,600); 
        this.mainMenu = MainMenu;
        this.level = level;
        this.root = (AnchorPane) this.getRoot();
        drawLevel();
        LevelData data = new LevelData(level);
        exa = new NewExa(data, exaInfo);
        helpTerminal = new Terminal(855, 158, data);
        terminal = new Terminal(435, 158, null);

        exa.getTextAreaContainer().getChildren().get(0);
        AnchorPane.setBottomAnchor(terminal,70.0);
        AnchorPane.setRightAnchor(terminal,60.0);

        AnchorPane.setBottomAnchor(helpTerminal,70.0);
        AnchorPane.setLeftAnchor(helpTerminal, 564.0);


        AnchorPane.setBottomAnchor(exaInfo, 245.0);
        AnchorPane.setRightAnchor(exaInfo, 350.0);


        root.getChildren().addAll( loadMenu, terminal, helpTerminal, setButtons, exa,exaInfo );


        setButtons.getBtnRun().setOnAction(e -> {
            if (!exa.getTextAreaContainer().getChildren().isEmpty()) {
                CodeArea codeArea1 = (CodeArea) exa.getTextAreaContainer().getChildren().get(0);
                CodeArea codeArea2 = null;
                if (exa.getTextAreaContainer().getChildren().size() > 1) {
                    codeArea2 = (CodeArea) exa.getTextAreaContainer().getChildren().get(1);
                }
                callCompiler(codeArea1, codeArea2, 0);
            }
        });

        setButtons.getBtnStep().setOnAction(e -> {
            if (!exa.getTextAreaContainer().getChildren().isEmpty()) {
                CodeArea codeArea1 = (CodeArea) exa.getTextAreaContainer().getChildren().get(0);
                CodeArea codeArea2 = null;
                if (exa.getTextAreaContainer().getChildren().size() > 1) {
                    codeArea2 = (CodeArea) exa.getTextAreaContainer().getChildren().get(1);
                }
                callCompiler(codeArea1, codeArea2, 1);
            }
        });

        setButtons.getBtnExit().setOnAction(e ->{
            new SceneSwitch(mainMenu.getStage(), mainMenu); 
            mainMenu.getStage().setFullScreen(true);
        });

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

        String exa1 = ca1.getTextArea().getText();
        if(ca2 == null){
            //il faut bloquer le code area quand le code est en compilation par pas
            switch (mode) {
                case 0:
                    compilator = new Compilator(exa1, terminal, exa.getRegisters1(), exaInfo);   
                    compilator.compileAll();
                    return true;
                case 1:
                    if(compilator == null){
                        compilator = new Compilator(exa1, terminal, exa.getRegisters1(), exaInfo);   
                        if(compilator.compileNextLine() == 1){
                            compilator = null;
                        }
                        return true;
                    }else{
                        if(compilator.compileNextLine() == 1){
                            compilator = null;
                        }
                        return true;
                    }
            }
    
        }else{
            String exa2 = ca2.getTextArea().getText();
            switch (mode) {
                case 0:
                    doubleCompilator = new DoubleCompilator(exa1, exa2, terminal,exa.getRegisters1(), exa.getRegisters2(), exaInfo);   
                    doubleCompilator.compileAll();
                    return true;
                case 1:
                    if(doubleCompilator == null){
                        doubleCompilator = new DoubleCompilator(exa1, exa2, terminal, exa.getRegisters1(), exa.getRegisters2(), exaInfo);   
                        if(doubleCompilator.compileNextLine() == 1){
                            doubleCompilator = null;
                        }
                        return true;
                    }else{
                        if(doubleCompilator.compileNextLine() == 1){
                            doubleCompilator = null;
                        }
                        return true;
                    }
            }
        }
        
        
       
        return true;
    }
}
