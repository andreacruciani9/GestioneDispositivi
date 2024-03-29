package it.epicode.GestioneDispositivi.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class DipendenteRequest {
 @NotNull(message = "username obbligatorio")
 @NotEmpty(message = "usurname mancante")
    private String username;
    @NotNull(message = "nome obbligatorio")
    @NotEmpty(message = "nome mancante")
    private String nome;
    @NotNull(message = "cognome obbligatorio")
    @NotEmpty(message = "cognome mancante")
    private String cognome;
    @Email(message = "email obbligatorio")
    private  String email;
    private List < Dispositivo> dispositivo;

}
