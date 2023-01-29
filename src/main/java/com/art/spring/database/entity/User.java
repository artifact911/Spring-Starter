package com.art.spring.database.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NamedEntityGraph(
        name = "User.company",
        attributeNodes = @NamedAttributeNode("company")
)
@Data
@ToString(exclude = "userChats")
@EqualsAndHashCode(of = "username")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User extends AuditingEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    // по умолчанию юзается CamelCase в качестве стратегии преобразования Java
    // именований в sql. Можно изменить через properties:
    // spring.jpa.hibernate.naming.implicit-strategy
    // spring.jpa.hibernate.naming.physical-strategy
    private LocalDate birthDate;

    private String firstname;

    private String lastname;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    // енобязательно, т.к. взяло бы и без него название поле и ч/з нижнее подчеркиваени
    // добивило _id
    @JoinColumn(name = "company_id")
    private Company company;

    @Builder.Default
    @OneToMany(mappedBy = "user")
    private List<UserChat> userChats = new ArrayList<>();
}
