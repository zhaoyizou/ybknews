package com.visionet.domain;

public class BaseJson<M> {

    private BaseHeader head;
    private M body;

    public M getBody() {
        return body;
    }

    public void setBody(M body) {
        this.body = body;
    }

    public BaseHeader getHead() {
        return head;
    }

    public void setHead(BaseHeader head) {
        this.head = head;
    }

}
