package com.example.document.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by Edi on 29.07.2019.
 */
@Entity
@Data
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    @SequenceGenerator(name = "seq", sequenceName = "document_id_seq", allocationSize = 1)
    private Long id;
    @Column(unique = true)
    private String name;
    @ManyToOne
    @JoinColumn(name="initiator_id", referencedColumnName="id")
    private Company initiator;
    private LocalDateTime initiationDateTime;
    @ManyToOne
    @JoinColumn(name="SENDER_ID", referencedColumnName="id")
    private Company sender;
    private boolean electronicSignatureSender;
    @ManyToOne
    @JoinColumn(name="recipient_id", referencedColumnName="id")
    private Company recipient;
    private boolean electronicSignatureRecipient;
    private String info;
}
