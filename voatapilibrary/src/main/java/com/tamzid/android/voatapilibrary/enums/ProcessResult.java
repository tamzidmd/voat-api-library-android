package com.tamzid.android.voatapilibrary.enums;

import com.google.gson.annotations.SerializedName;

public enum ProcessResult {

    @SerializedName("0")
    NotProcessed (0),

    @SerializedName("1")
    Success (1),

    @SerializedName("2")
    Ignored (2),

    @SerializedName("3")
    Denied (3);

    private final int value;

    ProcessResult(int value) {
        this.value = value;
    }

    public int getProcessResult() {
        return this.value;
    }
}
