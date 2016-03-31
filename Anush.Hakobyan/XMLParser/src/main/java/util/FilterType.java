package util;

/**
 * Created by anush.hakobyan on 3/29/2016.
 */
public enum FilterType {
    LIKE("LIKE"),
    IN("IN"),
    TOP("TOP"),
    STARTSWITH("STARTSWITH");

    private String id;

    private FilterType(String id) {
        this.id = id;
    }

    public static FilterType getFilterType(String id){
        for (FilterType type : FilterType.values()) {
            if (type.id.equals(id)) {
                return type;
            }
        }
        throw new IllegalArgumentException(String.format("FilterType with corrent id: %s not found", id));
    }
}
