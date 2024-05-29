package org.geniuus.practice.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String password;

    @Enumerated(EnumType.STRING)
    private Part part;

    private int age;


    public static Member create(String name, String password, Part part, int age) {
        return Member.builder()
                .name(name)
                .password(password)
                .part(part)
                .age(age)
                .build();
    }
}
