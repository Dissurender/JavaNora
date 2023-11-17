package dev.diss.javanora.service;

import dev.diss.javanora.config.SpotifyConfig;
import dev.diss.javanora.util.CodeChallengeUtility;
import dev.diss.javanora.util.CodeVerifierUtility;
import lombok.Data;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

@Data
@Service
@EnableConfigurationProperties(SpotifyConfig.class)
public class SpotifyURLService {
    private final SpotifyConfig spotifyConfig;
    private String codeVerifier;

    public String getAuthorizationURL() {
        final var props = spotifyConfig.getApp();
        final var codeVerifier = CodeVerifierUtility.generate();

        setCodeVerifier(codeVerifier);

        return "https://accounts.spotify.com/en/authorize?"
                + "client_id=" + props.getClientId()
                + "&response_type=code"
                + "&redirect_uri=" + props.getRedirectURL()
                + "&code_challenge_method=S256"
                + "&code_challenge=" + CodeChallengeUtility.generate(codeVerifier)
                + "&scope=ugc-image-upload,user-read-playback-state,user-modify-playback-state,user-read-currently-playing,streaming,app-remote-control,user-read-email,user-read-private"
                + ",playlist-read-collaborative,playlist-modify-public,playlist-read-private,playlist-modify-private"
                + ",user-library-modify,user-library-read,user-top-read,user-read-playback-position,user-read-recently-played,user-follow-read,user-follow-modify";
    }
}
