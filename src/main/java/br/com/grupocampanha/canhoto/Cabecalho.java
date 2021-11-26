/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.grupocampanha.canhoto;

/**
 *
 * @author jfc
 */
public class Cabecalho {

    private String numeroDaNota;
    private String nunAletNota;
    private String status;
    private String cnpj;
    
    public Cabecalho(String codigoDeBarras) {
        parse(codigoDeBarras);
    }
    
    private void parse(String codigoDeBarras) {
        this.numeroDaNota = codigoDeBarras.substring(11, 20);
        this.nunAletNota = codigoDeBarras.substring(22, 30);
        this.cnpj = codigoDeBarras.substring(34, 47)+codigoDeBarras.substring(4, 5);
    }

    public String getNumeroDaNota() {
        return numeroDaNota;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getNunAletNota() {
        return nunAletNota;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
