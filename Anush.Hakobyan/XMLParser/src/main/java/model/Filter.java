package model;

import util.FilterType;

import java.util.List;
import java.util.Objects;

/**
 * Created by anush.hakobyan on 3/29/2016.
 */
public class Filter {
    private FilterType type;
    private Integer categoryId;
    private List<String> values;

    public Filter() {}

    public Filter( FilterType type, Integer categoryId, List<String> values) {
        this.type = type;
        this.categoryId = categoryId;
        this.values = values;
    }

    public FilterType getType() {
        return type;
    }

    public void setType(FilterType type) {
        this.type = type;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

    public List<String> getValues() {
        return values;
    }

    @Override
    public boolean equals(Object object) {
        if(object == null) {
            return false;
        }

        if(!(object instanceof Filter)) {
            return false;
        }

        Filter filter = (Filter) object;

        return Objects.equals(this.type, filter.type)
                && Objects.equals(this.categoryId, filter.categoryId)
                &&  Objects.equals(this.values, filter.values);
    }

    @Override
    public String toString() {
        String resultString = "";
        resultString += "type = " + Objects.toString(this.type) + ",";
        resultString += "categoryId = " + Objects.toString(this.categoryId) + ",";
        resultString += "values = ";
        for(String value : this.values){
            resultString += value;
        }
        return resultString;
    }

}
