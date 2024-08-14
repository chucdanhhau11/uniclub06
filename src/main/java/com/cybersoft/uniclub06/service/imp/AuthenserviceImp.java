package com.cybersoft.uniclub06.service.imp;

import com.cybersoft.uniclub06.dto.RoleDTO;
import com.cybersoft.uniclub06.entity.RoleEntity;
import com.cybersoft.uniclub06.entity.UserEntity;
import com.cybersoft.uniclub06.repository.UserRepository;
import com.cybersoft.uniclub06.request.AuthenRequest;
import com.cybersoft.uniclub06.service.AuthenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Configuration
@Service
public class AuthenserviceImp implements AuthenService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<RoleDTO> checkLogin(AuthenRequest request) {
        List<RoleDTO> roles = new ArrayList<>();

        UserEntity user = userRepository.findUserEntityByEmail(request.email());

        if (user != null && passwordEncoder.matches(request.password(), user.getPassword())) {
            RoleEntity roleEntity = user.getRole();

            RoleDTO roleDTO = new RoleDTO();
            roleDTO.setId(roleEntity.getId());
            roleDTO.setName(roleEntity.getName());

            roles.add(roleDTO);
        }

        return roles;
    }

}
