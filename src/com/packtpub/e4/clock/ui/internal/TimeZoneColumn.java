package com.packtpub.e4.clock.ui.internal;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TableColumn;

public abstract class TimeZoneColumn extends ColumnLabelProvider{
	public abstract String getText(Object element);
	public abstract String getTitle();
	public int getWidth() {
		return 250;
	}
	public int getAlignment() {
		return SWT.LEFT;
	}
	
	public TableViewerColumn addColumnTo(TableViewer viewer) {
		TableViewerColumn tableViewerColumn = new TableViewerColumn(viewer, SWT.NONE);
		TableColumn column = tableViewerColumn.getColumn();
		column.setMoveable(true);
		column.setResizable(true);
		column.setText(getTitle());
		column.setWidth(getWidth());
		column.setAlignment(getAlignment());
		tableViewerColumn.setLabelProvider(this);
		return tableViewerColumn;
		
	}

}
