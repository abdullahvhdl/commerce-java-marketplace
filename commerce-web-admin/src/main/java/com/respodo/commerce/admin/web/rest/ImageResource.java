package com.respodo.commerce.admin.web.rest;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.activation.FileTypeMap;
import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Method;
import org.imgscalr.Scalr.Mode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

/**
 * REST controller for managing Image.
 */
@Controller
public class ImageResource {

	private final Logger log = LoggerFactory.getLogger(ImageResource.class);
	@Resource
	private Environment env;

	@RequestMapping(value = "/image/**/{file_name:.+}", method = RequestMethod.GET)
	public void getFile(@RequestParam Integer height,
			@RequestParam Integer width,
			@PathVariable("file_name") String fileName,
			HttpServletResponse response, HttpServletRequest request) {
		try {
			//fileName=path+fileName;
			// get your file as InputStream
			String filePath=request.getRequestURI();
			filePath=filePath.substring(filePath.lastIndexOf("image/") + 6,filePath.length()-fileName.length());
			File baseDirectory = new File(
					env.getRequiredProperty("commerce.image.directory"));
			if (!baseDirectory.exists()) {
				baseDirectory.mkdirs();

			}

			File file = new File(baseDirectory, filePath+fileName);
			if (!file.exists()) {
				return;
			}
			String fileNameWithoutExt = FilenameUtils.getBaseName(file
					.getName());

			String resizedFileName =filePath+fileNameWithoutExt + "_" + height + "_"
					+ width + "." + FilenameUtils.getExtension(fileName);

			File resizedFile = new File(baseDirectory, resizedFileName);
			if (!resizedFile.exists()) {
				BufferedImage img = ImageIO.read(file); // load image

				BufferedImage thumbImg = Scalr.resize(img, Method.QUALITY,
						Mode.AUTOMATIC, width, height, Scalr.OP_ANTIALIAS);
				// convert bufferedImage to outpurstream
				ByteArrayOutputStream os = new ByteArrayOutputStream();
				ImageIO.write(thumbImg, FilenameUtils.getExtension(fileName),
						os);

				// or wrtite to a file

				ImageIO.write(thumbImg, FilenameUtils.getExtension(fileName),
						resizedFile);
			}
			InputStream is = new FileInputStream(resizedFile);
			String contentType = FileTypeMap.getDefaultFileTypeMap()
					.getContentType(resizedFile);
			response.setContentType(contentType);
			// copy it to response's OutputStream
			org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
			response.flushBuffer();
		} catch (IOException ex) {
			log.info("Error writing file to output stream. Filename was '{}'",
					fileName, ex);
			throw new RuntimeException("IOError writing file to output stream");
		}

	}
	
	@ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/upload")
    public @ResponseBody Map<String, String> upload(@RequestParam("file") MultipartFile file, @RequestParam(value="path",defaultValue="") String path ) throws IOException {

        byte[] bytes;
        Map<String, String> response=new HashMap<String, String>();
        
        if (!file.isEmpty()) {
             bytes = file.getBytes();
             File baseDirectory = new File(
 					env.getRequiredProperty("commerce.image.directory")+File.separator+path);
 			if (!baseDirectory.exists()) {
 				baseDirectory.mkdirs();
 				
 			}
 			File outputFile=new File(baseDirectory,file.getOriginalFilename());
 			OutputStream output=new FileOutputStream(outputFile);
 	        output.write(bytes);
 	        response.put("fileUrl", "image"+File.separator+path+File.separator+file.getOriginalFilename());
        }
        
        
        return response;
    }

}
