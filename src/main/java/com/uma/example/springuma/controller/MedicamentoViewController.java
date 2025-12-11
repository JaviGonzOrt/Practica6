package com.uma.example.springuma.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.uma.example.springuma.model.Medicamento;
import com.uma.example.springuma.model.MedicamentoService;


@Controller
public class MedicamentoViewController {

    @Autowired
    private MedicamentoService medicamentoService;

    /*---Devuelve el formulario de index de medicamentos---*/
	@GetMapping("/")
	public String indexMedicamentoView() {
		return "indexC";
	}

    /*---Devuelve el formulario para anyadir un nuevo medicamento con un medicamento vacio---*/
	@GetMapping("/addMedicamento")
	public String addMedicamentoView(Model model) {
		model.addAttribute("medicamento", new Medicamento());
		return "addMedicamento";
	}

    /*---Devuelve el formulario para listar los medicamentos del sistema---*/
    @GetMapping("/listMedicamentos")
    public String mostrarMedicamentos(Model model) {
        model.addAttribute("medicamentos", medicamentoService.getAllMedicamentos());
        return "listaMedicamentos";
    }

    /*---Devuelve el formulario para editar un Medicamento---*/
	@GetMapping("/editMedicamento/{id}")
	public String editMedicamentoView(@PathVariable("id") Long id, Model model) {

		model.addAttribute("medicamento", medicamentoService.getMedicamento(id));
		return "updateMedicamento";
	}
    
    
}
