package com.mert.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOExceptionWithCause;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBookmark;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;

@Controller
@RequestMapping("/testdoc")
public class TestDocumentController {
	
	@RequestMapping(value="/testpdf", method = RequestMethod.GET)
	public ModelAndView TestPdf(String errMsg, String sccMsg) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		
		// Create New Document
		// PDDocument mydocument = new PDDocument();
		
		// Loading an Existing PDF Document
		File file = new File("D:/Temp/test1.pdf");
		PDDocument mydocument = PDDocument.load(file);
		
		// Add Page to Document
		/*
		for (int i = 0; i < 3; i++) {
			PDPage blankPage = new PDPage();
			mydocument.addPage(blankPage);
		}
		*/
		
		// Removing Pages from an Existing Document
		mydocument.removePage(2);
		
		// Retrieving the pages of the document 
		PDPage mypage = mydocument.getPage(1);
		
		// Preparing the Content Stream
		PDPageContentStream contentStream = new PDPageContentStream(mydocument, mypage);
		
		// Begin the Content stream 
		contentStream.beginText();
		
		contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
		contentStream.newLineAtOffset(25, 500);
		String text = "This is the sample document and we are adding content to it.";
		contentStream.showText(text);
		
		//Ending the content stream
		contentStream.endText();
		
		//Closing the content stream
		contentStream.close();
		
		// save document
		mydocument.save("D:/Temp/test1.pdf");
		mydocument.close();
		
		modelAndView.addObject("errMsg", errMsg);
		modelAndView.addObject("sccMsg", sccMsg);
		modelAndView.setViewName("testdoc/testpdf");
		return modelAndView;
	}
	
	@RequestMapping(value="/testword", method = RequestMethod.GET)
	public ModelAndView TestWord(String errMsg, String sccMsg) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		
		// XWPFDocument document = new XWPFDocument(OPCPackage.open("D:/Temp/test2.docx"));
		XWPFDocument document = new XWPFDocument(new FileInputStream("D:/Temp/test2.docx"));
		
		List<XWPFParagraph> paragraphs = document.getParagraphs();
		for (XWPFParagraph paragraph : paragraphs) {
			CTP ctp = paragraph.getCTP();
			List<CTBookmark> bookmarks = ctp.getBookmarkStartList();
			for(CTBookmark bookmark : bookmarks) {
				System.out.println("BOOKMARK");
				System.out.println(bookmark.getName());
				String replacementText = replaceBookmarkedPara(bookmark.getName(), bookmark.getName());
                removeAllRuns(paragraph);
                insertReplacementRuns(paragraph, replacementText);
			}
		}
		
		final FileOutputStream out = new FileOutputStream("D:/Temp/test2b.docx");
		document.write(out);

		document.close();
		
		modelAndView.addObject("errMsg", errMsg);
		modelAndView.addObject("sccMsg", sccMsg);
		modelAndView.setViewName("testdoc/testpdf");
		return modelAndView;
	}
	
	private static String replaceBookmarkedPara(String input, String bookmarkTxt) {
        char[] tmp = input.toCharArray();
        StringBuilder sb = new StringBuilder();
        int bookmarkedCharCount = 0;
        for (int i = 0 ; i < tmp.length ; i++) {
            int asciiCode = tmp[i];
            if (asciiCode == 8194) {
                bookmarkedCharCount ++;
                if (bookmarkedCharCount == 5) {
                    sb.append(bookmarkTxt);
                }
            }
            else {
                sb.append(tmp[i]);
            }
        }
        return sb.toString();
    }
	
	private static void removeAllRuns(XWPFParagraph paragraph) {
        int size = paragraph.getRuns().size();
        for (int i = 0; i < size; i++) {
            paragraph.removeRun(0);
        }
    }
	
	private static void insertReplacementRuns(XWPFParagraph paragraph, String replacedText) {
        String[] replacementTextSplitOnCarriageReturn = StringUtils.split(replacedText, "\n");

        for (int j = 0; j < replacementTextSplitOnCarriageReturn.length; j++) {
            String part = replacementTextSplitOnCarriageReturn[j];

            XWPFRun newRun = paragraph.insertNewRun(j);
            newRun.setText(part);

            if (j+1 < replacementTextSplitOnCarriageReturn.length) {
                newRun.addCarriageReturn();
            }
        }       
    }
	
	@RequestMapping(value="/testexcel", method = RequestMethod.GET)
	public ModelAndView TestExcel(HttpServletResponse response) throws IOException {
		ModelAndView modelAndView = new ModelAndView();
		
		response.setContentType("application/octet-stream");
		String heardeKey = "Content-Disposition";
		String headerValue = "attachment; filename=excelbook1.xlsx";
		response.setHeader(heardeKey, headerValue);
		
		XSSFWorkbook xSSFWorkbook = new XSSFWorkbook();;
		XSSFSheet xSSFSheet = xSSFWorkbook.createSheet("Sheet1");
		
		// Header Row
		Row row = xSSFSheet.createRow(0);
		
		List<String> listHeaderColumn = Arrays.asList("Header Satu", "Header Dua", "Header Tiga");
		
		Integer column = 0;
		for (String headerColumn : listHeaderColumn) {
			Cell cell = row.createCell(column);
			cell.setCellValue(headerColumn);
			column++;
		}
		
		ServletOutputStream servletOutputStream = response.getOutputStream();
		xSSFWorkbook.write(servletOutputStream);
		xSSFWorkbook.close();
		servletOutputStream.close();
		
		modelAndView.setViewName("testdoc/testpdf");
		return modelAndView;
	}
	
}
