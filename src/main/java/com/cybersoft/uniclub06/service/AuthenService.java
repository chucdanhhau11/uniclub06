package com.cybersoft.uniclub06.service;

import com.cybersoft.uniclub06.dto.RoleDTO;
import com.cybersoft.uniclub06.entity.RoleEntity;
import com.cybersoft.uniclub06.request.AuthenRequest;

import java.util.List;

public interface AuthenService {
    List<RoleDTO> checkLogin(AuthenRequest request);
}
