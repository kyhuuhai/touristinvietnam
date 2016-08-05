package guru.tour.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import guru.tour.entity.UserEntity;
import guru.tour.service.UserEntityManager;
import jdk.nashorn.internal.ir.RuntimeNode.Request;

@RestController
@RequestMapping(value = "/")
public class ServiceFriendsController {
	@Autowired
	UserEntityManager user;
	
	@RequestMapping(value = "/findPhone/{phone}", method = RequestMethod.GET)
	public ResponseEntity<List<UserEntity>> findPhone(@PathVariable("phone") String phone) {
		List<UserEntity> users=null;
			 users= user.getUserByPhone(phone);
		
		
		for (UserEntity user : users) {
			System.out.println("First Name " + user.getUsername() + " Phone: " + user.getPhone());
		}
		// model.addObject("allEmployees",employees);
/*		ModelAndView model = new ModelAndView("findfriend");
		model.addObject("lists", users);*/

		return new ResponseEntity<List<UserEntity>>(users, HttpStatus.OK);
		// return "listEmployeeView";
	}
	@RequestMapping(value = "/getall", method = RequestMethod.GET)
	public ResponseEntity<List<UserEntity>> getall() {
		List<UserEntity> users=null;
			 users= user.getAll();

		return new ResponseEntity<List<UserEntity>>(users, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getall", method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody UserEntity userEntity,    UriComponentsBuilder ucBuilder) {
        System.out.println("Creating User " + userEntity.getUsername());
 
        if (user.isUserExist(userEntity)) {
            System.out.println("A User with name " + userEntity.getUsername() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
 
        user.saveUser(userEntity);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/findPhone/{phone}").buildAndExpand(userEntity.getUsername()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
	
}
