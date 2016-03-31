package util;

/**
 * Created by anush.hakobyan on 3/29/2016.
 */
public enum FilterOperator {
    AND("AND"),
    OR("OR");

    private String id;

    private FilterOperator(String id) {
        this.id = id;
    }

    public static FilterOperator getFilterOperator(String id){
        for (FilterOperator type : FilterOperator.values()) {
            if (type.id.equals(id)) {
                return type;
            }
        }
        throw new IllegalArgumentException(String.format("FilterType with corrent id: %s not found", id));
    }
}
