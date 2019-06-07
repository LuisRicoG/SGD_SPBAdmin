package com.company.sgdadmin.serviceimp;


import com.company.sgdadmin.entity.DocumentosActivosEntity;
import com.company.sgdadmin.entity.DocumentosAcumuladosEntity;
import com.company.sgdadmin.repository.DocumentosActivosRepository;
import com.company.sgdadmin.service.FileManager;
import com.company.sgdadmin.util.ConstantsSGD;
import com.company.sgdadmin.util.CryptoFiles;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
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
    List<String> filesListInDir = new ArrayList<String>();

    /**
     *
     * @param entidad
     * @param isEncripted
     * @throws IOException
     */
    @Override
    public void downloadFile(DocumentosActivosEntity entidad, Boolean isEncripted) throws IOException, FileNotFoundException {
        String rootPath = ConstantsSGD.HOME;
        File file = new File(rootPath + entidad.getRuta() + entidad.nombre);
        downloadFile(file, isEncripted);
    }

    /**
     *
     * @param entidad
     * @param isEncripted
     * @throws IOException
     * @throws FileNotFoundException
     */
    public void downloadFile(DocumentosAcumuladosEntity entidad, Boolean isEncripted) throws IOException, FileNotFoundException {
        String rootPath = ConstantsSGD.HOME;
        File file = new File(rootPath + entidad.getRuta() + entidad.nombre);
        downloadFile(file, isEncripted);
    }

    private void downloadFile(File file, Boolean isEncripted) throws IOException, FileNotFoundException {
        String mimeType = URLConnection.guessContentTypeFromName(file.getName());
        if (mimeType == null) {
            System.out.println("mimetype is not detectable, will take default");
            mimeType = "application/octet-stream";
        }
        if (!file.exists()) {
            throw new FileNotFoundException();
        }

        if (isEncripted) {
            file = cryptoFiles.processFileEncrypt(file, true);
        }

        if (file != null) {
            System.out.println("mimetype : " + mimeType);

            response.setContentType(mimeType);
            response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + file.getName() + "\""));
            response.setContentLength((int) file.length());

            InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
            FileCopyUtils.copy(inputStream, response.getOutputStream());
            if (isEncripted) {
                cryptoFiles.processFileEncrypt(file, false);
            }
        } else {
            throw new FileNotFoundException();
        }
    }

    @Override
    public void zipFile(String ruta, String nombre) throws IOException {
        filesListInDir.clear();
        File dir = new File(ruta);
        populateFilesList(dir, nombre);
        //now zip files one by one
        //create ZipOutputStream to write to the zip file
        FileOutputStream fos = new FileOutputStream(ruta + File.separator + nombre);
        ZipOutputStream zos = new ZipOutputStream(fos);
        for (String filePath : filesListInDir) {
            System.out.println("Zipping " + filePath);
            //decrypt before zipping
            File file = new File(filePath);
            file = cryptoFiles.processFileEncrypt(file, true);
            //for ZipEntry we need to keep only relative file path, so we used substring on absolute path
            ZipEntry ze = new ZipEntry(filePath.substring(dir.getAbsolutePath().length() + 1, filePath.length()));
            zos.putNextEntry(ze);
            //read the file and write to ZipOutputStream
            FileInputStream fis = new FileInputStream(filePath);
            byte[] buffer = new byte[1024];
            int len;
            while ((len = fis.read(buffer)) > 0) {
                zos.write(buffer, 0, len);
            }
            zos.closeEntry();
            fis.close();
            //encrypt after zipping
            cryptoFiles.processFileEncrypt(file, false);
        }
        zos.close();
        fos.close();
    }

    private void populateFilesList(File dir, String nombre) throws IOException {
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                if (!file.getName().matches(nombre)) {
                    filesListInDir.add(file.getAbsolutePath());
                }
            } else {
                populateFilesList(file, nombre);
            }
        }
    }
}
