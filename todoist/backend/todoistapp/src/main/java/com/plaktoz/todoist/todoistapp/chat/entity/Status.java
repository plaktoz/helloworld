package com.plaktoz.todoist.todoistapp.chat.entity;

import java.util.List;

public class Status {
    private List<String> deliveredTo;
    private List<ReadReceipt> readBy;

    public Status() {
    }

    public Status(List<String> deliveredTo, List<ReadReceipt> readBy) {
        this.deliveredTo = deliveredTo;
        this.readBy = readBy;
    }

    public List<String> getDeliveredTo() {
        return deliveredTo;
    }

    public void setDeliveredTo(List<String> deliveredTo) {
        this.deliveredTo = deliveredTo;
    }

    public List<ReadReceipt> getReadBy() {
        return readBy;
    }

    public void setReadBy(List<ReadReceipt> readBy) {
        this.readBy = readBy;
    }
}