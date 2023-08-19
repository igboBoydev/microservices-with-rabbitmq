package com.abelkelly.notification.Model;

import lombok.*;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomMessage {
    private String messageId;
    private String message;
    private Date messageDate;
}
