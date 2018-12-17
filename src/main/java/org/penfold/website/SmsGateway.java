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
    private final String fromNumber;
    private final String callbackHost;

    public SmsGateway(
            @Value("${twilio.account-sid}") String accountSid,
            @Value("${twilio.auth-token}") String authToken,
            @Value("${twilio.from-number}") String fromNumber,
            @Value("${twilio.callback-host}") String callbackHost) {
        this.accountSid = accountSid;
        this.authToken = authToken;
        this.fromNumber = fromNumber;
        this.callbackHost = callbackHost;
    }

    public String sendMessage(String messageId, String toNumber, String body) {
        Twilio.init(accountSid, authToken);

        Message message = Message.creator(new PhoneNumber(toNumber), new PhoneNumber(fromNumber), body)
                .setStatusCallback(createStatusCallbackURI(messageId))
                .create();

        return message.getSid();
    }

    private URI createStatusCallbackURI(String messageId) {
        String uri = String.format("%s%s/api/callback/%s", callbackHost, messageId);
        return URI.create(uri);
    }
}
