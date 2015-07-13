package com.tamzid.android.voatapilibrary.enums;

import com.google.gson.annotations.SerializedName;

/**
 * Specifies the direction result sets should be sorted.
 */
public enum SortDirection {

    @SerializedName("0")
    Default (0),

    @SerializedName("1")
    Reverse (1);

    private final int value;
    public int getSortDirection() {
        return this.value;
    }

    SortDirection(int value) {
        this.value = value;
    }

}
