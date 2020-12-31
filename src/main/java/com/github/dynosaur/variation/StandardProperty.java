package com.github.dynosaur.variation;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("standardProperty")
public class StandardProperty extends Property {

    double rent, rentHouse1, rentHouse2, rentHouse3, rentHouse4, rentHotel;

    public StandardProperty() {
        super();
    }

    public StandardProperty(String name) {
        super(name);
    }
}
