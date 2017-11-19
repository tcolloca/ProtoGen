package org.tcolloca.protogen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.tcolloca.protogen.utils.ProtoGenLog;

class ProtocRunnable implements Runnable {

	private static final String RAN_MESSAGE = "ProtoGen plug-in ran protoc over %s.";
	
	private final ProtoGenLog log;
	private final String genDirName;
	private final String projectPathStr;
	private final String protoPathStr;
	
	ProtocRunnable(ProtoGenLog log, String genDirName, String projectPathStr, String protoPathStr) {
		this.log = log;
		this.genDirName = genDirName;
		this.projectPathStr = projectPathStr;
		this.protoPathStr = protoPathStr;
	}
	
	@Override
	public void run() {
		try {
			String genDirPathStr = projectPathStr + "/" + genDirName;
			String protoDirPathStr = Paths.get(protoPathStr).getParent().toString();
			Files.createDirectories(Paths.get(genDirPathStr));
			ProcessBuilder builder = new ProcessBuilder("protoc",
					"--java_out", genDirPathStr,
					"--proto_path", protoDirPathStr,
					protoPathStr)
					.redirectErrorStream(true);
			Process process = builder.start();
			readProcessOutput(process);
			log.info(String.format(RAN_MESSAGE, protoPathStr));
		} catch (Throwable th) {
			log.error(th);
		}
	}
	
	private void readProcessOutput(Process process) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String line;
		while ((line = br.readLine()) != null) {
			log.warning(line);
		}
		br.close();
	}
}
