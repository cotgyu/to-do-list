package com.toy.common.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
public class BaseEventVO implements Serializable {

    private String topicName;

    public BaseEventVO(String topicName) {
        this.topicName = topicName;
    }
}
