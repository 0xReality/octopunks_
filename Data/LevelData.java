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
    private String[] missionInfo;

    public LevelData(int level) {
        this.level = level;
        savedCode = new String[50]; 
        missionInfo = new String[50]; 
        handleFile();
    }

    private void handleFile() {
        File file = new File("Data/savedLevel" + level + "code.octo");
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

        File mFile = new File("Missions/mission"+ level +".octo");
        try{
            if(!file.exists()) System.out.println("didnt find file");
            List<String> lines = Files.readAllLines(Paths.get(mFile.getPath()));
            missionInfo = lines.toArray(new String[0]);
        }catch(IOException e){
            System.out.println("error");
        }
    }

    public void setSavedCode(String[] savedCode) {
        this.savedCode = savedCode;
        writeToFile(savedCode);
    }

    public void writeToFile(String[] text) {
        try (FileWriter writer = new FileWriter("Data/savedLevel" + level + "code.octo")) {
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

    public String[] getMissionInfo() {
        return missionInfo;
    }
}
