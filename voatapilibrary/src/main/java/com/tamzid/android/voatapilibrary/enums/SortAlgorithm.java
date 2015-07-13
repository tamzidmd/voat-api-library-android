package com.tamzid.android.voatapilibrary.enums;

import com.google.gson.annotations.SerializedName;

/**
 * Specifies the sort algorithm to apply to result set.
 */
public enum SortAlgorithm {

    @SerializedName("0")
    New (0),

    @SerializedName("1")
    Top (1),

    @SerializedName("2")
    Hot (2),

    @SerializedName("3")
    Active (3),

    @SerializedName("4")
    Viewed (4),

    @SerializedName("5")
    Discussed (5),

    @SerializedName("6")
    Bottom (6);

    private final int value;
    public int getSortAlgorithm() {
        return this.value;
    }

    SortAlgorithm(int value) {
        this.value = value;
    }
}
