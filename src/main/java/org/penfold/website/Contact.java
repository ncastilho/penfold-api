package org.penfold.website;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class Contact {
    @NotEmpty
    private String name;
    @NotEmpty
    private String email;
    @NotEmpty
    private String mobile;
    private List<Message> messages;
}
