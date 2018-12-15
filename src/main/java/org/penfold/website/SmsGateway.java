package org.penfold.website;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Component;

@Component
public class SmsGateway {

    public String sendMessage(String mobile, String content) {
        return RandomStringUtils.random(10, true, false);
    }
}
