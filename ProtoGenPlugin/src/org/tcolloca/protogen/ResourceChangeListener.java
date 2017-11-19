package org.tcolloca.protogen;

import java.util.concurrent.Executor;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.tcolloca.protogen.utils.ProtoGenLog;

class ResourceChangeListener implements IResourceChangeListener {

	private final ProtoGenLog log;
	private final Executor executor;
	private final String genDirName;
	private final String workspacePathStr;
	
	ResourceChangeListener(ProtoGenLog log, Executor executor, String genDirName) {
		this.log = log;
		this.executor = executor;
		this.genDirName = genDirName;
		this.workspacePathStr = ResourcesPlugin.getWorkspace().getRoot().getLocation().toString();
	}

	@Override
	public void resourceChanged(IResourceChangeEvent event) {
		try {
			if (event.getType() != IResourceChangeEvent.POST_CHANGE)
				return;
			IResourceDelta rootDelta = event.getDelta();
			rootDelta.accept(new IResourceDeltaVisitor() {
				@Override
				public boolean visit(IResourceDelta delta) throws CoreException {
					if (delta.getKind() != IResourceDelta.CHANGED)
						return true;
					if ((delta.getFlags() & IResourceDelta.CONTENT) == 0)
						return true;
					IResource resource = delta.getResource();
					if (resource.getType() == IResource.FILE 
							&& "proto".equalsIgnoreCase(resource.getFileExtension())) {
						String projectPathStr = workspacePathStr + "/" + resource.getFullPath().segment(0).toString();
						String protoPathStr = workspacePathStr + resource.getFullPath().toString();
						executor.execute(new ProtocRunnable(log, genDirName, projectPathStr, protoPathStr));
					}
					return true;
				}
			});
		} catch (Throwable th) {
			log.error(th);
		}
	}
}
