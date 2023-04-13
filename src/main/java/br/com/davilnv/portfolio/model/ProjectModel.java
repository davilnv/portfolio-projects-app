package br.com.davilnv.portfolio.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "projeto")
@Table(name = "projeto")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProjectModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 200)
    private String name;

    @Column(name = "data_inicio")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date initialDate;

    @Column(name = "data_previsao_fim")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date forecastDate;

    @Column(name = "data_fim")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date finalDate;

    @Column(name = "descricao", length = 5000)
    private String description;

    @Column(name = "status", length = 45)
    private String status;

    @Column(name = "orcamento")
    private double budget;

    @Column(name = "risco", length = 45)
    private String risk;

    @ManyToOne
    @JoinColumn(name = "idgerente")
    private PersonModel manager;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "membros",
            joinColumns = @JoinColumn(name = "idprojeto", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "idpessoa", referencedColumnName = "id")
    )
    private List<PersonModel> members = new ArrayList<>();

}
