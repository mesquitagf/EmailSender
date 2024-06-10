package com.ms.email.dto;

import com.ms.email.model.EmailModel;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class EmailDTO {

    @NotBlank
    private String ownerRef;
    @NotBlank
    @Email
    private String emailFrom;
    @NotBlank
    @Email
    private String emailTo;
    @NotBlank
    private String subject;
    @NotBlank
    private String text;

    public EmailModel covertToEmailModel(EmailDTO emailDTO) {
        var emailModel = new EmailModel();
        BeanUtils.copyProperties(emailDTO, emailModel);
        return emailModel;
    }
}
