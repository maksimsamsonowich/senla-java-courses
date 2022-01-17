package com.github.filter.fields;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.filter.fields.enums.SortDirection;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractSortDirection {

    @JsonProperty(value = "direction")
    private SortDirection sortDirection = SortDirection.ASCENDING;

    @JsonProperty(value = "isSorted")
    private Boolean isSorted = false;

}
