package com.tamzid.android.voatapilibrary.enums;

import com.google.gson.annotations.SerializedName;

public enum MessageType {

    @SerializedName("1")
    Inbox (1),

    @SerializedName("2")
    Sent (2),

    @SerializedName("4")
    Comment (4),

    @SerializedName("8")
    Submission (8),

    @SerializedName("16")
    Mention (16),

    @SerializedName("31")
    All (31);

    private final int value;
    public int getMessageType() {
        return this.value;
    }

    MessageType(int value) {
        this.value = value;
    }
}
