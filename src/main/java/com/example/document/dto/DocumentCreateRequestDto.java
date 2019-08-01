package com.example.document.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created by Edi on 30.07.2019.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentCreateRequestDto {
    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotNull(message = "Sender is mandatory")
    private Long senderId;
    @NotNull(message = "Recipient is mandatory")
    private Long recipientId;
    private String info;
}
