/*
 * ing.jorge.eduardo.p@gmail.com
 */
package com.company.sgdadmin.serviceimp;


import com.company.sgdadmin.dto.filemanager.FileManagerDTO;
import com.company.sgdadmin.entity.DocumentosActivosEntity;
import com.company.sgdadmin.repository.DocumentosActivosRepository;
import com.company.sgdadmin.service.FileManager;
import com.company.sgdadmin.util.CryptoFiles;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;    
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import javax.faces.bean.ManagedBean;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

@ManagedBean
public class FileManagerImpl implements FileManager {

    @Autowired
    DocumentosActivosRepository repository;
    @Value("${directorio}")
    private String dirPrincipal;

    @Autowired
    HttpServletResponse response;
    
    @Autowired
    CryptoFiles cryptoFiles;

    //private static final Logger LOGGER = Logger.getLogger(FileManagerImpl.class);
    public void uploading(FileManagerDTO dto) throws IOException {
  if(!dto.getFile().isEmpty()){
            byte[] bytes = dto.getFile().getBytes();
            Path path = Paths.get(dto.getName());
            Files.write(path,bytes);
        }
    }

    /**
     *
     * @param entidad
     * @throws IOException
     */
    @Override
    public void downloadFile(DocumentosActivosEntity entidad) throws IOException {
        //String rootPath = ConstantsSGD.HOME; validar si se incluye la ruta absoluta o la relativa
        File file = new File(entidad.getRuta() + File.separator + entidad.nombre);
        String mimeType = URLConnection.guessContentTypeFromName(file.getName());
        if (mimeType == null) {
            System.out.println("mimetype is not detectable, will take default");
            mimeType = "application/octet-stream";
        }
        if (!file.exists()) {
            String errorMessage = "Sorry. The file you are looking for does not exist";
            System.out.println(errorMessage);
            try (OutputStream outputStream = response.getOutputStream()) {
                outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
            }
            return;
        }

        file = cryptoFiles.processFileEncrypt(file, true);

        if (file != null) {
            System.out.println("mimetype : " + mimeType);

            response.setContentType(mimeType);

            /* "Content-Disposition : inline" will show viewable types [like images/text/pdf/anything viewable by browser] right on browser 
            while others(zip e.g) will be directly downloaded [may provide save as popup, based on your browser setting.]*/
            response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + file.getName() + "\""));

            /* "Content-Disposition : attachment" will be directly download, may provide save as popup, based on your browser setting*/
            //response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
            response.setContentLength((int) file.length());

            InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

            //Copy bytes from source to destination(outputstream in this example), closes both streams.
            FileCopyUtils.copy(inputStream, response.getOutputStream());
        }
    }
}
