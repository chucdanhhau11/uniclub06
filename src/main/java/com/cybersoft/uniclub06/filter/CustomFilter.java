package com.cybersoft.uniclub06.filter;

import com.cybersoft.uniclub06.dto.AuthorityDTO;
import com.cybersoft.uniclub06.utils.JwtHelper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CustomFilter extends OncePerRequestFilter {

    @Autowired
    private JwtHelper jwtHelper;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authorHeader = request.getHeader("Authorization");

        if (authorHeader != null && authorHeader.startsWith("Bearer ")){
            String token = authorHeader.substring(7);
            String data = jwtHelper.decodeToken(token);

            if (data != null){
                System.out.println("kiemtra " + data);

                List<AuthorityDTO> authorityDTOS = objectMapper.readValue(data, new TypeReference<List<AuthorityDTO>>() {
                });

                // streamAPI
                // map() : Cho phép biến đổi kiểu dữ liệu gốc thành kiểu dữ liệu khác trong quá trình duyệt mảng/đối tượng44
                // chuyển đổi nhanh các phần tử List này sang List khác thay cho nhiều đoạn code bên dưới
                List<SimpleGrantedAuthority> authorityList = authorityDTOS.stream().map(item -> new SimpleGrantedAuthority(item.getAuthority())).toList();

//                List<GrantedAuthority> authorityList = new ArrayList<>();
//                authorityList.forEach(dataDTO -> {
//                    SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(dataDTO.getAuthority());
//                    authorityList.add(simpleGrantedAuthority);
//                });

                UsernamePasswordAuthenticationToken authenToken =
                        new UsernamePasswordAuthenticationToken("","",authorityList);

                SecurityContext context = SecurityContextHolder.getContext();
                context.setAuthentication(authenToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}
