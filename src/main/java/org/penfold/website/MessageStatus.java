package org.penfold.website;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang.StringUtils;

public enum MessageStatus {
    INITIAL {
        @Override
        MessageStatus dispatch(MessageStatus messageStatus) {
            if (messageStatus.equals(FORWARDED) || messageStatus.equals(FAILED)) {
                return messageStatus;
            }

            throw new IllegalStateException();
        }
    },
    FORWARDED {
        @Override
        MessageStatus dispatch(MessageStatus messageStatus) {
            if (messageStatus.equals(SENT) || messageStatus.equals(QUEUED) || messageStatus.equals(FAILED)) {
                return messageStatus;
            }
            throw new IllegalStateException();
        }
    },
    @JsonProperty("queued")
    QUEUED {
        @Override
        MessageStatus dispatch(MessageStatus messageStatus) {
            if (messageStatus.equals(SENT) || messageStatus.equals(FAILED)) {
            return messageStatus;
            }
                throw new IllegalStateException();
        }
    },
    @JsonProperty("sent")
    SENT {
        @Override
        MessageStatus dispatch(MessageStatus messageStatus) {
            if (messageStatus.equals(DELIVERED) || messageStatus.equals(UNDELIVERED) || messageStatus.equals(FAILED)) {
            return messageStatus;
            }
                throw new IllegalStateException();
        }
    },
    @JsonProperty("delivered")
    DELIVERED {
        @Override
        MessageStatus dispatch(MessageStatus messageStatus) {
            throw new IllegalStateException();
        }
    },
    @JsonProperty("undelivered")
    UNDELIVERED {
        @Override
        MessageStatus dispatch(MessageStatus messageStatus) {
            throw new IllegalStateException();
        }
    },
    @JsonProperty("failed")
    FAILED {
        @Override
        MessageStatus dispatch(MessageStatus messageStatus) {
            if (messageStatus.equals(FORWARDED)) {
                return messageStatus;
            }

            throw new IllegalStateException();
        }
    };

    abstract MessageStatus dispatch(MessageStatus messageStatus);

    public static MessageStatus fromString(String value) {
        if(StringUtils.isBlank(value)) {
            throw new IllegalArgumentException();
        }

        return MessageStatus.valueOf(value.toUpperCase());
    }
}