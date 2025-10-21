package demoApp.Entities;

import java.time.LocalDate;
import java.util.List;

import demoApp.Entities.Enums.Sexo;
import demoApp.Entities.Enums.StatusCliente;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clienteId")
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusCliente statusCliente;
   
    @Column(nullable = false)
    private String nome;

    @Column(unique = true, nullable = false)
    private String cpf;

    @Column(nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Sexo sexo;

    @Column(nullable = false)
    private LocalDate dataAniversario;

    @OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private Endereco endereco;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Telefone> telefone;

public String toString() {
    return "Cliente{" +
            "id=" + id +
            ", statusCliente=" + statusCliente +
            ", nome='" + nome + '\'' +
            ", cpf='" + cpf + '\'' +
            ", email='" + email + '\'' +
            ", sexo=" + sexo +
            ", dataAniversario=" + dataAniversario +
            ", endereco=" + (endereco != null ? endereco.toString() : "null") +
            ", telefone=" + (telefone != null ? telefone.toString() : "null") +
            '}';
}

}
