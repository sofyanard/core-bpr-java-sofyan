package com.mert.service;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mert.model.NasabahDokumen;
import com.mert.repository.NasabahDokumenRepository;

@Service
public class NasabahDokumenService {
	
	@Autowired
	private NasabahDokumenRepository nasabahDokumenRepository;
	
	// Parameter upload.folder di application.properties
	@Value("${upload.folder}")
    private String uploadFolder;
	
	
	
	public List<NasabahDokumen> listByNonasabah(Long nonasabah) {
		return nasabahDokumenRepository.findByNonasabah(nonasabah);
	}
	
	public NasabahDokumen findById(Integer id) {
		return nasabahDokumenRepository.findOne(id);
	}
	
	public void save(NasabahDokumen nasabahDokumen) {
		nasabahDokumenRepository.save(nasabahDokumen);
	}
	
	public void delete(Integer id) {
		nasabahDokumenRepository.delete(id);
	}
	
	
	
	public String fileUpload(MultipartFile uploadFile) throws Exception {
		byte[] bytes = uploadFile.getBytes();
		Path path = Paths.get(uploadFolder + uploadFile.getOriginalFilename());
		Files.write(path, bytes);
		return uploadFile.getOriginalFilename();
		/* Catatan:
		 * ini masih asal upload, kalau ada nama file yg sama bakal ketimpa,
		 * belum dibatasi file size, extension / content-type dll */
	}
	
	public Resource fileDownload(String filename) {
		try {
            Path path = Paths.get(uploadFolder + filename);
            Resource resource = new UrlResource(path.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
            	throw new FileNotFoundException("Could not read file: " + filename);
            }
        }
        catch (Exception e) {
        	return null;
        }
	}

}
