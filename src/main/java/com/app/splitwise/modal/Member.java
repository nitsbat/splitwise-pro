package com.app.splitwise.modal;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "member")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, updatable = false)
    private Long memberId;

    private String userName;

    private double owed;

    private double pay;

    @ManyToOne
    private Group group;
}
