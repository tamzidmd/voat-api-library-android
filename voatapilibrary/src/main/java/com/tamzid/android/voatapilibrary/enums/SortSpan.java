package com.tamzid.android.voatapilibrary.enums;

import com.google.gson.annotations.SerializedName;

/**
 * Specifies the time span window to use to filter results in set.
 */
public enum SortSpan {

    @SerializedName("0")
    All (0),

    @SerializedName("1")
    Hour (1),

    @SerializedName("2")
    Day (2),

    @SerializedName("3")
    Week (3),

    @SerializedName("4")
    Month (4),

    @SerializedName("5")
    Quarter (5),

    @SerializedName("6")
    Year (6);

    private final int value;
    public int getSortSpan() {
        return this.value;
    }

    SortSpan(int value) {
        this.value = value;
    }
}
