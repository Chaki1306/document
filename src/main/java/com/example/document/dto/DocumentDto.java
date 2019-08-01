package com.example.document.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Edi on 29.07.2019.
 */
@Getter
@Setter
@Builder
public class DocumentDto {
    private String nameDocument;
    private String nameInitiator;
    private String initiationDateTime;
    private String nameSender;
    private boolean electronicSignatureSender;
    private String nameRecipient;
    private boolean electronicSignatureRecipient;
    private String info;
}
