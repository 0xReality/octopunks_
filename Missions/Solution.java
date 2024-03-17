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
    @SuppressWarnings("unused")
    private File file2;
    private String fileName;
    private String fileName2;
    private String s[];
    private String s2[];
    /* Permet de vérifier que le code rentrer par l'utilisateur est correct ou non ! */
    public Solution(int mission){
        this.mission = mission;
        this.fileName = "Missions/Solutions/solution" + mission + ".octo";
        this.fileName2 = "Missions/Solutions/solutionBis" + mission + ".octo";

        file = new File(fileName);
        s = loadFile(file).split("\n");

        if(mission == 3){
            file2 = new File(fileName2);
            s2 = loadFile(file).split("\n");
        }

       
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

    public Boolean CompareTo(String[] s2, String[] s3){
        if (Arrays.equals(s, s2) && Arrays.equals(this.s2,s3) ){
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