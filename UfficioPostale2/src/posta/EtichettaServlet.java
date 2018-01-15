package posta;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ClienteBean;
import bean.PacchiBean;
import bean.PostaBean;
import it.unisa.CalcolaDataSped;
import model.PacchiModel;
import model.PostaModel;


//import org.apache.poi.xwpf.usermodel.VerticalAlign;
//import org.apache.poi.xwpf.usermodel.XWPFDocument;
//import org.apache.poi.xwpf.usermodel.XWPFParagraph;
//import org.apache.poi.xwpf.usermodel.XWPFRun;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Jpeg;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

/**Questa servlet ha il compito di costruire il documento pdf che costituisce l’etichetta che il cliente dovrà stampare
 */
@WebServlet("/cliente/EtichettaServlet")
public class EtichettaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EtichettaServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**Metodo chiamato dalla jsp
	 * @param request  nella request devono essere setati i paramentri "cliente" e "codice"
	 * @param response la response che viene restituita
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());

		System.out.println("soono in etichetta servlet");
/*
		response.setContentType("application/vnd.ms-word");
		PrintWriter writer=response.getWriter();
		//response.addHeader("Content-Disposition", "attachment; filename=" + "tizia.pdf");
		//writer.println("tizia");

		ClienteBean cliente= (ClienteBean)request.getSession().getAttribute("cliente");

		String cod= request.getParameter("codice");
		writer.println("codice = " +cod);
		PostaModel modelPo= new PostaModel();
		try {
			int codice=Integer.parseInt(cod);
			PostaBean posta= modelPo.cercaByCodice(codice);
			writer.println("mittente: " + cliente.getNome() +" " + cliente.getCognome());
			writer.println("destinatario : " + posta.getDestinatario());
			writer.println("data di spedizione: "+ posta.getDataSpedizione());

			if(posta.getTipo().equals("pacchi")) {
				PacchiModel modelPa= new PacchiModel();
				PacchiBean pacco=modelPa.cercaPacco(codice);
				writer.println("peso: " +pacco.getPeso());
				writer.println("volume: "+ pacco.getVolume());

				LocalDateTime dataPossibile= CalcolaDataSped.domaniAlle15();
				synchronized (PostaModel.spedizioneDataOra) {
					PostaModel.spedizioneDataOra= CalcolaDataSped.aumenta(PostaModel.spedizioneDataOra);
				}

				if(PostaModel.spedizioneDataOra.isBefore(dataPossibile)) {
					synchronized (PostaModel.spedizioneDataOra) {
						PostaModel.spedizioneDataOra=dataPossibile;
					}
				}

				writer.println("");
				writer.println("orario consigliato per recarsi");
				writer.println("alla posta a spedire il pacco");
				writer.println(PostaModel.spedizioneDataOra + " "+ PostaModel.spedizioneDataOra.getDayOfWeek().toString());
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/error.jsp");
			dispatcher.forward(request, response);
			return;
		}
*/
		//response.setContentLength("tizia".length());

		ClienteBean cliente= (ClienteBean)request.getSession().getAttribute("cliente");
		String cod= request.getParameter("codice");

		PostaModel modelPo= new PostaModel();
		PostaBean posta;
		int codice;
		try {
			codice=Integer.parseInt(cod);
			posta= modelPo.cercaByCodice(codice);
		
		}catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/error.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		
		
		response.setContentType("application/pdf");
	
       try {
            
            Document document = new Document();
            //document.setMargins(30, 120, 120, 120);
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();
            
            Jpeg img=new Jpeg(new URL("http://localhost:8080/UfficioPostale2/immagini/LogoUfficiale.jpg"));
            img.setAbsolutePosition(50, 730); //parte da sotto nn ho capito secondo quale principio
            img.scalePercent(75);
            document.add(img);
            
            //Font font=new Font(FontFamily.COURIER,12.0f);
            ///Font f2= new Font(BaseFont., 12.0f, Font.NORMAL, BaseColor.BLACK);
            Font font= FontFactory.getFont(FontFactory.HELVETICA, 10.0f);
            String formato="%s %-30.30s";
            String riga1=String.format(formato,"mittente:          ", cliente.getNome()+" "+ cliente.getCognome());
            String riga2=String.format(formato,"destinatario:     ", posta.getDestinatario());
            String rigaCod=String.format(formato,"codice              ", posta.getCodice());
            DateFormat dateScad =DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.ITALY);
            String riga3=String.format(formato,"spedizione:      ", dateScad.format(posta.getDataSpedizione()));
            String paragrafo=riga1+"\n"+ riga2+"\n"+ riga3+"\n"+ rigaCod ; 
            System.out.println(riga1);
            System.out.println(riga2);
            System.out.println(riga3);
            Paragraph p2=new Paragraph(paragrafo,font);
            p2.setIndentationLeft(130);
            document.add(p2);
            
            //Paragraph p1=new Paragraph(riga2, font);
            //document.add(p1);
            //document.add(new Paragraph(riga3, font));

            //document.add(new Paragraph("mittente: " + cliente.getNome() +" " + cliente.getCognome()));
            //document.add(new Paragraph("destinatario : " + posta.getDestinatario()));           
            //document.add(new Paragraph("data di spedizione: "+ dateScad.format(posta.getDataSpedizione())));
            
            if(posta.getTipo().equals("pacchi")) {
            	PacchiModel modelPa= new PacchiModel();
				PacchiBean pacco=modelPa.cercaPacco(codice);

				String riga4=String.format(formato,"peso:                ",pacco.getPeso());
				String riga5=String.format(formato,"volume:            ", pacco.getVolume());
	            Paragraph paccoParg= new Paragraph(riga4+"\n"+ riga5 , font);
	            paccoParg.setIndentationLeft(130);
				document.add(paccoParg);
				
				//document.add(new Paragraph("peso: " +pacco.getPeso()));
				//document.add(new Paragraph("volume: "+ pacco.getVolume()));
				
				LocalDateTime dataPossibile= CalcolaDataSped.domaniAlle15();
				synchronized (PostaModel.spedizioneDataOra) {
					PostaModel.spedizioneDataOra= CalcolaDataSped.aumenta(PostaModel.spedizioneDataOra);
				}

				if(PostaModel.spedizioneDataOra.isBefore(dataPossibile)) {
					synchronized (PostaModel.spedizioneDataOra) {
						PostaModel.spedizioneDataOra=dataPossibile;
					}
				}
				Jpeg img2=new Jpeg(new URL("http://localhost:8080/UfficioPostale2/immagini/forbici.jpg"));
				img2.scalePercent(80);
	            document.add(img2);
				Font f=new Font(FontFamily.HELVETICA,10.0f,Font.ITALIC);
				Paragraph p=new Paragraph("orario consigliato per recarsi alla posta a spedire il pacco:",f);
				p.setSpacingBefore(10);
				document.add(p);
				String str=PostaModel.spedizioneDataOra.getDayOfMonth()+"/"+PostaModel.spedizioneDataOra.getMonth().getValue()+"/" +PostaModel.spedizioneDataOra.getYear()+ " "+ PostaModel.spedizioneDataOra.getHour()+ ":"+ PostaModel.spedizioneDataOra.getMinute();
				document.add(new Paragraph(str, f));
            }else {
            	document.add(new Paragraph("\n"));
            	Jpeg img2=new Jpeg(new URL("http://localhost:8080/UfficioPostale2/immagini/forbici.jpg"));
            	img2.scalePercent(80);
            	document.add(img2);
            }
            
            document.close();
            
        } catch (DocumentException | SQLException e) {
        	e.printStackTrace();
			RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/error.jsp");
			dispatcher.forward(request, response);
			return;
        }
		
		
		
	/*	XWPFDocument document = new XWPFDocument();
		
		XWPFParagraph paragraph = document.createParagraph();
		
		XWPFRun paragraphOneRunOne = paragraph.createRun();
	    paragraphOneRunOne.setBold(true);
	    paragraphOneRunOne.setItalic(true);
	    paragraphOneRunOne.setText("Font Style");
	    paragraphOneRunOne.addBreak();
	      
	    XWPFRun paragraphOneRunTwo = paragraph.createRun();
	    paragraphOneRunTwo.setText("Font Style two");
	    paragraphOneRunTwo.setTextPosition(100);
	    
	    document.write(response.getOutputStream());
	*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
