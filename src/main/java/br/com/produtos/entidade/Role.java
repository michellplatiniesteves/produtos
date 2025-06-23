package br.com.produtos.entidade;

import br.com.produtos.entidade.DTO.Roles;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "roles")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "role_seq")
    @SequenceGenerator(name = "role_seq",initialValue = 1,allocationSize = 1)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Roles role;
}
