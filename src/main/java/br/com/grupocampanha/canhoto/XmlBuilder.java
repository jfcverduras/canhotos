/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupocampanha.canhoto;


/**
 *
 * @author jfc
 */
public class XmlBuilder {

    public static String requestBody(String module, String body, boolean jason) {

        StringBuilder sb = new StringBuilder();
        if (jason) {
            sb.append("<serviceName>");
            sb.append(module);
            sb.append("</serviceName>");
        } else {
            sb.append("<serviceRequest serviceName='");
            sb.append(module);
            sb.append("'>");
        }
        
        sb.append("<requestBody>");
        sb.append(body);
        sb.append("</requestBody>");
        
        if (!jason) 
            sb.append("</serviceRequest>");
        
        return sb.toString();
    }

    
}
