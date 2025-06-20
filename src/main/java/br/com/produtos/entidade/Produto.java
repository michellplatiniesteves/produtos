package br.com.produtos.entidade;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "produto")
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Produto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "produto_seq",initialValue = 1,allocationSize = 1)
    private Long id;

    @NotEmpty(message = "Informe o nome do produto")
    @Column
    private String nome;

    @Column
    @Min(value = 1)
    private Integer quantidade;

    @Column
    @Min(value = 1)
    private BigDecimal preco;

    @ManyToOne(targetEntity = Categoria.class)
    @JoinColumn(name = "categoria",nullable = false,foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT,name = ""))
    private Categoria categoria;

}
