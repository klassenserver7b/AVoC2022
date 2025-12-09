
package de.klassenserver7b.AVoC.A07;

import java.io.File;
import java.nio.file.Path;

/**
**/
 
public class CustomFile {

	private String filename;
	private Path path;
	private int size;
	private boolean isdir;

	/**
	 * @param filename
	 * @param size
	 * @param isdir
	 
 
 
 */	public CustomFile(String filename, int size, boolean isdir) {
		this.filename = filename;
		this.size = size;
		this.isdir = isdir;
		this.path = new File(filename).toPath();
	}

	/**
	 * @param filename
	 * @param path
	 * @param size
	 * @param isdir
	 
 
 
 */	public CustomFile(String filename, Path path, int size, boolean isdir) {
		this.filename = filename;
		this.path = path;
		this.size = size;
		this.isdir = isdir;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}

		boolean ret = false;

		ret = (obj instanceof CustomFile aCFile);

		if (!ret) {
			return ret;
		}

		CustomFile f = (CustomFile) obj;

		ret = filename.equals(f.getFilename()) && size == f.getSize() && isdir == f.isDirectory();

		return ret;
	}

	public boolean isSameDir(CustomFile f) {
		return filename.equals(f.getFilename()) && f.isDirectory() && isdir;
	}

	public String getFilename() {
		return filename;
	}

	public int getSize() {
		return size;
	}

	public boolean isDirectory() {
		return isdir;
	}

	public Path getPath() {
		return path;
	}

}
