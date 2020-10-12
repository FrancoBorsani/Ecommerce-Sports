package com.ecommercesports.ecommercesports.implementation;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ecommercesports.ecommercesports.entities.Perfil;
import com.ecommercesports.ecommercesports.entities.User;
import com.ecommercesports.ecommercesports.repositories.IPerfilRepository;
import com.ecommercesports.ecommercesports.repositories.IUserRoleRepository;
import com.ecommercesports.ecommercesports.services.IPerfilService;

@Service
public class PerfilService implements IPerfilService{

	@Autowired
	@Qualifier("perfilRepository")
	private IPerfilRepository perfilRepository;
	
	@Autowired
	@Qualifier("userRoleRepository")
	private IUserRoleRepository userRoleRepository;

	
	@Override
	public List<Perfil> getAll() {
		return perfilRepository.findAll();
	}
	
	@Override
	public Perfil findById(int id) {
		return perfilRepository.findById(id);
	}

	@Override
	public boolean remove(int id) {
		try {
			
			perfilRepository.deleteById(id);;
			return true;
		}catch(Exception e) {
			
			return false;
			
		}
	}


	@Override
	public Perfil addNewProfile(User usuario) {
			return perfilRepository.save(new Perfil(usuario.getId(), usuario.getUsername(),userRoleRepository.findByIdUser(usuario.getId()).getRole(),
					usuario.getFirstName(), usuario.getLastName(), usuario.getCellPhone() ,usuario.getEmail()));
	}

	@Override
	public Perfil updateProfile(Perfil profileToModif, String username, MultipartFile imagen , String aboutMe )throws IOException {
		remove(profileToModif.getId());
		//modifico el perfil con el nuevo username
		profileToModif.setUsername(username);
		profileToModif.setAboutMe(aboutMe);
		String nuevaImagen = "id="+String.valueOf(profileToModif.getId())+"_"+ imagen.getOriginalFilename();//agregamos la ruta de la imagen con el id del usuario para poder diferenciarla de las imagenes de otros usuarios
		
		if(profileToModif.getImagen() != null) { //verifico si el perfil ya tiene una imagen
			buscarImagen(profileToModif.getImagen()).delete(); //al encontrar la imagen que contenia la eliminamos de la carpeta
		}
		byte [] imagebBytes = imagen.getBytes();
		Path rutaCompleta = Paths.get(buscarCarpeta()+"//"+ nuevaImagen); //guardamos la nueva imagen en su correspondiente directorio
		Files.write(rutaCompleta, imagebBytes);
		
		remove(profileToModif.getId());
		profileToModif.setImagen(nuevaImagen); 
		return perfilRepository.save(profileToModif); 
	}

	
	File buscarCarpeta() {
		boolean existe = false;
		File directorioBuscado = null;
		
		String sDirectorio = "C://"; //directorio donde se encuentra la carpeta en la que se guardan las imagenes
		File f = new File(sDirectorio);
		if(f.exists()) {
			File[] ficheros = f.listFiles();
			for (int x=0;x < ficheros.length ; x++) {
				if (ficheros[x].getName().equals(new File("c:\\Ecommerce-Sports UsersImg").getName()) ){
					directorioBuscado = ficheros[x];
					existe = true;
				}
			}
		}
		if(!existe) {
			directorioBuscado = new File("c:\\Ecommerce-Sports UsersImg");
			directorioBuscado.mkdir();//creo la carpeta
		}
		return directorioBuscado;
	}
	
	
	File buscarImagen(String imagen) {
		File directorioBuscado = null;
		String sDirectorio = "C://Ecommerce-Sports UsersImg"; //directorio donde se guardan las imagenes de los usuarios
		File f = new File(sDirectorio);
		if(f.exists()) {
			File[] ficheros = f.listFiles();
			for (int x=0;x < ficheros.length ; x++) {
				if (ficheros[x].getName().equals(new File("c:\\Ecommerce-Sports UsersImg\\"+ imagen).getName())){
					directorioBuscado = ficheros[x];	
				}
			}
		}
		return directorioBuscado;
	}
	
}
