package com.example.Pokemon.API.REST.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDate;

@Entity
@Table(name = "pokemones")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio y no puede estar vacio")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    private String nombre;

    @NotBlank(message = "El tipo del pokemon es obligatorio")
    private String tipo;

    @NotNull(message = "El nivel es obligatorio")
    @Min(value = 1, message = "El nivel mínimo es 1")
    @Max(value = 100, message = "El nivel máximo es de 100")
    private Integer nivel;

    @Min(value = 0, message = "El ataque no puede ser negativo")
    private Integer ataque;

    @Min(value = 0, message = "El ataque no puede ser negativo")
    private Integer defensa;

    @Size(max = 255, message = "La descripción no puede exceder los 255 caracteres")
    private String descripcion;

    @URL(message = "La imagen debe ser una URL válida")
    private String imagenUrl;

    @PastOrPresent(message = "La fecha de captura no puede ser en el futuro")
    private LocalDate fechaCaptura;

}
