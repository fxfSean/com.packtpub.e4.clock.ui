package com.packtpub.e4.clock.ui.views;

import java.net.URL;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.jface.resource.FontRegistry;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.resource.ResourceManager;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.part.ViewPart;

import com.packtpub.e4.clock.ui.internal.TimeZoneViewerComparator;
import com.packtpub.e4.clock.ui.internal.TimeZoneViewerFilter;


public class TimeZoneTreeView extends ViewPart {
	
	private TreeViewer treeViewer;
	@Inject
	private ISharedImages images;
	
	
	@PostConstruct
	public void create(Composite parent) {
		System.out.println("11");
		treeViewer = new TreeViewer(parent, SWT.H_SCROLL|SWT.V_SCROLL|SWT.MULTI);
		System.out.println("14");
		treeViewer.setLabelProvider(new TimeZoneLabelProvider(images, null, null));
		treeViewer.setContentProvider(new TimeZoneContentProvider());
		treeViewer.setInput(new Object[] {TimeZoneComparator.getTimeZones()});
	}

	@Override
	public void createPartControl(Composite parent) {
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
	}

	@Override
	public void setFocus() {
		
	}

}
