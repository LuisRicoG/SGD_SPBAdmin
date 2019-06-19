package com.company.sgdadmin.util;


/**
 *
 * @author JEPPLAP
 */
import com.company.sgdadmin.entity.ParametrosEntity;
import com.company.sgdadmin.repository.ParametrosRepository;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CryptoFiles {

    @Autowired
    ParametrosRepository repository;
    ParametrosEntity entity;

    private boolean fileProcessor(int cipherMode, String key, File inputFile, File outputFile) {
        try {
            Key secretKey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(cipherMode, secretKey);

            FileInputStream inputStream = new FileInputStream(inputFile);
            byte[] inputBytes = new byte[(int) inputFile.length()];
            inputStream.read(inputBytes);

            byte[] outputBytes = cipher.doFinal(inputBytes);

            FileOutputStream outputStream = new FileOutputStream(outputFile);
            outputStream.write(outputBytes);

            inputStream.close();
            outputStream.close();

            return true;
        } catch (NoSuchPaddingException | NoSuchAlgorithmException
                | InvalidKeyException | BadPaddingException
                | IllegalBlockSizeException | IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public File processFileEncrypt(File file, Boolean isEncrypted) {
        int cyper;
        Boolean isValidDecript;
        cyper = isEncrypted ? Cipher.DECRYPT_MODE : Cipher.ENCRYPT_MODE;        
        entity = repository.findByParametroid(ConstantsSGD.KEY_ID);
        if (entity != null) {
            isValidDecript = fileProcessor(cyper, entity.getValor(), file, file.getAbsoluteFile());
            if (isValidDecript) {
                return file;
            } else {
                return null;
            }
        }
        return null;
    }
}
