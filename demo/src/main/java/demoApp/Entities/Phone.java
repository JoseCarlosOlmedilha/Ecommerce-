package demoApp.Entities;

import demoApp.Entities.Enums.TypePhone;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "tb_phone")
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long phoneId;

    @Enumerated(EnumType.STRING)
    private TypePhone typePhone;

    @Column(nullable = false, length = 9)
    private String numero;

    @Column(nullable = false, length = 3)
    private String ddd;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false) 
    private Client client;
    }
