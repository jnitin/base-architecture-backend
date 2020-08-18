package com.backend.api.resources;

import com.backend.api.domain.Parametro;
import com.backend.api.dto.ParametroDTO;
import com.backend.api.services.ParametroService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/parametros")
public class ParametroResource extends CrudResource<Parametro, ParametroDTO> {

    @Autowired
    private ParametroService service;

    public ParametroResource() {
        setService(service);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Parametro> find(@PathVariable Integer id) {
		
		Parametro obj = service.find(id);
		return ResponseEntity.ok().body(obj);
		
	}

}