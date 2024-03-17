package Data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CompletedLevels {
    private ArrayList<Boolean> completedLevels;
    private static final String FILE_PATH = "Data/Saved/Levels.octo";

    public CompletedLevels(){
        this.completedLevels = new ArrayList<>();
        loadCompletedLevels();
    }

    private void loadCompletedLevels() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(": ");
                if (parts.length == 2) {
                    completedLevels.add("1".equals(parts[1]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setCompletedLevel(int level){
        if(level >= 3) return;
        this.completedLevels.set(level, true);
        saveCompletedLevels();
    }

    private void saveCompletedLevels() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (int i = 0; i < completedLevels.size(); i++) {
                bw.write("Level" + (i + 1) + ": " + (completedLevels.get(i) ? "1" : "0"));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isLevelCompleted(int level) {
        return level > 0 && level <= completedLevels.size() && completedLevels.get(level - 1);
    }
}
