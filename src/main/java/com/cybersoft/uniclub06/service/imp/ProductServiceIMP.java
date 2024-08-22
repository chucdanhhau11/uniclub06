package com.cybersoft.uniclub06.service.imp;

import com.cybersoft.uniclub06.entity.*;
import com.cybersoft.uniclub06.repository.ProductRepository;
import com.cybersoft.uniclub06.repository.VariantRepository;
import com.cybersoft.uniclub06.request.AddProductRequest;
import com.cybersoft.uniclub06.service.FileService;
import com.cybersoft.uniclub06.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductServiceIMP implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private VariantRepository variantRepository;

    @Autowired
    private FileService fileService;

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

}
