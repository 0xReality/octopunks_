package UI.gameplay;

import Compilation.Compilator;
import UI.Loader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

public class Game extends AnchorPane {
    private int level;
    private NewExa exa = new NewExa();
    private SetButtons setButtons = new SetButtons();
    private Terminal terminal = new Terminal();
    private ShowRegisters registers = new ShowRegisters();
    private Loader loadMenu = new Loader("file:resources/editor/bg.png", "file:resources/editor/bg_panel.png"); 

    @SuppressWarnings("unused")
    private Compilator compilator;

    public Game(int level) {
        this.level = level;
        drawLevel();

        this.getChildren().addAll(terminal, loadMenu, setButtons, registers, exa );


        setButtons.getBtnRun().setOnAction(e -> {
            if (!exa.getTextAreaContainer().getChildren().isEmpty()) {
                CodeArea codeArea1 = (CodeArea) exa.getTextAreaContainer().getChildren().get(0);
                CodeArea codeArea2 = null;
                if (exa.getTextAreaContainer().getChildren().size() > 1) {
                    codeArea2 = (CodeArea) exa.getTextAreaContainer().getChildren().get(1);
                }
                callCompiler(codeArea1, codeArea2);
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

    public boolean callCompiler(CodeArea ca1, CodeArea ca2){
        if(ca1 == null) return false;
        String exa1 = ca1.getTextArea().getText();
        String exa2 = "";
        if(ca2 != null)  exa2 = ca2.getTextArea().getText();
        /*if(exa1 != null && exa2 != null){
            //compilator = new Compilator(exa1, exa2);
            throw new UnsupportedOperationException("Not supported yet.");
        }*/
        compilator = new Compilator(exa1, terminal, registers);
        return true;
    }
}
