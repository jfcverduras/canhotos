/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.grupocampanha.canhoto;





/**
 *
 * @author jfc
 */
public class XmlReader {
    public static String cdataRemove(String string) {

        if (string.startsWith("<![CDATA[")) {
            String retorno = string.replace("<![CDATA[", "");
            return retorno.substring(0, retorno.length() - 3);
        }
        
        return string;
    }
}
