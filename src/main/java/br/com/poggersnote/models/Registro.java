package br.com.poggersnote.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity(name = "T_PN_REGISTRO")
public class Registro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate data;

    @NotNull
    @Column(nullable = false)
    private String titulo;

    @NotNull
    @Column(nullable = false)
    private String descricao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    @JsonIgnore
    private Usuario usuario;

    public Registro(String titulo, String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public void setDataCriacao() {
        this.data = LocalDate.now();
    }

    @Override
    public String toString() {
        return "Data: " + data + "\nTitulo: " + titulo + "\nDescricao: " + descricao;
    }
}
