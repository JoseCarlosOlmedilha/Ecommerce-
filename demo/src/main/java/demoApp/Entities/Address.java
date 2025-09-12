package demoApp.Entities;

import demoApp.Entities.Enums.UfAddress;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    @Column(nullable = false, length = 150)
    private String street;

    @Column(nullable = false, length = 50)
    private String city;

    @Enumerated(EnumType.STRING)
    private UfAddress uf;

    @Column(nullable = false, length = 10)
    private String number;

    @Column(nullable = false, length = 100)
    private String complemento;

    @Column(nullable = false)
    private String cep;

    @Column(nullable = false, length = 15)
    private String bairro;

    @OneToOne
    @JoinColumn(name = "cliente_id", nullable = false, unique = true)
    private Client client;


}
