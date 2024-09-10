package exercise.daytime;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class Day implements Daytime {
    private String name = "day";

    public String getName() {
        return name;
    }

    // BEGIN
    @PostConstruct
    public void init() {
        System.out.println("Bean " + getName() + " is init");
    }

    @PreDestroy
    public void cleanup() {
        System.out.println("Bean " + getName() + " is Cleaning");
    }
    // END
}
