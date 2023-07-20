package com.pichincha.prueba.rest.dto;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ReportDto {

    @Id
    private int id;
    private String fecha;
    private String cliente;
    private String numero_cuenta;
    private String tipo;
    private boolean estado;
    private String saldo_inicial;
    private String movimiento;
    private String saldo_disponible;

    public ReportDto() {}

    public ReportDto(int id,String fecha, String cliente, String numero_cuenta, String tipo, boolean estado, String saldo_inicial, String movimiento, String saldo_disponible) {
        super();
        this.id = id;
        this.fecha = fecha;
        this.cliente = cliente;
        this.numero_cuenta = numero_cuenta;
        this.tipo = tipo;
        this.estado = estado;
        this.saldo_inicial = saldo_inicial;
        this.movimiento = movimiento;
        this.saldo_disponible = saldo_disponible;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getNumero_cuenta() {
        return numero_cuenta;
    }

    public void setNumero_cuenta(String numero_cuenta) {
        this.numero_cuenta = numero_cuenta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getSaldo_inicial() {
        return saldo_inicial;
    }

    public void setSaldo_inicial(String saldo_inicial) {
        this.saldo_inicial = saldo_inicial;
    }

    public String getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(String movimiento) {
        this.movimiento = movimiento;
    }

    public String getSaldo_disponible() {
        return saldo_disponible;
    }

    public void setSaldo_disponible(String saldo_disponible) {
        this.saldo_disponible = saldo_disponible;
    }
}
