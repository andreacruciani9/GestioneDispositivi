package it.epicode.GestioneDispositivi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Dipendente {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String username;
    private String nome;
    private String cognome;
    @Column(unique = true)
    private  String email;
    @JsonIgnore
    @OneToMany(mappedBy = "dipendente")
    private List<Dispositivo> dispositivos;
    private String avatar;

    public Dipendente(String username, String nome, String cognome,String email) {
        this.username = username;
        this.nome = nome;
        this.cognome = cognome;
        this.email=email;
        List<Dispositivo> dispositivos = new ArrayList<>();
    }
    public Dipendente(){}
}
