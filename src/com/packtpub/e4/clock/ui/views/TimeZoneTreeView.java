package com.packtpub.e4.clock.ui.views;

import java.net.URL;
import java.time.ZoneId;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.e4.core.di.extensions.Preference;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.FontRegistry;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.resource.ResourceManager;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.ISharedImages;

import com.packtpub.e4.clock.ui.internal.TimeZoneDialog;
import com.packtpub.e4.clock.ui.internal.TimeZoneViewerComparator;
import com.packtpub.e4.clock.ui.internal.TimeZoneViewerFilter;


public class TimeZoneTreeView {
	
	private TreeViewer treeViewer;
	@Inject
	private ISharedImages images;
//	@Preference(nodePath = "com.packtpub.e4.clock.ui",
//			value = "launchCount")
//	@Inject
//	IEclipsePreferences preferences;
	int launchCount;
	
	public void setLaunchCount(@Preference(nodePath = "com.packtpub.e4.clock.ui",
			value = "launchCount") int launchCount) {
		this.launchCount = launchCount;
	}
	
	@PostConstruct
	public void create(Composite parent) {
		System.out.println("Launch count is: " + launchCount);
		treeViewer = new TreeViewer(parent, SWT.H_SCROLL|SWT.V_SCROLL|SWT.MULTI);
		ResourceManager rm = JFaceResources.getResources();
		LocalResourceManager lrm = new LocalResourceManager(rm, parent);
		ImageRegistry ir = new ImageRegistry(lrm);
		FontRegistry fr = JFaceResources.getFontRegistry();
		URL sample = getClass().getResource("/icons/sample.png");
		ir.put("sample", ImageDescriptor.createFromURL(sample));
		System.out.println("14");
		treeViewer.setData("Reverse", Boolean.TRUE);
		treeViewer.setComparator(new TimeZoneViewerComparator());
		treeViewer.setFilters(new ViewerFilter[] {
				new TimeZoneViewerFilter("GMT")
		});
		treeViewer.setExpandPreCheckFilters(true);
		treeViewer.setLabelProvider(new DelegatingStyledCellLabelProvider(new TimeZoneLabelProvider(images, ir,fr)));
		treeViewer.setContentProvider(new TimeZoneContentProvider());
		treeViewer.setInput(new Object[] {TimeZoneComparator.getTimeZones()});
		treeViewer.addDoubleClickListener(event-> {
			Viewer viewer = event.getViewer();
			Shell shell = viewer.getControl().getShell();
			ISelection sel = viewer.getSelection();
			Object selectedValue;
			if (!(sel instanceof IStructuredSelection) || sel.isEmpty()) {
				selectedValue = null;
			} else {
				selectedValue = ((IStructuredSelection) sel).getFirstElement();
			}
			if (selectedValue instanceof ZoneId) {
				ZoneId timeZoneId  = (ZoneId)selectedValue;
//				MessageDialog.openInformation(shell, timeZoneId.getId(), timeZoneId.toString());
				new TimeZoneDialog(shell, timeZoneId).open();
			}
			
		});
	}
	
	


}
