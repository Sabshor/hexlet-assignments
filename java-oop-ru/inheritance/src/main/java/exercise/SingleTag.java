package exercise;

import java.util.Map;

// BEGIN
public class SingleTag extends Tag {
    private Map<String, String> attributes;

    public SingleTag(String tagName, Map<String, String> attributes) {
        super(tagName);
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        String attr = getAllAttributes(attributes);
        attr = attr.isEmpty() ? attr : " ".concat(attr);
        return String.format("<%s%s>", getName(), attr);
    }
}
// END
