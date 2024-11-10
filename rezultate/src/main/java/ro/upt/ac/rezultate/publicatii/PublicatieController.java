package ro.upt.ac.rezultate.publicatii;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PublicatieController {
	@Autowired
	PublicatieRepository publicatieRepository; 
	
	@GetMapping("/publicatie-create")
	public String create(Publicatie publicatie)
	{
		return "publicatie-create";
	}
	
	@PostMapping("/publicatie-create-save")
	public String createSave(@Validated Publicatie publicatie, BindingResult result, Model model)
	{
		if(result.hasErrors())
		{
			return "publicatie-create";
		}
		publicatieRepository.save(publicatie);
		return "redirect:/publicatie-read";
	}
	
	@GetMapping("/publicatie-read")
	public String read(Model model) 
	{
	    model.addAttribute("publicatii", publicatieRepository.findAll());
	    return "publicatie-read";
	}
	
	@GetMapping("/publicatie-edit/{id}")
	public String edit(@PathVariable("id") int id, Model model) 
	{
	    Publicatie publicatie = publicatieRepository.findById(id);
	    //.orElseThrow(() -> new IllegalArgumentException("Invalid publicatie Id:" + id));
	    
	    model.addAttribute("publicatie", publicatie);
	    return "publicatie-update";
	}
	
	@PostMapping("/publicatie-update/{id}")
	public String update(@PathVariable("id") int id, @Validated Publicatie publicatie, BindingResult result, Model model) 
	{
	    if(result.hasErrors()) 
	    {
	        publicatie.setId(id);
	        return "publicatie-update";
	    }
	        
	    publicatieRepository.save(publicatie);
	    return "redirect:/publicatie-read";
	}
	
	@GetMapping("/publicatie-delete/{id}")
	public String delete(@PathVariable("id") int id, Model model) 
	{
	    Publicatie publicatie = publicatieRepository.findById(id);
	    //.orElseThrow(() -> new IllegalArgumentException("Invalid companie Id:" + id));
	    
	    publicatieRepository.delete(publicatie);
	    return "redirect:/publicatie-read";
	}	
}
