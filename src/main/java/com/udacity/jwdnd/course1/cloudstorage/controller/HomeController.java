package com.udacity.jwdnd.course1.cloudstorage.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
	private Logger logger = LoggerFactory.getLogger(HomeController.class);

    private AuthorizationService authorizationService;
    private NoteService noteService;
    private CredentialService credentialService;
    private FileService fileService;

    public HomeController(
            AuthorizationService authorizationService,
            NoteService noteService,
            CredentialService credentialService,
            FileService fileService
    ) {

        this.authorizationService = authorizationService;
        this.noteService = noteService;
        this.credentialService = credentialService;
        this.fileService = fileService;
    }

    @GetMapping("/home")
    public String getHomepage(
            @ModelAttribute("userNoteVO") UserNoteVO userNoteVO,
            @ModelAttribute("userCredentialVO") UserCredentialVO userCredentialVO,
            Authentication authentication,
            Model model
    ) {

        String username = (String) authentication.getPrincipal();

        Map<String, Object> data = new HashMap<>();

        data.put("noteList", this.noteService.getNotesByUsername(username));
        data.put("credentialList", this.credentialService.getCredentialsByUsername(username));
        data.put("fileList", this.fileService.getFilesByUser(username));

        model.addAllAttributes(data);

        return "home";
    }

    @GetMapping("/logout")
    public String logOut(
            @ModelAttribute("userVo") UserVO userVo,
            Model model
    ) {

        this.logger.error("logout");

        return this.loginPage(userVo, false, true, model);
    }

    @GetMapping("/login")
    public String loginPage(
            @ModelAttribute("userVo") UserVO userVo,
            @RequestParam(required = false, name = "error") Boolean errorValue,
            @RequestParam(required = false, name = "loggedOut") Boolean loggedOut,
            Model model
    ) {

        Boolean hasError = (errorValue == null)? false : errorValue;
        Boolean isLoggedOut = (loggedOut == null)? false : loggedOut;

        Map<String, Object> data = new HashMap<>();

        data.put("toLogin", true);
        data.put("loginSuccessfully", false);
        data.put("hasError", hasError);
        data.put("isLoggedOut", isLoggedOut);

        model.addAllAttributes(data);

        return "login";
    }

    @GetMapping("/signup")
    public String signupForm(
            @ModelAttribute("userVo") UserVO userVo,
            Model model
    ) {

        Map<String, Object> data = new HashMap<>();

        data.put("toSignUp", true);
        data.put("signupSuccessfully", false);
        data.put("hasError", false);

        model.addAllAttributes(data);

        return "signup";
    }

    @PostMapping("/signup")
    public String signupSubmit(
            @ModelAttribute("userVo") UserVO userVo,
            Model model
    ) {

        this.logger.error("Received user info from Signup Form: " + userVo.toString());

        Map<String, Object> data = new HashMap<>();

        if (!this.authorizationService.signupUser(userVo)) {

            data.put("toSignUp", true);
            data.put("signupSuccessfully", false);
            data.put("hasError", true);
        } else {
            data.put("toSignUp", false);
            data.put("signupSuccessfully", true);
            data.put("hasError", false);
        }

        model.mergeAttributes(data);

        return "signup";
    }

    @GetMapping("/result")
    public String showResult(
            Authentication authentication,
            @RequestParam(required = false, name = "isSuccess") Boolean isSuccess,
            @RequestParam(required = false, name = "errorType") Integer errorType,
            Model model
    ) {

        Map<String, Object> data = new HashMap<>();

        data.put("isSuccess", isSuccess);
        data.put("errorType", errorType);

        model.addAllAttributes(data);

        return "result";
    }
}
