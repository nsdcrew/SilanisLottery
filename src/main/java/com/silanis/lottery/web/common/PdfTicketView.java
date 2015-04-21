package com.silanis.lottery.web.common;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;
import com.silanis.lottery.bean.Ticket;

public class PdfTicketView extends AbstractPdfView{
	 
		@Override
		protected void buildPdfDocument(Map model, Document document,
			PdfWriter writer, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	 
			Ticket ticketData = (Ticket) model.get("ticketData");
			
			Table table = new Table(2);
			table.addCell("Lotery Silanis");
			table.addCell("");
			table.addCell("Ticket Number");
			table.addCell(ticketData.getIdTicket().toString());
			table.addCell("Participant name");
			table.addCell(ticketData.getParticipantName());
			table.addCell("Draw number");
			table.addCell(ticketData.getIdDraw().toString());
			table.addCell("bying date");
			table.addCell(ticketData.getInscriptionDate().toString());
			table.addCell("price");
			table.addCell(ticketData.getPrice().toString());
			document.add(table);
		}
	}