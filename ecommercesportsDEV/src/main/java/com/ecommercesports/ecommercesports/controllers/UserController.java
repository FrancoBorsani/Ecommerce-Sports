package com.ecommercesports.ecommercesports.controllers;


import java.net.URL;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.ecommercesports.ecommercesports.converters.CarritoConverter;
import com.ecommercesports.ecommercesports.entities.Carrito;
import com.ecommercesports.ecommercesports.entities.ClaveTemporal;
import com.ecommercesports.ecommercesports.entities.Item;
import com.ecommercesports.ecommercesports.entities.User;
import com.ecommercesports.ecommercesports.entities.UserRole;
import com.ecommercesports.ecommercesports.helpers.ViewRouteHelpers;
import com.ecommercesports.ecommercesports.implementation.CarritoService;
import com.ecommercesports.ecommercesports.implementation.PerfilService;
import com.ecommercesports.ecommercesports.implementation.SendMailService;
import com.ecommercesports.ecommercesports.implementation.UserRoleService;
import com.ecommercesports.ecommercesports.models.ClaveTemporalModel;
import com.ecommercesports.ecommercesports.repositories.IClaveTemporalRepository;
import com.ecommercesports.ecommercesports.repositories.IUserRepository;
import com.ecommercesports.ecommercesports.repositories.IUserRoleRepository;
import com.ecommercesports.ecommercesports.services.IClaveTemporalService;


@Controller	
public class UserController {	
	
	@Autowired
	@Qualifier("claveTemporalService")
	private IClaveTemporalService claveTemporalService;
	
	@Autowired
	@Qualifier("claveTemporalRepository")
	private IClaveTemporalRepository claveTemporalRepository;
	
	@Autowired
	@Qualifier("userRepository")
	private IUserRepository userRepository;
	
	@Autowired
	@Qualifier("userRoleRepository")
	private IUserRoleRepository userRoleRepository;
	
	@Autowired
	@Qualifier("userRoleService")
	private UserRoleService userRoleService;
	
	@Autowired
	@Qualifier("perfilService")
	private PerfilService perfilService;
	
	@Autowired
	@Qualifier("carritoService")
	private CarritoService carritoService;
	
	@Autowired
	@Qualifier("carritoConverter")
	private CarritoConverter carritoConverter;
	
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
			newUSer.setEnabled(true);
			userRepository.save(newUSer);
			userRoleRepository.save(new UserRole(userRepository.findByUsername(newUSer.getUsername()),"ROLE_USER"));			
			
