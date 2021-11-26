/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.grupocampanha.canhoto;

import br.com.grupocampanha.xml.XML;
import br.com.grupocampanha.xml.exceptions.UnformattedXmlException;
import java.io.File;

/**
 *
 * @author jfc
 */
class Config {

    static String getServerUrl() throws Exception {
        try {
            return XML.parse(new File("./Config.xml")).node.find(f -> f.getNome().equalsIgnoreCase("serverURL")).getValor();
        } catch (Exception e) {
            throw new Exception("FILE ./Config.xml nao existe ou node serverURL nao existe");
        }
    }

}
