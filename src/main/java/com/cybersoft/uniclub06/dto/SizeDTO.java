package com.cybersoft.uniclub06.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class SizeDTO implements Serializable {
    private int id;
    private String name;
    private int quantity;
    private double price;
    private String sku;
}
