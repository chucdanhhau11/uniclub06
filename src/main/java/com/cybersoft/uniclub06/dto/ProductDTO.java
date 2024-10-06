package com.cybersoft.uniclub06.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ProductDTO implements Serializable {
    private int id;
    private String linkhinh;
    private String name;
    private double price;
    private String overview;
    private List<String> categories;
    private List<String> tags;
    private List<SizeDTO> sizes;
    private List<ColorDTO> colors;
    private List<ColorDTO> priceColorSize;


}
