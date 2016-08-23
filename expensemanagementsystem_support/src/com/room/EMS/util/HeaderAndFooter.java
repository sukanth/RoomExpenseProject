package com.room.EMS.util;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfAction;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import com.room.EMS.constants.ApplicationConstants;

public class HeaderAndFooter extends PdfPageEventHelper{

    protected Phrase header;
    protected PdfPTable footer;
    Font redFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10, Font.BOLD, new CMYKColor(0, 255, 0, 0));
    Font blueFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8, Font.ITALIC, new CMYKColor(255, 0, 0, 0));
    public HeaderAndFooter() {
	//header = new Phrase("**** Header ****");
	footer = new PdfPTable(1);
	footer.setTotalWidth(300);
	footer.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	footer.addCell(new Phrase(new Chunk(ApplicationConstants.COPYRIGHTS,blueFont).setAction(new PdfAction(PdfAction.FIRSTPAGE))));

    }

    public void onEndPage(PdfWriter writer, Document document) {
	PdfContentByte cb = writer.getDirectContent();
	ColumnText.showTextAligned(cb, Element.ALIGN_RIGHT,new Phrase(String.format("%d", writer.getPageNumber()),redFont), (document.right() - document.left())/ 2 + document.leftMargin(),document.top() + 10, 0);
	footer.writeSelectedRows(0, -1,(document.right() - document.left() - 225) / 2+ document.leftMargin(), document.bottom() - 5, cb);

    }

}
