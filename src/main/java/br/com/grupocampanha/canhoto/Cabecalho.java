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
    private String serie;
    public Cabecalho(String codigoDeBarras) {
        parse(codigoDeBarras);
    }
    
    private void parse(String codigoDeBarras) {
        this.numeroDaNota = codigoDeBarras.substring(25, 34);
        this.nunAletNota = codigoDeBarras.substring(35, 43);
        this.cnpj = codigoDeBarras.substring(6, 20);
this.serie =  codigoDeBarras.substring(22, 25);
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

    public String getSerie() {
        return this.serie;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
