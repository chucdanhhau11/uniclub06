package com.cybersoft.uniclub06.service.imp;

import com.cybersoft.uniclub06.dto.ColorDTO;
import com.cybersoft.uniclub06.dto.ProductDTO;
import com.cybersoft.uniclub06.dto.SizeDTO;
import com.cybersoft.uniclub06.entity.*;
import com.cybersoft.uniclub06.repository.ColorRepository;
import com.cybersoft.uniclub06.repository.ProductRepository;
import com.cybersoft.uniclub06.repository.SizeRepository;
import com.cybersoft.uniclub06.repository.VariantRepository;
import com.cybersoft.uniclub06.request.AddProductRequest;
import com.cybersoft.uniclub06.service.FileService;
import com.cybersoft.uniclub06.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceIMP implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private VariantRepository variantRepository;

    @Autowired
    private FileService fileService;

    @Autowired
    private SizeRepository sizeRepository;

    @Autowired
    private ColorRepository colorRepository;

    @Transactional // anotation (giúp RollBack) này nếu thêm cột variant ở sau không được thì nó xóa luôn dữ liệu product vừa thêm chỉ dành cho tính năng thêm xóa sửa
    @Override
    public void addProduct(AddProductRequest request) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(request.name());
        productEntity.setDescription(request.desc());
        productEntity.setPrice(request.price());
        productEntity.setInformation(request.information());

        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setId(request.idBrand());

        productEntity.setId_brand(brandEntity);

        ProductEntity productInserted = productRepository.save(productEntity);

        VariantEntity variantInserted = new VariantEntity();
        variantInserted.setId_product(productInserted);

        ColorEntity colorEntity = new ColorEntity();
        colorEntity.setId(request.idColor());
        variantInserted.setColor(colorEntity);

        SizeEntity sizeEntity = new SizeEntity();
        sizeEntity.setId(request.idSize());
        variantInserted.setSize(sizeEntity);
        variantInserted.setPrice(request.priceSize());
        variantInserted.setQuantity(request.quantity());
        variantInserted.setImages(request.files().getOriginalFilename());

        variantRepository.save(variantInserted);

        fileService.saveFile(request.files());

    }

    @Override
    public List<ProductDTO> getProducts(int numPage) {

        Pageable page = PageRequest.of(numPage,4);
        return productRepository.findAll(page).stream().map(item -> {
            ProductDTO productDTO = new ProductDTO();
            try {

                productDTO.setId(item.getId());
                productDTO.setName(item.getName());
                productDTO.setLinkhinh("http://localhost:8080/file/" + item.getVariantEntities().getFirst().getImages());
                productDTO.setPrice(item.getPrice());
            }catch (Exception e){
                System.out.println("null rồi  " + e.getMessage());
            }
            return productDTO;
        }).toList();
    }

    @Override
    public ProductDTO getDetailProduct(int id) {

        Optional<ProductEntity> optionProductEntity = productRepository.findById(id);

        return optionProductEntity.stream().map(productEntity -> {
            ProductDTO productDTO = new ProductDTO();

            productDTO.setId(productEntity.getId());
            productDTO.setName(productEntity.getName());
            productDTO.setPrice(productEntity.getPrice());
            productDTO.setCategories(productEntity.getProductCategories().stream().map(productCategory ->
                productCategory.getCategory().getName()
            ).toList());

//            productDTO.setSizes(productEntity.getVariantEntities().stream().map(variantEntity -> {
//                SizeDTO sizeDTO = new SizeDTO();
//                sizeDTO.setId(variantEntity.getSize().getId());
//                sizeDTO.setName(variantEntity.getSize().getName());
//                return sizeDTO;
//            }).toList());

            productDTO.setSizes(sizeRepository.findAll().stream().map(sizeEntity -> {
                SizeDTO sizeDTO = new SizeDTO();
                sizeDTO.setId(sizeEntity.getId());
                sizeDTO.setName(sizeEntity.getName());

                return sizeDTO;

            }).toList());

            productDTO.setPriceColorSize(productEntity.getVariantEntities().stream().map(variantEntity -> {

                ColorDTO colorDTO = new ColorDTO();
                colorDTO.setImages(variantEntity.getImages());
                colorDTO.setId(variantEntity.getColor().getId());
                colorDTO.setName(variantEntity.getColor().getName());

                colorDTO.setSizes(productEntity.getVariantEntities().stream().map(variantEntity1 -> {

                    SizeDTO sizeDTO = new SizeDTO();
                    sizeDTO.setId(variantEntity1.getSize().getId());
                    sizeDTO.setName(variantEntity1.getSize().getName());
                    sizeDTO.setQuantity(variantEntity1.getQuantity());
                    sizeDTO.setPrice(variantEntity1.getPrice());

                    return sizeDTO;

                }).toList());

                return colorDTO; // hôm bửa tới đây
            }).toList());

            productDTO.setColors(colorRepository.findAll().stream().map(colorEntity -> {

                ColorDTO colorDTO = new ColorDTO();
                colorDTO.setId(colorEntity.getId());
                colorDTO.setName(colorEntity.getName());

                return colorDTO;

            }).toList());

            return productDTO;
        }).findFirst().orElseThrow(() -> new RuntimeException("Không tìm thấy dữ liệu"));


    }

}
