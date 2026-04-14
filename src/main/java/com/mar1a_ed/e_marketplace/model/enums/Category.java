package com.mar1a_ed.e_marketplace.model.enums;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;

public enum Category {

    FOOD,
    BEAUTY,
    ELECTRONICS,
    CLEANING,

    @JsonEnumDefaultValue
    UNKNOWN
}
