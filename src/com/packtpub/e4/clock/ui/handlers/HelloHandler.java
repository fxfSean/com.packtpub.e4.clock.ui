package com.packtpub.e4.clock.ui.handlers;

import java.util.Iterator;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.di.UISynchronize;
import org.eclipse.jface.dialogs.MessageDialog;

public class HelloHandler {
	
	@Execute
	public void execute(final UISynchronize display) {
		Job job = new Job("about to say hello") {

			@Override
			protected IStatus run(IProgressMonitor monitor) {
				try {
					SubMonitor subMonitor = SubMonitor.convert(monitor, "Preparing", 5000);
//					monitor.beginTask("Preparing", 5000);
					for(int i = 0; i < 50 && !subMonitor.isCanceled(); i++) {
						if (i == 10) {
							subMonitor.subTask("Doing somthing");
						} else if (i == 12) {
							checkDozen(subMonitor.newChild(100));
							continue;
						} else if (i == 25) {
							subMonitor.subTask("d s else");
						} else if (i == 40) {
							subMonitor.subTask("nearly there");
						}
					
						Thread.sleep(100);
						monitor.worked(100);
					}
				} catch (Exception e) {
					// TODO: handle exception
				} finally {
					monitor.done();
				}
				if (!monitor.isCanceled()) {
					display.asyncExec(() -> {
						MessageDialog.openInformation(null, "Hello", "world");
					});
					
				}
				return Status.OK_STATUS;
			}
			
		};
		job.schedule();
		return;
	}
	
	private void checkDozen(IProgressMonitor monitor) {
		if (monitor == null) {
			monitor = new NullProgressMonitor();
			
		}
		try {
			monitor.beginTask("Checking a dozen", 12);
			for(int i = 0; i<12; i++) {
				Thread.sleep(10);
				monitor.worked(1);
			}
				
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			monitor.done();
		}
		
	}

}
