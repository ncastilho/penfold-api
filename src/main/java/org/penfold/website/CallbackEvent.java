package org.penfold.website;

import lombok.Data;
import org.apache.commons.lang.StringUtils;

import javax.validation.constraints.NotEmpty;

@Data
public class CallbackEvent {
    @NotEmpty
    private String accountSid;
    private String errorCode;
    private String errorMessage;
    private String messageStatus;
    @NotEmpty
    private String messageSid;
    private String from;
    private String to;

    public String getMessage() {
        if (StringUtils.isBlank(errorCode)) {
            return String.format("Message has been %s: [sid=%s]", messageStatus, messageSid);
        }

        return String.format("%s: [sid=%s, code=%s]", errorMessage, errorCode);
    }
}
