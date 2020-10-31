package com.ecommercesports.ecommercesports.implementation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ecommercesports.ecommercesports.entities.TarifaEnvio;
import com.ecommercesports.ecommercesports.repositories.ITarifaEnvioRepository;
import com.ecommercesports.ecommercesports.services.ITarifaEnvioService;

@Service("tarifaEnvioService")
public class TarifaEnvioService implements ITarifaEnvioService{

	@Autowired
    @Qualifier("tarifaEnvioRepository")
    private ITarifaEnvioRepository tarifaEnvioRepository;
	
	
    @Override
	public double getCostoEnvio_2(String empresa, int nroColumna) {
		double precioDelEnvio=0;
		for(TarifaEnvio TEnv : tarifaEnvioRepository.findAll()){
			if(TEnv.getNombre().equalsIgnoreCase(empresa)) {				
				switch (nroColumna) {
					case 1: precioDelEnvio=TEnv.getDe_0_a_05Kg(); break;
					case 2: precioDelEnvio=TEnv.getDe_05_a_1Kg(); break;
					case 3: precioDelEnvio=TEnv.getDe_1_a_2Kg(); break;
					case 4: precioDelEnvio=TEnv.getDe_2_a_3Kg(); break;
					case 5: precioDelEnvio=TEnv.getDe_3_a_5Kg(); break;
					case 6: precioDelEnvio=TEnv.getDe_5_a_10Kg(); break;
					case 7: precioDelEnvio=TEnv.getDe_10_a_15Kg(); break;
					case 8: precioDelEnvio=TEnv.getDe_15_a_20Kg(); break;
					case 9: precioDelEnvio=TEnv.getDe_20_a_25Kg(); break;
				}
			}
		}
	 return precioDelEnvio;
	}
	
}//Fin class
