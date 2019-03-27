package kurs.messaging.commands;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.servlet.http.Part;

import org.apache.commons.io.FileUtils;

import kurs.messaging.beans.User;
import kurs.messaging.services.UserService;
import kurs.messaging.util.StringUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PictureUploadedCommand extends Command {

	private UserService service;

	public PictureUploadedCommand(String jsp) {
		super(jsp);
	}

	@Override
	public String execute() throws Exception {

		service = new UserService();
		//service.returnConnection();

		User user = (User) request.getSession().getAttribute("user");
		user = service.checkIfImageExistsUpdate(user.getUsername(), user.getImageId());

		String uploadPath = request.getServletContext().getRealPath("") + File.separator + StringUtil.UPLOAD_DIRECTORY;
		log.info(uploadPath);

		File uploadDirectory = new File(uploadPath);
		if (!uploadDirectory.exists()) {
			uploadDirectory.mkdir();
		}

		String fileName = "";
		for (Part part : request.getParts()) {
			fileName = getFileName(part);
			part.write(uploadPath + File.separator + fileName);
			log.info("File : {}", fileName);
		}
		// if it's null, move file with Apache Commons Library 
		if(user.getImageId() == null) {
			File srcFile = new File(uploadPath + File.separator + fileName);
			File destinationFolder = new File(request.getServletContext().getInitParameter("moveDir"));
			FileUtils.moveFileToDirectory(srcFile, destinationFolder, true);
		}
		// if it's not null, move file with Java 7 Files.move
		else {
			Path source = Paths.get(uploadPath + File.separator + fileName);
			Path target = Paths.get(request.getServletContext().getInitParameter("moveDir") + "\\" + fileName);
			Files.move(source, target, StandardCopyOption.REPLACE_EXISTING);
		}
		
		user = service.updateImageForUser(user, fileName);

		return nextPage;
	}

	private String getFileName(Part part) {
		for (String content : part.getHeaderNames()) {
			log.info(content);
		}
		return StringUtil.UNIQUE_PIC_FILENAME;
	}
}
