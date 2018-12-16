package org.penfold.website;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class Callback {
    @NotEmpty
    private String accountSid;
    private String errorCode;
    private String errorMessage;
    private String messageStatus;
    @NotEmpty
    private String messageSid;
    private String from;
    private String to;
}
