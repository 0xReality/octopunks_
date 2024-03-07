package Fonctions.MetaFichier;

import java.util.ArrayList;
import java.util.List;

public class File {
    private int id;
    private List<Integer> content;
    boolean isInHand;

    public File(int id, boolean isInHand) {
        this.id = id;
        this.content = new ArrayList<>();
        this.isInHand = isInHand;
    }

    public void addContent(int data) {
        content.add(data);
    }

    public int getId() {
        return id;
    }

    public List<Integer> getContent() {
        return new ArrayList<>(content);
    }

    public boolean isInHand() {
        return isInHand;
    }
}
