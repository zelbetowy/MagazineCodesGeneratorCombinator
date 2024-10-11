package pl.matelkom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppStarter {

    @Autowired
    private final ProfileRunner profileRunner;





    //Constructor is needed to inject proper version of runner - Desktop or Profile.
    public AppStarter(ProfileRunner profileRunner) {
        this.profileRunner = profileRunner;
    }





    public void start() {
        profileRunner.start();
    }
}
