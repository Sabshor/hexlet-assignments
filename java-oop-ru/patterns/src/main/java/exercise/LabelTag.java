package exercise;

// BEGIN
public class LabelTag implements TagInterface {
    private String text;
    private TagInterface tag;

    public LabelTag(String text, TagInterface tag) {
        this.text = text;
        this.tag = tag;
    }

    @Override
    public String render() {
        return String.format("<label>%s%s</label>", text, tag.render());
    }
}
// END
