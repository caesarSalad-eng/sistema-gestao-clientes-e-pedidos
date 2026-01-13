package com.br.sistema_gestao_clientes_e_pedidos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter
    @Setter
    @Column(nullable = false)
    private String nome;

    @Getter
    @Setter
    @Column(nullable = false, unique = true)
    private String email;

    @Getter
    @Setter
    @Column(nullable = false)
    private String telefone;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER)
    @Getter
    private List<Pedido> pedidos;

    @Setter
    @Getter
    private Date dataCadastro;

    @PrePersist
    public void prePersist(){

        this.dataCadastro = new Date();

    }

}
