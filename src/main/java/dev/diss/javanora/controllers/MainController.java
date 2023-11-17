package dev.diss.javanora.controllers;

import dev.diss.javanora.service.SpotifyURLService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class MainController {
    private final SpotifyURLService spotifyURLService;

    @GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
    public String showHome(final Model model) {
        model.addAttribute("url", spotifyURLService.getAuthorizationURL());
        return "Hello";
    }
}
