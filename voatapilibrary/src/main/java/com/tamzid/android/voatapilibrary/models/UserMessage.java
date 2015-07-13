package com.tamzid.android.voatapilibrary.models;

import com.tamzid.android.voatapilibrary.enums.MessageType;

public class UserMessage {
    public int id;
    public int commentID;
    public int submissionID;
    public String subverse;
    public String recipient;
    public String sender;
    public String subject;
    public boolean unread;
    public MessageType type;
    public String typeName;
    public String sentDate;
    public String content;
    public String formattedContent;
}
