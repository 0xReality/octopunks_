package Missions;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;

public class Solution {
    @SuppressWarnings("unused")
    private int mission;
    private File file;
    private String fileName;
    private String s[];
    /* Permet de vérifier que le code rentrer par l'utilisateur est correct ou non ! */
    public Solution(int mission){
        this.mission = mission;
        this.fileName = "Missions/Solutions/solution" + mission + ".octo";
        file = new File(fileName);
        s = loadFile(file).split("\n");
    }

    public String getFileName() {
        return fileName;
    }

    public File getFile() {
        return file;
    }
  
    public Boolean CompareTo(String[] s2){
        if (Arrays.equals(s, s2)){
            return true;
        }
        return false;
    }



     public static String loadFile(File f) {
        try {
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(f));
            StringWriter out = new StringWriter();
            int b;
            while ((b=in.read()) != -1)
                out.write(b);
            out.flush();
            out.close();
            in.close();
            return out.toString();
        }
        catch (IOException ie)
        {
            ie.printStackTrace(); 
        }
        return null;
    }
}

