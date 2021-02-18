package com.backend.api.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@EqualsAndHashCode
public class Address extends Base {
    @Column(updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "cep", nullable = false, length = 8)
    private String cep;

    @Column(name = "logradouro", nullable = false)
    private String logradouro;

    @Column(name = "complemento", nullable = false)
    private String complemento;

    @Column(name = "bairro", nullable = false)
    private String bairro;

    @Column(name = "localidade", nullable = false)
    private String localidade;

    @Column(name = "ur", nullable = false, length = 2)
    private String uf;
}