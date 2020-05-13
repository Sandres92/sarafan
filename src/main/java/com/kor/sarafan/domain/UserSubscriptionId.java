package com.kor.sarafan.domain;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSubscriptionId implements Serializable {
    @JsonView(Views.id.class)
    private String channelId;
    @JsonView(Views.id.class)
    private String subscriberId;
}
