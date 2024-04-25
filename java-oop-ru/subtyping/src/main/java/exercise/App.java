package exercise;

// BEGIN
public class App {
   // public static void main(String[] args) {
        /*KeyValueStorage storage = new InMemoryKV(Map.of("key", "value", "key2", "value2"));
        App.swapKeyValue(storage);
        storage.get("key", "default"); // "default"
        storage.get("value", "default"); // "key"

        System.out.println(storage.toMap()); // => {value=key, value2=key2}
        */
/*
        KeyValueStorage storageDisc = new FileKV("src/test/resources/fileTemp", Map.of("key", "value"));
        App.swapKeyValue(storageDisc);
        // Получение значения по ключу
        storageDisc.get("key", "default"); // "value"
        System.out.println(storageDisc.toMap()); // => {value=key}
    }*/

    public static void swapKeyValue(KeyValueStorage database) {
        KeyValueStorage dbTemporary = new InMemoryKV(database.toMap());
        dbTemporary.toMap().forEach((key, value) -> {
            database.unset(key);
            database.set(value, key);
        });
    }
}
// END
