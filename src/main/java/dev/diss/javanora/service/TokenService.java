package dev.diss.javanora.service;

import dev.diss.javanora.config.SpotifyConfig;
import dev.diss.javanora.dto.TokenDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;

@Service
@RequiredArgsConstructor
@EnableConfigurationProperties(value = SpotifyConfig.class)
public class TokenService {
    private final SpotifyURLService spotifyURLService;
    private final RestTemplate restTemplate;
    private final SpotifyConfig spotifyConfig;
    private static final String URL = "https://accounts.spotify.com/api/token";

    public String getToken(String code) {
        final var props = spotifyConfig.getApp();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("client_id", props.getClientId());
        map.add("grant_type", "authorization_code");
        map.add("code", code);
        map.add("redirect_uri", props.getRedirectURL());
        map.add("code_verifier", spotifyURLService.getCodeVerifier());

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        ResponseEntity<TokenDTO> response = restTemplate.postForEntity(URL, request, TokenDTO.class);
        return response.getBody().getAccess_token();
    }
}
