package study.packetFormat.model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GzipUtil {
	private static int BUFFER = 1024;
	
	public static byte[] compress(byte[] src) throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		GZIPOutputStream gos = new GZIPOutputStream(bos);
		gos.write(src, 0, src.length);
		gos.close();
		return bos.toByteArray();
	}
	
	public static byte[] decompress(byte[] src) throws IOException {
		ByteArrayInputStream bis = new ByteArrayInputStream(src);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		GZIPInputStream gis = new GZIPInputStream(bis);
		int count;   
		byte data[] = new byte[BUFFER];   
		while ((count = gis.read(data, 0, BUFFER)) != -1) {   
			bos.write(data, 0, count);
		}  
		byte[] result = bos.toByteArray();
		bis.close();
		bos.close();
		return result;
	}
}
