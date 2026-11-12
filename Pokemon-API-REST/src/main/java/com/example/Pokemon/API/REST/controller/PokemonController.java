package com.example.Pokemon.API.REST.controller;

import com.example.Pokemon.API.REST.model.Pokemon;
import com.example.Pokemon.API.REST.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pokemones")
public class PokemonController {

    @Autowired
    private PokemonService pokemonService;

    @GetMapping
    public List<Pokemon> obtenerTodos() {
        return pokemonService.obtenerPokemones();
    }

    @GetMapping("/{id}")
    public Pokemon obtenerPorId(@PathVariable Long id) {
        return pokemonService.obtenerPokemonId(id);
    }

    @PostMapping
    public Pokemon crear(@RequestBody Pokemon pokemon) {
        return pokemonService.crear(pokemon);
    }

    @PutMapping("/{id}")
    public Pokemon actualizarCompleto(@PathVariable Long id, @RequestBody Pokemon pokemon) {
        if (pokemonService.obtenerPokemonId(id) != null) {
            return pokemonService.actualizarCompleto(id, pokemon);
        }
        return null;
    }

    @PatchMapping("/{id}")
    public Pokemon actualizarParcial(@PathVariable Long id, @RequestBody Pokemon pokemon) {
        if (pokemonService.obtenerPokemonId(id) != null) {
            return pokemonService.actualizarParcial(id, pokemon);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        if (pokemonService.obtenerPokemonId(id) != null) {
            pokemonService.eliminar(id);
        }
    }



}
