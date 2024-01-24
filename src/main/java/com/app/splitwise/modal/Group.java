package com.app.splitwise.modal;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "groups")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, updatable = false)
    private Long groupId;

    @Column(unique = true)
    private String groupName;

    private int groupSize = 0;

    @OneToMany
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    private List<Member> members;
}
