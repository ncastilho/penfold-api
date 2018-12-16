package org.penfold.website;

import org.apache.commons.lang.StringUtils;

import java.util.Arrays;

public enum MessageState {
    INITIAL {
        @Override
        MessageState transitionTo(MessageState messageState) {
            if (Arrays.asList(FORWARDED, FAILED).contains(messageState)) {
                return messageState;
            }

            throw new IllegalStateException();
        }
    },
    FORWARDED {
        @Override
        MessageState transitionTo(MessageState messageState) {
            if (Arrays.asList(SENT, QUEUED, FAILED).contains(messageState)) {
                return messageState;
            }
            throw new IllegalStateException();
        }
    },
    QUEUED {
        @Override
        MessageState transitionTo(MessageState messageState) {
            if (Arrays.asList(SENT, FAILED).contains(messageState)) {
                return messageState;
            }
            throw new IllegalStateException();
        }
    },
    SENT {
        @Override
        MessageState transitionTo(MessageState messageState) {
            if (Arrays.asList(DELIVERED, UNDELIVERED, FAILED).contains(messageState)) {
                return messageState;
            }
            throw new IllegalStateException();
        }
    },
    DELIVERED {
        @Override
        MessageState transitionTo(MessageState messageState) {
            throw new IllegalStateException();
        }
    },
    UNDELIVERED {
        @Override
        MessageState transitionTo(MessageState messageState) {
            throw new IllegalStateException();
        }
    },
    FAILED {
        @Override
        MessageState transitionTo(MessageState messageState) {
            if (messageState.equals(FORWARDED)) {
                return messageState;
            }

            throw new IllegalStateException();
        }
    };

    abstract MessageState transitionTo(MessageState messageState);

    public static MessageState fromString(String value) {
        if (StringUtils.isBlank(value)) {
            throw new IllegalArgumentException();
        }

        return MessageState.valueOf(value.toUpperCase());
    }
}