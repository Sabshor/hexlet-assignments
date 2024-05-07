package exercise;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class PairedTag extends Tag {
    private Map<String, String> attributes;
    private String tagText;
    private List<Tag> singleTags;

    public PairedTag(String tagName, Map<String, String> attributes, String tagText, List<Tag> singleTags) {
        super(tagName);
        this.attributes = attributes;
        this.tagText = tagText;
        this.singleTags = singleTags;
    }


    @Override
    public String toString() {
        String attributesChild = singleTags.stream()
                .map(Tag :: toString)
                .collect(Collectors.joining(""));
        String attr = getAllAttributes(attributes);
        attr = attr.isEmpty() ? attr : " ".concat(attr);
        return String.format("<%s%s>%s%s</%s>", super.getName(), attr, tagText, attributesChild, super.getName());
    }
}
// END
