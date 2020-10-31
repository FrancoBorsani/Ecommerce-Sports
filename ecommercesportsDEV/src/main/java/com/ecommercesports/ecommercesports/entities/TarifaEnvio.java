package com.ecommercesports.ecommercesports.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tarifa_envio")
public class TarifaEnvio {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idEmpresa;
    
    @Column(name = "nombre")
	private String nombre;
    
    @Column(name = "de_0_a_05Kg")
	private double de_0_a_05Kg;
    
    @Column(name = "de_05_a_1Kg")
	private double de_05_a_1Kg;
    
    @Column(name = "de_1_a_2Kg")
	private double de_1_a_2Kg;
    
    @Column(name = "de_2_a_3Kg")
	private double de_2_a_3Kg;
    
    @Column(name = "de_3_a_5Kg")
	private double de_3_a_5Kg;
    
    @Column(name = "de_5_a_10Kg")
	private double de_5_a_10Kg;
    
    @Column(name = "de_10_a_15Kg")
	private double de_10_a_15Kg;
    
    @Column(name = "de_15_a_20Kg")
	private double de_15_a_20Kg;
    
    @Column(name = "de_20_a_25Kg")
	private double de_20_a_25Kg;

	public TarifaEnvio() { }

	public TarifaEnvio(int idEmpresa, String nombre, double de_0_a_05Kg, double de_05_a_1Kg, double de_1_a_2Kg,
			double de_2_a_3Kg, double de_3_a_5Kg, double de_5_a_10Kg, double de_10_a_15Kg, double de_15_a_20Kg,
			double de_20_a_25Kg) {
		super();
		this.idEmpresa = idEmpresa;
		this.nombre = nombre;
		this.de_0_a_05Kg = de_0_a_05Kg;
		this.de_05_a_1Kg = de_05_a_1Kg;
		this.de_1_a_2Kg = de_1_a_2Kg;
		this.de_2_a_3Kg = de_2_a_3Kg;
		this.de_3_a_5Kg = de_3_a_5Kg;
		this.de_5_a_10Kg = de_5_a_10Kg;
		this.de_10_a_15Kg = de_10_a_15Kg;
		this.de_15_a_20Kg = de_15_a_20Kg;
		this.de_20_a_25Kg = de_20_a_25Kg;
	}

	public int getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getDe_0_a_05Kg() {
		return de_0_a_05Kg;
	}

	public void setDe_0_a_05Kg(double de_0_a_05Kg) {
		this.de_0_a_05Kg = de_0_a_05Kg;
	}

	public double getDe_05_a_1Kg() {
		return de_05_a_1Kg;
	}

	public void setDe_05_a_1Kg(double de_05_a_1Kg) {
		this.de_05_a_1Kg = de_05_a_1Kg;
	}

	public double getDe_1_a_2Kg() {
		return de_1_a_2Kg;
	}

	public void setDe_1_a_2Kg(double de_1_a_2Kg) {
		this.de_1_a_2Kg = de_1_a_2Kg;
	}

	public double getDe_2_a_3Kg() {
		return de_2_a_3Kg;
	}

	public void setDe_2_a_3Kg(double de_2_a_3Kg) {
		this.de_2_a_3Kg = de_2_a_3Kg;
	}

	public double getDe_3_a_5Kg() {
		return de_3_a_5Kg;
	}

	public void setDe_3_a_5Kg(double de_3_a_5Kg) {
		this.de_3_a_5Kg = de_3_a_5Kg;
	}

	public double getDe_5_a_10Kg() {
		return de_5_a_10Kg;
	}

	public void setDe_5_a_10Kg(double de_5_a_10Kg) {
		this.de_5_a_10Kg = de_5_a_10Kg;
	}

	public double getDe_10_a_15Kg() {
		return de_10_a_15Kg;
	}

	public void setDe_10_a_15Kg(double de_10_a_15Kg) {
		this.de_10_a_15Kg = de_10_a_15Kg;
	}

	public double getDe_15_a_20Kg() {
		return de_15_a_20Kg;
	}

	public void setDe_15_a_20Kg(double de_15_a_20Kg) {
		this.de_15_a_20Kg = de_15_a_20Kg;
	}

	public double getDe_20_a_25Kg() {
		return de_20_a_25Kg;
	}

	public void setDe_20_a_25Kg(double de_20_a_25Kg) {
		this.de_20_a_25Kg = de_20_a_25Kg;
	}

	@Override
	public String toString() {
		return "TarifaEnvio [idEmpresa=" + idEmpresa + ", nombre=" + nombre + ", de_0_a_05Kg=" + de_0_a_05Kg
				+ ", de_05_a_1Kg=" + de_05_a_1Kg + ", de_1_a_2Kg=" + de_1_a_2Kg + ", de_2_a_3Kg=" + de_2_a_3Kg
				+ ", de_3_a_5Kg=" + de_3_a_5Kg + ", de_5_a_10Kg=" + de_5_a_10Kg + ", de_10_a_15Kg=" + de_10_a_15Kg
				+ ", de_15_a_20Kg=" + de_15_a_20Kg + ", de_20_a_25Kg=" + de_20_a_25Kg + "]";
	}
    
}//Fin class
