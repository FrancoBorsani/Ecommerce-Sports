package com.ecommercesports.ecommercesports.controllers;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.ecommercesports.ecommercesports.converters.ClaveTemporalConverter;
import com.ecommercesports.ecommercesports.entities.ClaveTemporal;
import com.ecommercesports.ecommercesports.entities.User;
import com.ecommercesports.ecommercesports.helpers.ViewRouteHelpers;
import com.ecommercesports.ecommercesports.implementation.SendMailService;
import com.ecommercesports.ecommercesports.implementation.UserRoleService;
import com.ecommercesports.ecommercesports.models.ClaveTemporalModel;
import com.ecommercesports.ecommercesports.repositories.IUserRepository;
import com.ecommercesports.ecommercesports.repositories.IUserRoleRepository;
import com.ecommercesports.ecommercesports.services.IClaveTemporalService;


@Controller	
public class UserController {	
	
	@Autowired
	@Qualifier("claveTemporalService")
	private IClaveTemporalService claveTemporalService;
	
	
	
	@Autowired
	@Qualifier("userRepository")
	private IUserRepository userRepository;
	
	@Autowired
	@Qualifier("userRoleRepository")
	private IUserRoleRepository userRoleRepository;
	
	@Autowired
	@Qualifier("userRoleService")
	private UserRoleService userRoleService;
	
	
	@GetMapping("/registro")
	public ModelAndView registro() {
	 ModelAndView mAV = new ModelAndView(ViewRouteHelpers.USER_REGISTRO);
	 return mAV;
	}

	@PostMapping("/register")
	public RedirectView  registerUserAccount( @ModelAttribute("user") User newUSer, RedirectAttributes redirectAttrs) {
		
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
		newUSer.setPassword(bCryptPasswordEncoder.encode(newUSer.getPassword())); 
				
		if(userRepository.findByUsername(newUSer.getUsername()) != null ||  userRepository.findByEmail(newUSer.getEmail()) != null) {
			System.out.println("la cuenta ya existe");
			redirectAttrs.addFlashAttribute("mensaje","Ya existe un usuario con esos datos");
			redirectAttrs.addFlashAttribute("clase", "danger");
			return new RedirectView("/registro");
		}
		else {
			userRepository.save(newUSer);
		}
		
		System.out.println("-----------------------------------------");
		
		System.out.println(newUSer.getFirstName());
		System.out.println(newUSer.getLastName());
		System.out.println(newUSer.getEmail());
		System.out.println(bCryptPasswordEncoder.encode(newUSer.getPassword()));
				
		System.out.println("-----------------------------------------");
		
		return new RedirectView(ViewRouteHelpers.ROUTE_INDEX);
	}
	
	
	
	@GetMapping("/ingreso")
	public ModelAndView ingreso() {
	 ModelAndView mAV = new ModelAndView(ViewRouteHelpers.USER_LOGIN);	 
	 
	 return mAV;
	}
	
	@GetMapping("/login")	
	public String login(Model model,	
						@RequestParam(name="error",required=false) String error,	
						@RequestParam(name="logout", required=false) String logout) {	

		if(error!=null){
			model.addAttribute("error", error);
		}
			
		model.addAttribute("logout", logout);
		
		String username = "";
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
		  username = ((UserDetails)principal).getUsername();
		}
		System.out.println(username);
		if(!username.isEmpty()) {
			return "redirect:/";
		}
		else {
			return "/acceso/ingreso";
		}
	}	


	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
	    return "redirect:/login?logout"; //You can redirect wherever you want, but generally it's a good practice to show login screen again.
	}

	@GetMapping("/loginsuccess")	
	public String loginCheck() {	
		return "redirect:/";	
	}
	
	
	
	@GetMapping("/recuperarclave")
	public ModelAndView recuperarClave() {
	 ModelAndView mAV = new ModelAndView(ViewRouteHelpers.USER_RECUPERARCLAVE);	 
	 mAV.addObject("claveTemporal", new ClaveTemporalModel());
	 return mAV;
	}
	
	
	

    @Autowired
    private SendMailService SendmailService;

   // @GetMapping("/")
   // public String index(){
   //     return "send_mail_view";
  //  }

    @PostMapping("/sendMail")
    public ModelAndView sendMail(@RequestParam("correo") String correo){
    	ModelAndView mAV = new ModelAndView(ViewRouteHelpers.USER_VERIFICARCLAVE);
    	int claveTemporal = (int) (Math.random() * 100000) + 1;
    //	ClaveTemporalModel c = new ClaveTemporalModel(claveTemporalService.getAll().get(claveTemporalService.getAll().size()-1).getId(), claveTemporal);
    //	c.setClave(((int)claveTemporal));
    	ClaveTemporalModel c = new ClaveTemporalModel();
    	c.setClave(claveTemporal);
    	claveTemporalService.insertOrUpdate(c);
    	
    	//claveTemporalService.insertOrUpdate(c);
    	String message = "\n\n Datos de contacto: " + "\nE-mail: " + correo + " registrado en Ecommerce Sports." + "\nClave de recuperación: " + claveTemporal;
        String subject = "RECUPERACION DE CLAVE";
        SendmailService.sendMail("proyectodesoftwaretp@gmail.com", "" + correo,subject,message);
        
   	 return mAV;
    }
	
    
    @PostMapping("/verificarClave")
    public ModelAndView verificarClave(@ModelAttribute("clave") long clave,  RedirectAttributes redirectAttrs ){
    	
    	ModelAndView mAV = new ModelAndView(ViewRouteHelpers.HOME);
    	
    	
    	int i=0;
		boolean band = false;

		while(i<claveTemporalService.getAll().size() && !band){
			ClaveTemporal c = claveTemporalService.getAll().get(i);
			System.out.println( claveTemporalService.getAll().get(i));
			System.out.println(c.getId());
				if(c.getClave() == clave){
					band = true;
					System.out.println("SE ENCONTRO");
				}
			i++;
		}

		if(!band){
			redirectAttrs.addFlashAttribute("mensaje","La clave es incorrecta");
			redirectAttrs.addFlashAttribute("clase", "danger");
			ModelAndView mAV2 = new ModelAndView(ViewRouteHelpers.USER_VERIFICARCLAVE);
			return mAV2;
		}else{
			claveTemporalService.remove(clave);
		}
   	 return mAV;
    }
    

}
	

