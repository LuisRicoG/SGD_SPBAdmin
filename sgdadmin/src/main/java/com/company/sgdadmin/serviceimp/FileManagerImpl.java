/*
 * ing.jorge.eduardo.p@gmail.com
 */
package com.company.sgdadmin.serviceimp;

import com.company.sgdadmin.dto.filemanager.FileManagerDTO;
import com.company.sgdadmin.entity.DocumentosActivosEntity;
import com.company.sgdadmin.exceptions.FileNotFoundException;
import com.company.sgdadmin.repository.DocumentosActivosRepository;
import com.company.sgdadmin.service.FileManager;
import com.company.sgdadmin.util.ConstantsSGD;
import com.company.sgdadmin.util.CryptoFiles;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.faces.bean.ManagedBean;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;

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
    @Override
    public void uploading(FileManagerDTO dto) throws FileNotFoundException {
//////////////////////////////////////////////////////////////////////////////
////////////////////SOLO PARA MODO DESARROLLO/////////////////////////////////
//////////////////////////////////////////////////////////////////////////////
        if (!dto.getFile().isEmpty()) {
            File dir = new File(dto.getPath());
            if (!dir.exists()) {
                dir.mkdirs();
            }
//////////////////////////////////////////////////////////////////////////////
            try {
                byte[] bytes = dto.getFile().getBytes();
                Path path = Paths.get(dto.getPath() + dto.getName());
                Files.write(path, bytes);
            } catch (IOException e) {
                throw new FileNotFoundException();
            }

        }
    }

    /**
     *
     * @param entidad
     * @throws IOException
     */
    @Override
    public void downloadFile(DocumentosActivosEntity entidad) throws IOException {
        String rootPath = ConstantsSGD.HOME;
        File file = new File(rootPath + entidad.getRuta() + entidad.nombre);
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

            response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + file.getName() + "\""));

            response.setContentLength((int) file.length());

            InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

            FileCopyUtils.copy(inputStream, response.getOutputStream());
            cryptoFiles.processFileEncrypt(file, false);
        }
    }
}
