/**
 * TNTConcept Easy Enterprise Management by Autentia Real Bussiness Solution S.L.
 * Copyright (C) 2007 Autentia Real Bussiness Solution S.L.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 * 
 */
package com.autentia.tnt.upload.impl;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.*;
import org.apache.myfaces.custom.fileupload.UploadedFile;

import com.autentia.tnt.upload.*;
import com.autentia.tnt.util.*;

/**
 * Intraweb default uploader: places files in the path described in
 * configuration file.
 * 
 * @author ivan
 */
public class DefaultUploader implements Uploader {

	/** path where files must be stored */
	private String path;

	/**
	 * Constructor
	 * 
	 * @param object
	 *            object the file is associated to
	 */
	public DefaultUploader(String object) {
		path = ConfigurationUtil.getDefault().getUploadPath() + object
				+ File.separator;
	}

	/** */
	public void store(String id, UploadedFile file) throws IOException {
		byte[] buffer = new byte[65536];
		int nr;

		final InputStream in = file.getInputStream();
		final OutputStream out = new FileOutputStream(getFilePath(id)
				+ FileUtil.getFileName(file.getName()));

		while ((nr = in.read(buffer)) != -1) {
			out.write(buffer, 0, nr);
		}

		in.close();
		out.close();
	}

	/** */
	public void replace(String id, String oldFile, UploadedFile newFile)
			throws IOException {
		if (oldFile != null) {
			delete(id, oldFile);
		}
		store(id, newFile);
	}

	/** */
	public boolean exists(String id, String file) {
		return new File(getFilePath(id) + file).exists();
	}

	/** */
	public boolean delete(String id, String file) throws IOException {
		final String fullPath = getFilePath(id) + file;
		boolean exists = exists(id, file);
		if (!exists) {
			return false;
		} else {
			boolean ok = new File(fullPath).delete();
			if (!ok) {
				throw new IOException("File could not be deleted: " + fullPath);
			} else {
				return true;
			}
		}
	}

	/** */
	public String version(String id, String oldFile, UploadedFile newFile)
			throws IOException {

		final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-hhmmss");

		if (!exists(id, oldFile)) {
			throw new FileNotFoundException(oldFile + " doesn't exists!");
		}

		if (oldFile == null) {
			store(id, newFile);
			return newFile.getName();
		}

		final String versioned = oldFile.substring(0, oldFile.lastIndexOf('.'))
				+ "_" + sdf.format(new Date())
				+ oldFile.substring(oldFile.lastIndexOf('.'));

		byte[] buffer = new byte[65536];
		int nr;

		final InputStream in = newFile.getInputStream();
		final OutputStream out = new FileOutputStream(getFilePath(id)
				+ FileUtil.getFileName(versioned));

		while ((nr = in.read(buffer)) != -1) {
			out.write(buffer, 0, nr);
		}

		in.close();
		out.close();

		return versioned;
	}

	/**
	 * Get file path and create directories as necessary.
	 * 
	 * @return the file path (directory where it should be stored
	 */
	private String getFilePath(String id) {
		final String filePath = (id == null) ? (path)
				: (path + id + File.separator);
		new File(filePath).mkdirs();
		return filePath;
	}
}
