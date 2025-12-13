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
		return "indexM";
	}

	/*---Devuelve el formulario para anyadir una nueva cuenta con una cuenta vacia---*/
	@GetMapping("/addMedicamento")
	public String addMedicamentoView(Model model) {
		model.addAttribute("medicamento", new Medicamento());
		return "addMedicamento";
	}

	/*---Devuelve el formulario para listar las cuentas del sistema---*/
	@GetMapping("/listMedicamento")
	public String listMedicamentoView(Model model) {

		model.addAttribute("medicamentos", medicamentoService.getAllMedicamentos());

		return "listMedicamento";
	}


	/*---Devuelve el formulario para editar un Medicamento---*/
	@GetMapping("/editMedicamento/{id}")
	public String editMedicamentoView(@PathVariable("id") Long id, Model model) {

		model.addAttribute("medicamento", medicamentoService.getMedicamento(id));
		return "updateMedicamento";
	}
    
}
