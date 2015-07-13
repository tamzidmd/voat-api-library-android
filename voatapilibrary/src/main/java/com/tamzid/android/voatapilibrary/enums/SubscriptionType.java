package com.tamzid.android.voatapilibrary.enums;

import com.google.gson.annotations.SerializedName;

public enum SubscriptionType {

    @SerializedName("1")
    Subverse (1),

    @SerializedName("2")
    Set (2);

    private final int value;
    public int getSubscriptionType() {
        return this.value;
    }

    SubscriptionType(int value) {
        this.value = value;
    }
}
