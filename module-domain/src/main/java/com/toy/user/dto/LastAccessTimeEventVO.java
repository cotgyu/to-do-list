package com.toy.user.dto;


import com.toy.common.domain.BaseEventVO;
import com.toy.common.domain.MyTopic;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class LastAccessTimeEventVO extends BaseEventVO {

    private long userId;
    private LocalDateTime lastAccessTime;

    public LastAccessTimeEventVO(long userId, LocalDateTime lastAccessTime) {
        super(MyTopic.LAST_ACCESS_TIME.getTopicName());

        this.userId = userId;
        this.lastAccessTime = lastAccessTime;
    }
}
