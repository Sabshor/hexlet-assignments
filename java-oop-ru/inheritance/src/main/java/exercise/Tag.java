package exercise;

import java.util.stream.Collectors;
import java.util.Map;

// BEGIN
public abstract class Tag {
    private String name;

    public Tag(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getAllAttributes(Map<String, String> attributes) {
        return attributes.entrySet().stream()
                .map(element -> String.format("%s=\"%s\"", element.getKey(), element.getValue()))
                .collect(Collectors.joining(" "));
    }

    public abstract String toString();
}
// END
