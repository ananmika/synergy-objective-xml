package model;

import util.FilterOperator;

import java.util.*;

/**
 * Created by anush.hakobyan on 3/29/2016.
 */
public class Expression {

    private Integer id;
    private Map<String, String> names = new HashMap<>();
    private String value;
    private List<Filter> filters = new ArrayList<>();
    private List<FilterOperator> filterOperators = new ArrayList<>();
    private SharingInformation sharingInfo;

    public Expression(Integer id) {
        this.id = id;
    }

    public Expression(Integer id, Map<String, String> names, String value, List<Filter> filters,
                      List<FilterOperator> filterOperators, SharingInformation sharingInfo) {
        this.id = id;
        this.names = names;
        this.value = value;
        this.filters = filters;
        this.filterOperators = filterOperators;
        this.sharingInfo = sharingInfo;
    }

    public Integer getId() {
        return id;
    }

    public Map<String, String> getNames() {
        return this.names;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value){
        this.value = value;
    }

    public List<Filter> getFilters() {
        return this.filters;
    }

    public void setFilter(Filter fitler) {
        this.filters.add(fitler);
    }

    public SharingInformation getSharingInfo() {
        return this.sharingInfo;
    }

    public void setSharingInfo(SharingInformation sharingInfo) {
        this.sharingInfo = sharingInfo;
    }

    public List<FilterOperator> getFilterOperators() {
        return this.filterOperators;
    }

    public void setName(String name) {
        for (Map.Entry<String, String> nameEntry : this.names.entrySet()) {
            if(nameEntry.getValue() == null) {
                nameEntry.setValue(name);
                break;
            }
        }
    }

    public void setName(String languageId, String name) {
        this.names.put(languageId, name);
    }

    public void setLanguageId(String languageId) {
        this.names.put(languageId, null);
    }

    public void setFilterOperator(FilterOperator operator) {
        this.filterOperators.add(operator);
    }

    @Override
    public boolean equals(Object object) {
        if(object == null) {
            return false;
        }

        if(!(object instanceof Expression)) {
            return false;
        }

        Expression expression = (Expression) object;

        return Objects.equals(this.id, expression.id)
                && Objects.equals(this.names, expression.names)
                && Objects.equals(this.value, expression.value)
                && Objects.equals(this.filters, expression.filters)
                && Objects.equals(this.filterOperators, expression.filterOperators)
                && Objects.equals(this.sharingInfo, expression.sharingInfo);
    }

    @Override
    public String toString() {
        String resultString = "Expression = ";
        resultString += "id = " + Objects.toString(this.id);
        resultString += ", names = ";
        for(Map.Entry<String, String> nameEntry: this.names.entrySet()) {
            resultString += nameEntry.getKey() + " = " + nameEntry.getValue() + ",";
        }
        resultString += "filters = ";
        for(Filter filter : this.filters) {
            resultString += "filter = " + Objects.toString(filter) + ",";
        }
        for(FilterOperator operator : this.filterOperators) {
            resultString += "filter = " + Objects.toString(operator) + ",";
        }
        resultString += "sharing information = " + Objects.toString(this.sharingInfo);
        return resultString;
    }
}
