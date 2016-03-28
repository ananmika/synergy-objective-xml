/**
 * Created by Mkrtich.Adamyan on 3/26/2016.
 */
public enum AnimalCategories {
    AMPHIBIAN("amphibian"),
    REPTYLE("reptile"),
    FISH("fish"),
    BIRD("bird"),
    INVERTEBRATE("invertebrate"),
    MAMMAL("amphibian");

    private String name;

    AnimalCategories(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
