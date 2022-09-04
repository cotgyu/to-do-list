package com.toy.common.domain;

public enum MyTopic {
    LAST_ACCESS_TIME("LastAccessTimeTopic");

    private final String topicName;
    MyTopic(String topicName){
        this.topicName = topicName;
    }

    public String getTopicName() {
        return topicName;
    }
}
