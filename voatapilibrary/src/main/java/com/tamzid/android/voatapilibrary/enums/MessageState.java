package com.tamzid.android.voatapilibrary.enums;

import com.google.gson.annotations.SerializedName;

public enum MessageState {

    @SerializedName("1")
    Unread (1),

    @SerializedName("2")
    Read (2),

    @SerializedName("3")
    All (3);

    private final int value;
    public int getMessageState() {
        return this.value;
    }

    MessageState(int value) {
        this.value = value;
    }
}
