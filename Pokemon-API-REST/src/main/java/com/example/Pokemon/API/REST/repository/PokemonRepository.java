package com.example.Pokemon.API.REST.repository;

import com.example.Pokemon.API.REST.model.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PokemonRepository  extends JpaRepository<Pokemon, Long> {
}
