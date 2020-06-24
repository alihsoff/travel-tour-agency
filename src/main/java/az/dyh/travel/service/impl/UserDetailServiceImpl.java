package az.dyh.travel.service.impl;

import az.dyh.travel.bean.CustomUserDetail;
import az.dyh.travel.entity.User;
import az.dyh.travel.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;


@Service("userDetailServiceImpl")
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        List<GrantedAuthority> roles = Collections.singletonList(new SimpleGrantedAuthority(user.getRole()));

        return new CustomUserDetail(
                user,
                user.getUsername(),
                user.getPassword(),
                roles
        );
    }

}
