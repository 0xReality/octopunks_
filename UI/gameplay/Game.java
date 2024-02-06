package UI.gameplay;

import Compilation.Compilator;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

public class Game extends AnchorPane {
    private int level;
    private NewExa exa = new NewExa();
    private SetButtons setButtons = new SetButtons();
    private Terminal terminal = new Terminal();
    private ShowRegisters registers = new ShowRegisters();
    private Compilator compilator; 

    public Game(int level) {
        this.level = level;
        drawLevel();

        this.getChildren().addAll(setButtons, registers, terminal, exa);


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

        String separator = "\n-----\n";
        String exa1 = ca1.getTextArea().getText() + separator;
        String exa2 = (ca2 != null) ? ca2.getTextArea().getText() : "";
        
        String code = exa1 + exa2;
        switch (mode) {
            case 0:
                compilator = new Compilator(code, terminal, registers);   
                compilator.compileAll();
                return true;
            case 1:
                if(compilator == null){
                    compilator = new Compilator(code, terminal, registers);   
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

        return true;
    }
}
