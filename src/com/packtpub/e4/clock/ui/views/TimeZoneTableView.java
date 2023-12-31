package com.packtpub.e4.clock.ui.views;

import java.time.ZoneId;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.services.EMenuService;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.packtpub.e4.clock.ui.internal.TimeZoneIDColumn;
import com.packtpub.e4.clock.ui.internal.TimeZoneOffsetColumn;
import com.packtpub.e4.clock.ui.internal.TimeZoneSummerTimeColumn;

public class TimeZoneTableView {

	private TableViewer tableViewer;
	@Inject
	private ESelectionService selectionService;
	
	@PostConstruct
	public void create(Composite parent, EMenuService menuService) {
		System.out.println(312);
		tableViewer = new TableViewer(parent, SWT.H_SCROLL | SWT.V_SCROLL);
		tableViewer.getTable().setHeaderVisible(true);
		tableViewer.setContentProvider(ArrayContentProvider.getInstance());
//		tableViewer.setInput(ZoneId.getAvailableZoneIds());
		new TimeZoneIDColumn().addColumnTo(tableViewer);
		new TimeZoneOffsetColumn().addColumnTo(tableViewer);
		new TimeZoneSummerTimeColumn().addColumnTo(tableViewer);
		tableViewer.setInput(ZoneId.getAvailableZoneIds().stream().map(ZoneId::of).toArray());
		
		System.out.println(313);
		System.out.println(menuService);
		menuService.registerContextMenu(tableViewer.getControl(), "com.packtpub.e4.clock.ui.popup");
		tableViewer.addSelectionChangedListener(event -> {
			Object selection = ((IStructuredSelection) event.getSelection()).getFirstElement();
			if (selection != null && selectionService != null) {
				selectionService.setSelection(selection);
				System.out.println(314);
			}
		});
	}
	
	@Inject
	@Optional
	public void setTimeZone(
			@Named(IServiceConstants.ACTIVE_SELECTION) ZoneId timeId) {
		if (timeId != null && tableViewer != null) {
			tableViewer.setSelection(new StructuredSelection(timeId));
			tableViewer.reveal(timeId);
			System.out.println(134 + timeId.toString());
		}
		
	}
	
	@Focus
	public void focus() {
		if (tableViewer != null) {
			tableViewer.getControl().setFocus();
		}
	}
}
