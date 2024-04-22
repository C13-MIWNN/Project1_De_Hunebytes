package nl.miwnn13.hunebite.hunebytes.HuneBite.services;

import nl.miwnn13.hunebite.hunebytes.HuneBite.model.HunebyteUser;
import nl.miwnn13.hunebite.hunebytes.HuneBite.repositories.HunebyteUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Justin Lamberts
 * Purpose for the class
 **/
@Service
public class HunebyteUserService implements UserDetailsService {
    private final HunebyteUserRepository hunebyteUserRepository;
    private final PasswordEncoder passwordEncoder;

    public HunebyteUserService(HunebyteUserRepository hunebyteUserRepository, PasswordEncoder passwordEncoder) {
        this.hunebyteUserRepository = hunebyteUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return hunebyteUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public void saveUser(HunebyteUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        hunebyteUserRepository.save(user);
    }
    public boolean isNotInitialised(){
        return hunebyteUserRepository.count() == 0;
    }
}
