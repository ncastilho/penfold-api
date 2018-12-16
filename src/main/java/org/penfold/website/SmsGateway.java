package org.penfold.website;

import com.twilio.Twilio;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.net.URI;

@Component
public class SmsGateway {

    private final String accountSid;
    private final String authToken;
    private final String fromPhoneNumber;
    private final String contextPath;
    private final String callbackHost;

    public SmsGateway(
            @Value("${twilio.account-sid}") String accountSid,
            @Value("${twilio.auth-token}") String authToken,
            @Value("${twilio.from-phone-number}") String fromPhoneNumber,
            @Value("${twilio.callback-host}") String callbackHost,
            @Value("${server.servlet.context-path}") String contextPath) {
        this.accountSid = accountSid;
        this.authToken = authToken;
        this.fromPhoneNumber = fromPhoneNumber;
        this.callbackHost = callbackHost;
        this.contextPath = contextPath;
    }

    public String sendMessage(String messageId, String toPhoneNumber, String body) {
        Twilio.init(accountSid, authToken);

        Message message = Message.creator(new PhoneNumber(toPhoneNumber), new PhoneNumber(fromPhoneNumber), body)
                .setStatusCallback(URI.create(String.format("%s%s/callback/%s", callbackHost, contextPath, messageId)))
                .create();

        return message.getSid();
    }
}
