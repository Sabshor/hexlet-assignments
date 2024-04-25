package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
public class FileKV implements KeyValueStorage {
    private String pathToDatabase;
    private HashMap<String, String> databaseMap;

    public FileKV(String pathToDatabase, Map<String, String> database) {
        this.pathToDatabase = pathToDatabase;
        this.databaseMap = new HashMap<>(database);
        writeToFileDatabase();
    }

    private void writeToFileDatabase() {
        String content = Utils.serialize(databaseMap);
        Utils.writeFile(pathToDatabase, content);
    }

    private void readFromFileDatabase() {
        String content = Utils.readFile(pathToDatabase);
        databaseMap = (HashMap<String, String>) Utils.unserialize(content);
    }

    @Override
    public void set(String key, String value) {
        this.databaseMap.put(key, value);
        writeToFileDatabase();
    }

    @Override
    public void unset(String key) {
        this.databaseMap.remove(key);
        writeToFileDatabase();
    }

    @Override
    public String get(String key, String defaultValue) {
        readFromFileDatabase();
        return this.databaseMap.getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        readFromFileDatabase();
        return this.databaseMap;
    }
}
// END
