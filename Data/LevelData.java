package Data;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class LevelData {
    private int level;
    private String[] savedCode;
    private String[] savedCode2;
    private String name;
    private String name2;
    private String[] missionInfo;

    public LevelData(int level) {
        this.level = level;
        savedCode = new String[50]; 
        savedCode2 = new String[50]; 
        missionInfo = new String[50];
        this.name =  "Data/Saved/savedLevel" + level + "code.octo";
        this.name2 =  "Data/Saved/savedLevelBis" + level + "code.octo";
        handleFile();
    }

    private void handleFile() {
        File file = new File(name);
        try {
            if (!file.exists()) {
                file.createNewFile();
            } else {
                List<String> lines = Files.readAllLines(Paths.get(file.getPath()));
                savedCode = lines.toArray(new String[0]);
            }
        } catch (IOException e) {
            System.err.println("INFO: cannot save level data");
        }

        File file2 = new File(name2);
        try {
            if (!file2.exists()) {
                file2.createNewFile();
            } else {
                List<String> lines = Files.readAllLines(Paths.get(file2.getPath()));
                savedCode2 = lines.toArray(new String[0]);
            }
        } catch (IOException e) {
            System.err.println("INFO: cannot save level data");
        } 



        File mFile = new File("Missions/mission"+ level +".octo");
        try{
            if(!file.exists()) System.out.println("didnt find file");
            List<String> lines = Files.readAllLines(Paths.get(mFile.getPath()));
            missionInfo = lines.toArray(new String[0]);
        }catch(IOException e){
            System.out.println("error");
        }
    }


    public void setSavedCode(String[] savedCode, int x) {
        this.savedCode = savedCode;
        writeToFile(savedCode, (x == 1 ? this.name : this.name2));
    }

    public void writeToFile(String[] text, String name) {
        try (FileWriter writer = new FileWriter(name)) {
            for (String string : text) {
                writer.write(string);
                writer.write("\n");
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String[] getSavedCode() {
        return savedCode;
    }

    public String[] getSavedCode2() {
        return savedCode2;
    }

    public String[] getMissionInfo() {
        return missionInfo;
    }

    public int getLevel() {
        return level;
    }
}
