package API;


import ClientRequest.LoginDetails;
import Domain.Users.Member;
import FootballExceptions.UserInformationException;

import SpringControllers.GuestController;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Map;

@RequestMapping("footballapp/guest")
@RestController
public class GuestRestController {

    private final GuestController guestController;
    @Autowired
    public GuestRestController() {
        guestController = new GuestController();
    }

    @GetMapping
    public String get(){
        System.out.println("DSDDASD");
        return "ASDF";
    }

    @PostMapping("/login")
    @JsonIgnore
    public LinkedList<Member> login(@RequestBody Map <String,String> body, final HttpServletResponse response) throws IOException {
        System.out.println("DASD");
        try {

            return guestController.login(body.get("username"),body.get("password"));
        } catch (UserInformationException e) {
           response.sendError(HttpServletResponse.SC_CONFLICT,"Incorrect Login Details");
           return null;
        }
    }


    @GetMapping("/{instance}")
    public String getHomepageByInstance(@PathVariable Member instance){
        return "FSD";
    }

}
