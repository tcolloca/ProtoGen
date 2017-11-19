package org.tcolloca.protogen;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.ui.IStartup;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.tcolloca.protogen.utils.ProtoGenLog;

public class Main extends AbstractUIPlugin implements IStartup {

	private static String START_MESSAGE = "ProtoGen plug-in has started.";
	private static String GEN_DIR_NAME = "src-gen";
	
	@Override
	public void earlyStartup() {
		ProtoGenLog log = new ProtoGenLog(getLog());
		try {
			Executor executor = Executors.newCachedThreadPool();
			ResourcesPlugin.getWorkspace().addResourceChangeListener(
					new ResourceChangeListener(log, executor, GEN_DIR_NAME));
			log.info(START_MESSAGE);
		} catch (Throwable th) {
			log.error(th);
		}
	}
}
