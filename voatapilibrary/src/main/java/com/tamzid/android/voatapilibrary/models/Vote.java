package com.tamzid.android.voatapilibrary.models;

import com.tamzid.android.voatapilibrary.enums.ProcessResult;

public class Vote {
    public ProcessResult result;
    public String resultName;
    public String message;
    public boolean success;
    /**
     * The users recorded vote value after the operation has completed.
     * Use this value to verify vote operation is recorded correctly.
     * Valid values are: -1 (down voted), 0 (revoked, unvoted), or 1 (up voted)
     */
    public int recordedValue;
}
