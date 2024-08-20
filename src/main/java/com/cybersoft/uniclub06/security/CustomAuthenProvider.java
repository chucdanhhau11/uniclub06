package com.cybersoft.uniclub06.security;

import com.cybersoft.uniclub06.dto.RoleDTO;
import com.cybersoft.uniclub06.request.AuthenRequest;
import com.cybersoft.uniclub06.service.AuthenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomAuthenProvider implements AuthenticationProvider {

    @Autowired
    private AuthenService authenService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = authentication.getName(); // username người dùng nhập vào
        String password = authentication.getCredentials().toString(); // password người dùng nhập vào

        // truyền username và password vào AuthenRequest
        AuthenRequest authenRequest = new AuthenRequest(userName, password);

        // trả true fals chứng thực username password tồn tại
        List<RoleDTO> roleDTOS = authenService.checkLogin(authenRequest);

        // kiểm tra biến isSuccess và trả về
        if (roleDTOS.size() > 0) {
            // streamAPI
            // map() : Cho phép biến đổi kiểu dữ liệu gốc thành kiểu dữ liệu khác trong quá trình duyệt mảng/đối tượng44
            // chuyển đổi nhanh các phần tử List này sang List khác thay cho nhiều đoạn code bên dưới
            List<SimpleGrantedAuthority> authorityList = roleDTOS.stream().map(item -> new SimpleGrantedAuthority(item.getName())).toList();

            // vì tham số thứ 3 của UsernamePasswordAuthenticationToken là GrantedAuthority nên phải tạo kiểu GrantedAuthority để gán vô
//            List<GrantedAuthority> authorityList = new ArrayList<>();
//            roleDTOS.forEach(roleDTO -> {
//                SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(roleDTO.getName());
//                authorityList.add(simpleGrantedAuthority);
//            });

            return new UsernamePasswordAuthenticationToken("", "", authorityList);
        } else {
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
