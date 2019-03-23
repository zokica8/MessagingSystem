package kurs.messaging.util;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import lombok.extern.slf4j.Slf4j;

// writing small program to print records in a 
// csv file using java and apache commons csv library!
@Slf4j
public class CSVApacheFileWriter {
	
	private static final String FILE_LOCATION_USER = 
			"C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/" + StringUtil.USER_CSV;
	private static final String FILE_LOCATION_POST = 
			"C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/" + StringUtil.POST_CSV;
	private static final String FILE_LOCATION_LIKES = 
			"C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/" + StringUtil.LIKE_CSV;
	
	public void writeUsersToCSVFile() throws IOException {
		try(BufferedWriter out = Files.newBufferedWriter(Paths.get(FILE_LOCATION_USER))) {
			try(CSVPrinter printer = new CSVPrinter(out, CSVFormat.EXCEL.withHeader(Headers.class))) {
				printer.printRecord("zokivasilic8@gmail.com", "JavaProgramer100");
				printer.printRecord("goransilbaski@gmail.com", "Goran99");
				printer.printRecord("petarvasilic@yahoo.com", "SchneiderElectric");
				printer.printRecord("bojanvasilic@gmail.com", "CProgramer33");
				printer.printRecord("bojanavasilic@gmail.com", "Pravnik");
				printer.printRecord("bojanstajic@gmail.com", "PrincTotti");
				printer.printRecord("sinisastajic@gmail.com", "Rose365");
				printer.printRecord("milanstankov@yahoo.com", "Wolkabout");
				printer.printRecord("sokarda@gmail.com", "TeslaMania");
				printer.printRecord("bobek@gmail.com", "BobekMania");
				
				printer.flush();
			}
		}
	}
	
	public void writePostsToCSVFile() throws IOException {
		try(BufferedWriter out = Files.newBufferedWriter(Paths.get(FILE_LOCATION_POST))) {
			try(CSVPrinter printer = new CSVPrinter(out, CSVFormat.EXCEL.withHeader(HeadersPost.class))) {
				printer.printRecord("Sadrzaj 1", "2019-03-04 10:10:10", 1);
				printer.printRecord("Sadrzaj 2", "2019-03-04 10:10:11", 2);
				printer.printRecord("Sadrzaj 3", "2019-03-04 10:10:12", 3);
				printer.printRecord("Sadrzaj 4", "2019-03-04 10:10:13", 4);
				printer.printRecord("Sadrzaj 5", "2019-03-04 10:10:14", 5);
				printer.printRecord("Sadrzaj 6", "2019-03-04 10:10:15", 6);
				printer.printRecord("Sadrzaj 7", "2019-03-04 10:10:22", 7);
				printer.printRecord("Sadrzaj 8", "2019-03-04 10:10:32", 8);
				printer.printRecord("Sadrzaj 9", "2019-03-04 10:10:42", 9);
				printer.printRecord("Sadrzaj 10", "2019-03-04 10:10:52", 10);
				
				printer.flush();
			}
		}
	}
	
	public void writeLikesToCSVFile() throws IOException {
		try(BufferedWriter out = Files.newBufferedWriter(Paths.get(FILE_LOCATION_LIKES))) {
			try(CSVPrinter printer = new CSVPrinter(out, CSVFormat.EXCEL.withHeader(HeadersLikes.class))) {
				printer.printRecord(1,1, "2019-03-06 11:12:33");
				printer.printRecord(2,1, "2019-03-06 11:12:33");
				printer.printRecord(3,8, "2019-03-06 11:12:33");
				printer.printRecord(4,2, "2019-03-06 11:12:33");
				printer.printRecord(5,3, "2019-03-06 11:12:33");
				printer.printRecord(6,7, "2019-03-06 11:12:33");
				printer.printRecord(7,5, "2019-03-06 11:12:33");
				printer.printRecord(8,3, "2019-03-06 11:12:33");
				printer.printRecord(9,4, "2019-03-06 11:12:33");
				printer.printRecord(10,3, "2019-03-06 11:12:33");
				printer.printRecord(1,2, "2019-03-06 11:12:33");
				printer.printRecord(1,3, "2019-03-06 11:12:33");
				printer.printRecord(1,4, "2019-03-06 11:12:33");
				printer.printRecord(1,5, "2019-03-06 11:12:33");
				
				printer.flush();
			}
		}
	}
	public static void main(String[] args) {
		
		CSVApacheFileWriter writer = new CSVApacheFileWriter();
		try {
			writer.writeUsersToCSVFile();
			writer.writePostsToCSVFile();
			writer.writeLikesToCSVFile();
			log.info("Write successful!");
		} catch (IOException e) {
			log.info("Writing to CSV not successful!");
			log.info(e.getMessage());
			e.printStackTrace();
		}
	}
}
