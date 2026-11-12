package com.example.Pokemon.API.REST.service;

import com.example.Pokemon.API.REST.model.Pokemon;
import com.example.Pokemon.API.REST.repository.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokemonService {

    @Autowired
    private PokemonRepository pokemonRepository;

    public List<Pokemon> obtenerPokemones(){
        return pokemonRepository.findAll();
    }

    public Pokemon obtenerPokemonId(Long id) {
        return pokemonRepository.findById(id).orElse(null);
    }

    public Pokemon crear(Pokemon pokemon) {
        return pokemonRepository.save(pokemon);
    }

    public Pokemon actualizarCompleto(Long id, Pokemon pokemonActualizado) {
        Pokemon pokemonExistente = obtenerPokemonId(id);
        if (pokemonExistente != null) {
            pokemonExistente.setNombre(pokemonActualizado.getNombre());
            pokemonExistente.setTipo(pokemonActualizado.getTipo());
            pokemonExistente.setNivel(pokemonActualizado.getNivel());
            pokemonExistente.setAtaque(pokemonActualizado.getAtaque());
            pokemonExistente.setDefensa(pokemonActualizado.getDefensa());
            pokemonExistente.setDescripcion(pokemonActualizado.getDescripcion());
            pokemonExistente.setImagenUrl(pokemonActualizado.getImagenUrl());
            return pokemonRepository.save(pokemonExistente);
        }
        return null;
    }

    public Pokemon actualizarParcial(Long id, Pokemon pokemonParcial) {
        Pokemon pokemonExistente = obtenerPokemonId(id);
        if (pokemonExistente != null) {
            if (pokemonParcial.getNombre() != null) {
                pokemonExistente.setNombre(pokemonParcial.getNombre());
            }
            if (pokemonParcial.getTipo() != null) {
                pokemonExistente.setTipo(pokemonParcial.getTipo());
            }
            if (pokemonParcial.getNivel() != null) {
                pokemonExistente.setNivel(pokemonParcial.getNivel());
            }
            if (pokemonParcial.getAtaque() != null) {
                pokemonExistente.setAtaque(pokemonParcial.getAtaque());
            }
            if (pokemonParcial.getDefensa() != null) {
                pokemonExistente.setDefensa(pokemonParcial.getDefensa());
            }
            if (pokemonParcial.getDescripcion() != null) {
                pokemonExistente.setDescripcion(pokemonParcial.getDescripcion());
            }
            if (pokemonParcial.getImagenUrl() != null) {
                pokemonExistente.setImagenUrl(pokemonParcial.getImagenUrl());
            }
            return pokemonRepository.save(pokemonExistente);
        }
        return null;
    }

    public boolean eliminar(Long id) {
        if (pokemonRepository.existsById(id)) {
            pokemonRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
