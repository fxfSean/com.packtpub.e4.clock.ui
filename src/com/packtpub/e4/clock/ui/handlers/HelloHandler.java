package com.packtpub.e4.clock.ui.handlers;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.dialogs.MessageDialog;

public class HelloHandler {
	
	@Execute
	public void execute() {
		MessageDialog.openInformation(null, "Hello", "world");
		
	}

}
