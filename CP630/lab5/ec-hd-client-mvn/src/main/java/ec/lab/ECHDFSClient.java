package ec.lab;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class ECHDFSClient {
	

	public static void main(String[] args) throws IOException {

		// String hdfsPath = "hdfs://" + args[0] + ":" + args[1];
		String hdfsPath = "hdfs://localhost:19000";

		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", hdfsPath);

		//get FileSystem
		FileSystem fs = FileSystem.get(conf);
		
		// list Files
		FileStatus[] fsStatus = fs.listStatus(new Path("/"));
		for (int i = 0; i < fsStatus.length; i++) {
			System.out.println(fsStatus[i].getPath().toString());
		}

		//read a file
		Path path = new Path("/ec/wordcount.txt");
		if (!fs.exists(path)) {
			System.out.println("File " + " does not exists");
		}
		else {
			FSDataInputStream in = fs.open(path);
			byte[] b = new byte[1024];
			int numBytes = 0;
			while ((numBytes = in.read(b)) > 0) {
				String s = new String(b);
				System.out.println(s);
			}
			in.close();
		}
		System.out.println("end");
	}
}