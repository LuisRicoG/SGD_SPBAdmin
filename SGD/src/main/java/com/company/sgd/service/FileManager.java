/*
 * ing.jorge.eduardo.p@gmail.com
 */
package com.company.sgd.service;

import com.company.sgd.dto.filemanager.FileManagerDTO;
import java.io.IOException;

/**
 *
 * @author JEPPLAP
 */
public interface FileManager {
public String uploading(FileManagerDTO dto) throws IOException;
}