			perfilService.addNewProfile(newUSer);
		}
		
		System.out.println("-----------------------------------------");
		
		System.out.println(newUSer.getFirstName());
		System.out.println(newUSer.getLastName());
		System.out.println(newUSer.getEmail());
		System.out.println(bCryptPasswordEncoder.encode(newUSer.getPassword()));
				
		System.out.println("-----------------------------------------");
		
		Carrito c = new Carrito();
		c.setFecha(LocalDate.now());
		c.setTotal(0);
		c.setUser(newUSer);
		Set<Item> lista = new HashSet<Item>();
		c.setListaItems(lista);
		carritoService.insertOrUpdate(carritoConverter.entityToModel(c));

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
	//  redirectAttrs.addAttribute(new ClaveTemporalModel());
	 mAV.addObject("claveTemporal", new ClaveTemporalModel());
	//	return new RedirectView(ViewRouteHelpers.USER_RECUPERARCLAVE);
	 return mAV;
	}
	

	@GetMapping("/ingresoTemporal")
	public ModelAndView ingresoTemporal() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.USER_VERIFICARCLAVE);
	 return mAV;
	}
	
	
	@GetMapping("/inicio")
	public ModelAndView inicio() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.HOME);
	 return mAV;
	}

    @Autowired
    private SendMailService SendmailService;

   // @GetMapping("/")
   // public String index(){
   //     return "send_mail_view";
  //  }

    @PostMapping("/sendMail")
    public RedirectView sendMail(@RequestParam("correo") String correo, RedirectAttributes redirectAttrs){
    	ModelAndView mAV = new ModelAndView(ViewRouteHelpers.USER_VERIFICARCLAVE);
    
    	boolean band = false;
    	try {
    		User cl = userRepository.findByEmail(correo);
    		
    		if(cl != null) {
    			band = true;
    			
    		}
    	}
    	catch(Exception e) {
    		System.out.println("Error al buscar el usuario: " + e.getMessage());
    	}
    	
    	
    	if(!band) {
    		ModelAndView mAV2 = new ModelAndView(ViewRouteHelpers.USER_RECUPERARCLAVE);
    		redirectAttrs.addFlashAttribute("mensaje","Usuario no registrado");
			redirectAttrs.addFlashAttribute("clase", "danger");
			return new RedirectView("/recuperarclave");
	    //   return mAV2;
			
			//return new RedirectView(ViewRouteHelpers.USER_RECUPERARCLAVE);
		//	return mAV2;
    	}
    	
    	int claveTemporal = (int) (Math.random() * 100000) + 1;
    //	ClaveTemporalModel c = new ClaveTemporalModel(claveTemporalService.getAll().get(claveTemporalService.getAll().size()-1).getId(), claveTemporal);
    //	c.setClave(((int)claveTemporal));
    	ClaveTemporalModel c = new ClaveTemporalModel();
    	c.setClave(claveTemporal);
    	c.setCorreo(correo);
    	claveTemporalService.insertOrUpdate(c);
    	
    	//claveTemporalService.insertOrUpdate(c);
    	String message = "\n\n Datos de contacto: " + "\nE-mail: " + correo + " registrado en Ecommerce Sports." + "\nClave de recuperación: " + claveTemporal;
        String subject = "RECUPERACION DE CLAVE";
        SendmailService.sendMail("proyectodesoftwaretp@gmail.com", "" + correo,subject,message);
      //  return new RedirectView(ViewRouteHelpers.USER_VERIFICARCLAVE);
		
   	   // return mAV;
        return new RedirectView("/ingresoTemporal");
    }
	
    
    @PostMapping("/verificarClave")
    public RedirectView verificarClave(@ModelAttribute("clave") long clave, @RequestParam("password") String password,  RedirectAttributes redirectAttrs ){
    	
    //	ModelAndView mAV = new ModelAndView(ViewRouteHelpers.HOME);
    	
    	
    	int i=0;
		boolean band = false;

		while(i<claveTemporalService.getAll().size() && !band){
			ClaveTemporal c = claveTemporalService.getAll().get(i);
				if(c.getClave() == clave){
					band = true;
				}
			i++;
		}

		if(!band){
			redirectAttrs.addFlashAttribute("mensaje","La clave enviada al mail es incorrecta");
			redirectAttrs.addFlashAttribute("clase", "danger");
			return new RedirectView("/ingresoTemporal");
			
			//ModelAndView mAV2 = new ModelAndView(ViewRouteHelpers.USER_VERIFICARCLAVE);
			//return mAV2;
		}else{
			ClaveTemporal cl = claveTemporalRepository.findByClave((int)clave);
			User u = userRepository.findByEmail(cl.getCorreo());
			BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
			u.setPassword(bCryptPasswordEncoder.encode(password));
			userRepository.save(u);
			claveTemporalService.remove(clave);
		}
	    return new RedirectView("/inicio");
		//	 return mAV;
    }

	@PostMapping("/delete")
	public RedirectView delete(int idUser) {
		userRoleRepository.deleteById(idUser);
		userRepository.deleteById(idUser);
				
		return new RedirectView(ViewRouteHelpers.HOME);
	}

	@GetMapping("/updateProfile")
	public ModelAndView updateProfile() {
		ModelAndView mAV=new ModelAndView(ViewRouteHelpers.USER_UPDATE_USER);
		return mAV;
	}
	@PostMapping("/updateProfilePost")
	public String updateProfilePost(@RequestParam("nuevoUsername") String nuevoUsername 
									,@RequestParam("aboutMe") String aboutMe
									//,@RequestParam("urlImage") String urlImage
									,RedirectAttributes redirectAttrs)  {
    	
		String currentUsername = "";
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			currentUsername = ((UserDetails)principal).getUsername();
		}    	
    	
		User currentUser = userRepository.findByUsername(currentUsername);
		if(userRepository.findByUsername(nuevoUsername) != null) {
			System.out.println("Ya existe un usuario con ese username");
			redirectAttrs.addFlashAttribute("msg", "El nombre de usuario ingresado ya existe");
			redirectAttrs.addFlashAttribute("clase", "danger");
			return "redirect:/updateProfile";
		}		
		currentUser.setUsername(nuevoUsername);
		perfilService.updateProfile(perfilService.findById(currentUser.getId()), nuevoUsername, aboutMe ); //modifico el perfil	
		System.out.println("Perfil editado con exito");
		return "redirect:/logout";
	}	
    @GetMapping("/updateProfile/cancel")
	public String canceUpdateProfile(ModelMap model) {
		return "redirect:/profile";
	}
	
    @GetMapping("/profile")
    public ModelAndView viewProfile() {
    	ModelAndView mAV = new ModelAndView(ViewRouteHelpers.PROFILE_INDEX);
    	
    	String username = "";
    	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	if( principal instanceof UserDetails) {
    		username = ((UserDetails)principal).getUsername();
    	}
    	
    	User currentUser = userRepository.findByUsername(username);
    	mAV.addObject("perfilUser", perfilService.findById(currentUser.getId()));
        return mAV; 
    }
    
    
    @GetMapping("/cambiarClave")
    public ModelAndView cambiarClave() {
    	ModelAndView mAV = new ModelAndView(ViewRouteHelpers.USER_CAMBIARCLAVE);
    	String username = "";
    	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	if( principal instanceof UserDetails) {
    		username = ((UserDetails)principal).getUsername();
    	}
    	
    	User currentUser = userRepository.findByUsername(username);
    	mAV.addObject("perfilUser", perfilService.findById(currentUser.getId()));
    	return mAV;
    }
    @PostMapping("/cambiarClavePost")
    public String cambiarClavePost(@RequestParam("nuevaclave") String password) {    	
    	
    	String username = "";
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
		  username = ((UserDetails)principal).getUsername();
		}    	
    	
		User u = userRepository.findByUsername(username);
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
		u.setPassword(bCryptPasswordEncoder.encode(password));
		userRepository.save(u);
		
		return "redirect:/profile";
    }
    
    
}
	

