package org.penfold.website;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class Message {
    @NotEmpty
    private String content;
    @NotEmpty
    private String scheduledTime;
}