package br.com.produtos.entidade;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "categoria")
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Categoria implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "categoria_seq")
    @SequenceGenerator(name = "categoria_seq",initialValue = 1,allocationSize = 1)
    private Long id;

    @NotEmpty(message = "Informe o nome da categoria")
    @Column
    private String nome;

}
