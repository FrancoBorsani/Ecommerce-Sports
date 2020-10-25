package com.ecommercesports.ecommercesports.implementation;

import com.ecommercesports.ecommercesports.converters.DescuentoConverter;
import com.ecommercesports.ecommercesports.entities.Descuento;
import com.ecommercesports.ecommercesports.models.DescuentoModel;
import com.ecommercesports.ecommercesports.repositories.IDescuentoRepository;
import com.ecommercesports.ecommercesports.services.IDescuentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("descuentoService")
public class DescuentoService implements IDescuentoService {

    @Autowired
    @Qualifier("descuentoRepository")
    private IDescuentoRepository descuentoRepository;

    @Autowired
    @Qualifier("descuentoConverter")
    private DescuentoConverter descuentoConverter;

    @Override
    public DescuentoModel insertOrUpdate(DescuentoModel descuentoModel) {
        Descuento descuento = descuentoRepository.save(descuentoConverter.modelToEntity(descuentoModel));

        return descuentoConverter.entityToModel(descuento);
    }

    @Override
    public boolean remove(long idDescuento) {
        try {
            descuentoRepository.deleteById(idDescuento);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public DescuentoModel findByIdDescuento(long idDescuento) {
        return descuentoConverter.entityToModel(descuentoRepository.findByIdDescuento(idDescuento));
    }

    @Override
    public DescuentoModel findByCode(String codigo) {
        try {
            return descuentoConverter.entityToModel(descuentoRepository.findByCode(codigo.toLowerCase()));
        }
        catch (Exception e) {
            return descuentoConverter.entityToModel(descuentoRepository.findByIdDescuento(1));
            //e.printStackTrace();
        }
    }
}