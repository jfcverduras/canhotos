/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grupocampanha.canhoto;

import br.com.grupocampanha.xml.Node;
import br.com.grupocampanha.xml.XML;
import br.com.grupocampanha.xml.exceptions.InsertNodeValueException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author jfc
 */
public class Sankhya {

    private static String SID;
    private static String idusu;

    public static void login(String usuario, String senha) throws Exception {

        HttpClient cliente = HttpClients.createDefault();
        HttpPost post = new HttpPost(Config.getServerUrl() + "/mge/service.sbr?serviceName=MobileLoginSP.login");
        StringBuilder sb = new StringBuilder();

        sb.append("<NOMUSU>");
        sb.append(usuario);
        sb.append("</NOMUSU>");
        sb.append("<INTERNO>");
        sb.append(senha);
        sb.append("</INTERNO>");

        post.setEntity(new StringEntity(XmlBuilder.requestBody("MobileLoginSP.login", sb.toString(), false)));

        HttpResponse resposta = cliente.execute(post);
        
        if (resposta.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            Node node = XML.parse(EntityUtils.toString(resposta.getEntity()),"temp").getRootNode().find(f -> f.getNome().equals("serviceResponse"));
            if (node.getPropriedade("status").equals("1")) {
                SID = node.find(f -> f.getNome().equals("jsessionid")).getValue();
                String body = "<entity name=\"Usuario\" getPresentations=\"false\">\n"
                        + "<criterio nome=\"NOMEUSU\" valor=\"" + usuario.toUpperCase() + "\" />\n"
                        + "<fields>\n"
                        + "<field name= 'CODUSU'/>\n"
                        + "	</fields>\n"
                        + "</entity>";

                idusu= caller("mge", "crud.find", body, false).find(f -> f.getNome().equalsIgnoreCase("CODUSU")).getValue();
           
            } else {
                byte[] erro = Base64.getDecoder().decode(XmlReader.cdataRemove(node.find(f -> f.getNome().equals("statusMessage")).getValue()));
                throw new Exception(new String(erro));
            }
        } else {
            throw new Exception(resposta.getStatusLine().toString());
        }

    }

    private static Node caller(String module, String service, String body, boolean useJason)throws Exception {
        HttpClient cliente = HttpClients.createDefault();
        HttpPost post = new HttpPost(Config.getServerUrl() + "/" + module + "/service.sbr?serviceName=" + service + "&mgeSession=" + SID);
        
        post.setHeader("Cookie", "JSESSIONID=" + SID);
        
        body = XmlBuilder.requestBody(service, body, useJason);
        
        post.setEntity(new StringEntity(!useJason ? body : org.json.XML.toJSONObject(body).toString()));

        HttpResponse result = cliente.execute(post);
        
        if (result.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String stringResult = EntityUtils.toString(result.getEntity());
            Node resposta;
            
            if (useJason) {
                try {
                    stringResult = org.json.XML.toString(new org.json.JSONObject(stringResult));
                } catch (Exception e) {
                    resposta = XML.parse(stringResult,"temp").getRootNode();
                    throw new Exception(new String(Base64.getDecoder().decode(XmlReader.cdataRemove(resposta.find(f -> f.getNome().equals("statusMessage")).getValue()))));
                }
                stringResult = "<Resposta>" + stringResult + "</Resposta>";
            }
            
            resposta = XML.parse(stringResult,"temp").getRootNode();
            resposta = resposta.find(f -> f.getNome().equals(useJason ? "Resposta" : "serviceResponse"));
            
            if (!useJason && !resposta.getPropriedade("status").equals("1")) 
                throw new Exception(new String( Base64.getDecoder().decode( resposta.find(f -> f.getNome().equals("statusMessage")).getValue().replace(" ",""))));
            
            return resposta;

        } else {
            HttpEntity entidade = result.getEntity();
            
            if (EntityUtils.toString(entidade).isEmpty()) 
                throw new Exception(result.getStatusLine().toString());
             else 
                throw new Exception(EntityUtils.toString(entidade));
            
        }
    }

   public static String importarCabecalho(Cabecalho cab){
       Node body = new Node("sql"){{try {
           add("SELECT NUNOTA FROM TGFCAB cab left join TSIEMP emp on emp.codemp = cab.codemp WHERE emp.CGC = "+cab.getCnpj()+" and cab.numnota = "+cab.getNumeroDaNota()+" and cab.SERIENOTA = "+cab.getSerie()+" AND CAB.TIPMOV = 'V'");
           
       } catch (InsertNodeValueException ex) {
               Logger.getLogger(Sankhya.class.getName()).log(Level.SEVERE, null, ex);
           }
        }};
       
        try {
            Node nunota = caller("mge", "DbExplorerSP.executeQuery",body.toString(),true).find(f->f.getNome().equalsIgnoreCase("array"));
           if(nunota == null )
               return "NOTA NAO EXISTE NO SISTEMA OU CANCELADA";
           
            body = new Node("dataSet"){{
                adicionarPropriedade("rootEntity", "AcompanhamentoNota");
                adicionarPropriedade("includePresentationFields", "S");
                try{
                   Node entity = new Node("entity");
                   entity.adicionarPropriedade("path", "");
                   Node fieldset = new Node("fieldset");
                   fieldset.adicionarPropriedade("list", "*");
                    add(entity);
                   entity.add(fieldset);
                    Node dataRow = new Node("dataRow");
                    add(dataRow);
                   Node localFields= new Node("localFields");
                   dataRow.add(localFields);
                   Node DHOCOR = new Node("DHOCOR");
                   //"<![CDATA[" "]]>"
                   DHOCOR.add(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy 00:00")));
                   localFields.add(DHOCOR);
                   Node HRACT = new Node("HRACT");
                   HRACT.add(LocalTime.now().format(DateTimeFormatter.ofPattern("hhmmss")));
                   localFields.add(HRACT);
                   Node digitado= new Node("DIGITADO");
                   digitado.add("S");
                   localFields.add(digitado);
                   Node nunota2= new Node("NUNOTA");
                   nunota2.add(nunota.getValue());
                   localFields.add(nunota2);
                   Node ocorrencias = new Node("OCORRENCIAS");
                      Node sequencia = new Node("SEQUENCIA");
                      sequencia.add("0");
                    ocorrencias.add("<![CDATA["+cab.getStatus()+"]]>");
                    localFields.add(ocorrencias);
                    localFields.add(sequencia);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }};
            
            Node result = caller("mge","CRUDServiceProvider.saveRecord", body.toString(), false);
            return "ok";
        } catch (Exception ex) {
           return ex.getMessage();
        }
   }
}
