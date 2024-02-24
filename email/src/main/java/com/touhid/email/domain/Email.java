package com.touhid.email.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Email {
    @Id
    @SequenceGenerator(name = "email_id_generator",
            sequenceName = "email_id_seq_gen",
            initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "email_id_generator")
    private long id;
    @Column(nullable = false)
    private String orderId;
    @Column(nullable = false)
    private String message;
    @Column(nullable = false)
    private LocalDateTime addedOn;
}
