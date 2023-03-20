package com.example.gazelec.sport.controllers;



import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.gazelec.sport.models.User;
import com.example.gazelec.sport.respositories.UserRepository;
import com.example.gazelec.sport.services.UserService;



@RequestMapping ("/utilisateurs")
@CrossOrigin("*")
@RestController
public class UserController {
    
	@Autowired
	private UserRepository utilRepo ;
	@Autowired 
	private UserService userService ; 
	@Autowired
    private JavaMailSender javaMailSender;
	
	@PostMapping("/ajouter")
	public User AjouterUtilisateur (@RequestBody User user ) {
		return userService.AjouterUtilisateur(user);
	}
	@GetMapping("/Consulter")
	public  List<User> ListerUtilisateurs (){
		return userService.ConsulterUtilisateurs(); 
	}
	@GetMapping ("Rechercher/{id}")
	public User ConsulterUtilisateur ( @PathVariable Long id) {
		return userService.ConsulterUtilById(id);
	}
	@PutMapping ("Modifier/{id}")
	public User ModifierUtilisateur (@RequestBody User user )
	{
		return userService.ModifierUtilisateur(user);
	}
	@DeleteMapping ("Supprimer/{id}")
	public void SupprimerUtilisateur (@PathVariable Long id )
	{
		userService.SupprimeUtilById(id); 
	}
	  @GetMapping("/ListeDesUtilisateurs/{role}")
	     List<User> ListeParRole( @PathVariable String role){
	        return userService.ListeParRole(role);
	   }
	  @GetMapping("/RechercherUtilisateur/{role}/{critere}")
	     List<User> ChercherUtilisateur( @PathVariable String role ,@PathVariable String critere ){
	        return userService.RechercherUtilisateur(role ,critere );
	   }
	  @GetMapping("/ListeAvecDisciplines/{role}")
	     List<User> UtilisateurEtDiscipline( @PathVariable String role  ){
	        return userService.UtilisateursEtDiscipline(role);
	   }
	  
	  
	  @GetMapping("/forgot-password/{email}")
	    public boolean findUserByemail(@PathVariable String email )  {
		    boolean exist=true;
	        String token = UUID.randomUUID().toString();
	       Optional <User> user = userService.FindUserByMail(email);
	        if (!user.isPresent()) {
	            System.out.println("Utilisateur n'existe pas");
	            exist=false;
	        }
	        else {
	        	 
	        // Generate password reset token
	        
	       User  userr = user.get();
	       userr.setResetPasswordToken(token);
	        userService.AjouterUtilisateur(userr);
	        System.out.println("Utilisateur "+userr.getEmail()); 
	        
	        String Url = "http://localhost:4200/resetPassword?token=" + token;
	        // Send password reset email
	        SimpleMailMessage mail = new SimpleMailMessage();
	        mail.setFrom("gazelecprojet@gmail.com");
	        mail.setTo(userr.getEmail());
	        mail.setSubject("Récupération du mot de passe");
	        mail.setText("Pour récupérer le mot de passe cliquez sur ce lien : \n " + Url );
	        System.out.println("Mail "+mail);
	        javaMailSender.send(mail);
	       
	        }   return exist;
	    }

	
}