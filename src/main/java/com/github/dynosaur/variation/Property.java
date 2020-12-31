package com.github.dynosaur.variation;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

@JsonTypeInfo(use = Id.NAME, include = As.WRAPPER_OBJECT)
@JsonSubTypes({
    @Type(value = StandardProperty.class, name = "standardProperty"),
    @Type(value = Property.class, name = "basicProperty")
})
@JsonRootName("basicProperty")
public class Property {
    private double cost;
    private String name;

    public Property() { }

    public Property(String name) {
        this.name = name;
    }
}
