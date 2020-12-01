package ua.skidchenko.touristic_agency.controller;


import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import ua.skidchenko.touristic_agency.controller.exceptions.WrongFormInputDataException;
import ua.skidchenko.touristic_agency.dto.UserDTO;
import ua.skidchenko.touristic_agency.exceptions.UsernameExistsExcetion;
import ua.skidchenko.touristic_agency.service.TourService;
import ua.skidchenko.touristic_agency.service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@Log4j2
@RequestMapping("/main")
public class MainPageController {

    final
    TourService tourService;

    final
    UserService userService;

    public MainPageController(TourService tourService, UserService userService) {
        this.tourService = tourService;
        this.userService = userService;
    }

    @GetMapping("")
    public String main() {
        return "redirect:/tours/list/1";
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/new")
    public String newUserPage() {
        log.info("User register page was visited.");
        return "registrationPage";
    }

    @PostMapping("/new")
    public String createNewUser(@Valid UserDTO userDTO,
                                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.warn("Exception during validation of UserDTO" + userDTO.toString());
            throw new WrongFormInputDataException("Entered incorrect data.",
                    getValidationErrors(bindingResult));
        }
        log.info("Saving into DB new user. User data: " + userDTO.toString());
        userService.saveUser(userDTO);
        return "redirect:/main/new/confirm";
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/new/confirm")
    public String confirmCreatingOfUser(Model model) {
        log.info("Redirected to PRG-page.");
        model.addAttribute("message","user.registered");
        model.addAttribute("href","/main/new");
        model.addAttribute("hrefDescription","user.registered.href_description");
        return "registrationPage";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({WrongFormInputDataException.class,
            UsernameNotFoundException.class,
            UsernameExistsExcetion.class})
    public String handleOtherExceptions(RuntimeException exception, Model model) {
        log.warn("Handling exception: " + exception.getMessage());
        model.addAttribute("error", exception.getMessage());
        return "error";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({WrongFormInputDataException.class})
    public String handleValidationExceptions(WrongFormInputDataException exception, Model model) {
        log.warn("Handling exception: " + exception.getErrors());
        model.addAttribute("errors", exception.getErrors());
        return "errors";
    }


    @NotNull
    private List<String> getValidationErrors(BindingResult bindingResult) {
        return bindingResult.
                getAllErrors().
                stream().
                map(ObjectError::getDefaultMessage).
                collect(Collectors.toList());
    }


}