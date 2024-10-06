package com.cybersoft.uniclub06.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ColorDTO implements Serializable {
    private int id;
    private String name;
    private String images;
    private List<SizeDTO> sizes;
}
