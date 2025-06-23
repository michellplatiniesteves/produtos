package br.com.produtos.entidade;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "usuario_seq")
    @SequenceGenerator(name = "usuario_seq",initialValue = 1,allocationSize = 1)
    private Long id;
    private String nome;
    @Column(unique = true)
    private String email;
    private String senha;
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "usuario_roles",joinColumns = @JoinColumn(name = "usuario_id"),inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;
}
