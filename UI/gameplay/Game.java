package UI.gameplay;

import Compilation.Compilator;
import Compilation.DoubleCompilator;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

public class Game extends AnchorPane {
    private int level;
    private NewExa exa = new NewExa();
    private SetButtons setButtons = new SetButtons();
    private Terminal terminal = new Terminal();
    //private ShowRegisters registers = new ShowRegisters(540.0, 600.0);
    //private ShowRegisters registers2 = new ShowRegisters(300.0, 600.0);
    private Compilator compilator; 
    private DoubleCompilator doubleCompilator; 

    public Game(int level) {
        this.level = level;
        drawLevel();

        this.getChildren().addAll(setButtons, terminal, exa);


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

    }

    public void drawLevel(){
        Label helloLabel = new Label("Level: " + level);
        helloLabel.setFont(new Font("Arial", 24));
        helloLabel.setLayoutX(1920 / 2 - 30);
        helloLabel.setLayoutY(10);

        this.getChildren().add(helloLabel);
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
                    compilator = new Compilator(exa1, terminal, exa.getRegisters1());   
                    compilator.compileAll();
                    return true;
                case 1:
                    if(compilator == null){
                        compilator = new Compilator(exa1, terminal, exa.getRegisters1());   
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
                    doubleCompilator = new DoubleCompilator(exa1, exa2, terminal,exa.getRegisters1(), exa.getRegisters2());   
                    doubleCompilator.compileAll();
                    return true;
                case 1:
                    if(doubleCompilator == null){
                        doubleCompilator = new DoubleCompilator(exa1, exa2, terminal, exa.getRegisters1(), exa.getRegisters2());   
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
