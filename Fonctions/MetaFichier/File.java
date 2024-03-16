package Fonctions.MetaFichier;

public class File {
    private int id;
    private int content;

    public File(int id, int content) {
        this.id = id;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public int getContent() {
        return this.content;
    }

}
