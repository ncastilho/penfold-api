package org.penfold.website;

import org.apache.commons.lang.StringUtils;

import java.util.Arrays;

public enum State {
    INITIAL {
        @Override
        State validateState(State state) {
            if (Arrays.asList(FORWARDED, FAILED).contains(state)) {
                return state;
            }

            throw createException(this, state);
        }
    },
    FORWARDED {
        @Override
        State validateState(State state) {
            if (Arrays.asList(SENT, QUEUED, FAILED).contains(state)) {
                return state;
            }

            throw createException(this, state);
        }
    },
    QUEUED {
        @Override
        State validateState(State state) {
            if (Arrays.asList(SENT, FAILED).contains(state)) {
                return state;
            }

            throw createException(this, state);
        }
    },
    SENT {
        @Override
        State validateState(State state) {
            if (Arrays.asList(DELIVERED, UNDELIVERED, FAILED).contains(state)) {
                return state;
            }

            throw createException(this, state);
        }
    },
    DELIVERED,
    UNDELIVERED,
    FAILED {
        @Override
        State validateState(State state) {
            if (state.equals(FORWARDED)) {
                return state;
            }

            throw createException(this, state);
        }
    };

    State validateState(State state) {
        throw createException(this, state);
    }

    public static State fromString(String value) {
        if (StringUtils.isBlank(value)) {
            throw new IllegalArgumentException();
        }

        return State.valueOf(value.toUpperCase());
    }

    private static RuntimeException createException(State from, State to) {
        throw new IllegalStateException(String.format("Illegal state transition: [from=%s, to=%s]", from, to));
    }
}