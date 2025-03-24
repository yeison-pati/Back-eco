package com.itm.ecosurprise.controllers;

import org.springframework.web.bind.annotation.*;

import com.itm.ecosurprise.models.Usuario;

import java.util.List;

@RestController("/api/Usuarios")
public class CrontroladorUsuarios {

	@GetMapping()
    public List<Usuario> obtenerTodos() {
		return null;
	}
	@GetMapping("/id:?")
	public Usuario obtenerPorParametros(@RequestParam long id) {
		return null;
	}

	@PostMapping("/crear")
	public boolean crear() {
		return false;
	}

	@PutMapping("/actualizar/id:?")
	public boolean actualizar() {
		return false;
	}

	@DeleteMapping("/eliminar:id")
	public boolean eliminarProducto() {
		return false;
	}

}
