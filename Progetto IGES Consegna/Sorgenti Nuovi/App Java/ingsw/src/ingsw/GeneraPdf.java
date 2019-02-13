package ingsw;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;

/**
 *
 * @author dario
 */
public class GeneraPdf {
    private void scriviPdf(String fileName,String idIngiunzione,  String nome, String cognome, String importo, String mora, String nProtocollo ,String mcTotale) {
        try {  
           Document document = new Document(PageSize.A4);
           PdfWriter.getInstance(document, new FileOutputStream(fileName));
            
            Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 40);
            Font subTitleFont = new Font(Font.FontFamily.TIMES_ROMAN, 25);
            Font textFont = new Font(Font.FontFamily.TIMES_ROMAN, 20);
            Paragraph titolo = new Paragraph("SoftEngineUnina CGI2016", titleFont);
            Paragraph sottoTitolo = new Paragraph("Numero Protocollo: " + nProtocollo, subTitleFont);
            Paragraph object = new Paragraph("A causa di un mancato pagamento delle bollette per la fornitura dell'acqua per un totale di "+mcTotale+" mc, con la presente si sollecita al pagamento per la fornitura suddetta.", textFont);
            Paragraph corpo = new Paragraph("Nome: "+nome+"\n"+"Cognome: "+cognome+"\n"+"idIngiunzione: "+idIngiunzione+"\n"+"Importo: "+importo+"    "+ "mora: "+mora,  textFont);
            
            document.open();
            document.add(titolo);
            document.add(sottoTitolo);
            document.add(object);
            document.add(corpo);
            document.close();
        }
          catch(Exception e){
            throw new RuntimeException();
        }
    }
    
    public void generaPdf(String idIngiunzione, String idUtente, String nome, String cognome, String importo, String mora, String nProtocollo ,String mcTotale) {
        try{
            //Il nome del PDF sarà l'id dell'utente che dovrà pagare l'importo
            String fileName = System.getProperty("user.dir")+"\\"+idUtente+"_"+idIngiunzione+".pdf";
            scriviPdf(fileName,idIngiunzione,nome,cognome,importo,mora,nProtocollo,mcTotale);
        }
        catch(Exception e){
            throw new RuntimeException();
        }
        
    }
    public void generaPdf(String idIngiunzione, String idUtente, String nome, String cognome, String importo, String mora, String mcTotale) {
        try{
            //Il nome del PDF sarà l'id dell'utente che dovrà pagare l'importo
            String fileName = System.getProperty("user.dir")+"\\"+idUtente+"_"+idIngiunzione+".pdf";
            scriviPdf(fileName,idIngiunzione,nome,cognome,importo,mora,"",mcTotale);
        }
        catch(Exception e){
            throw new RuntimeException();
        }
        
    }
}