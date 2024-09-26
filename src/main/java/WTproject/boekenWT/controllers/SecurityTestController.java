//package WTproject.boekenWT.controllers;
//
//import WTproject.boekenWT.models.DTO.BookDTO;
//import WTproject.boekenWT.models.LoginDTO;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/securitytest")
////@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class SecurityTestController {
//
//    @PostMapping("/onlytrainer")
////    @PreAuthorize("hasRole('trainer')")
//    public String trainerTest(@RequestBody LoginDTO loginDTO) {
//
//        return loginDTO.getEmail() + " is a trainer and can do this";
//
//    }
//    @PostMapping("/onlytrainee")
//    @PreAuthorize("hasAuthority('trainee')")
//    public String traineeTest(@RequestBody LoginDTO loginDTO) {
//
//        return loginDTO.getEmail() + " is a trainee and can do this";
//
//    }
//}
