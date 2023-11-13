package com.packtpub.e4.clock.ui;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

public class ClockWidget extends Canvas{
	private final Color color;
	private ZoneId zone = ZoneId.systemDefault();

	public ClockWidget(Composite parent, int style, RGB rgb) {
		super(parent, style);
		this.color = new Color(parent.getDisplay(), rgb);
		addPaintListener(this::drawClock);
		addDisposeListener(e-> color.dispose());
		Runnable redrawRunnable = () -> {
			while (!this.isDisposed()) {
				this.getDisplay().asyncExec(() -> this.redraw());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
					return;
				}
			}
		};
		new Thread(redrawRunnable, "TickTock").start();
	}
	
	public void setZone(ZoneId zoneId) {
		this.zone = zoneId;
	}
	@Override
	public Point computeSize(int wHint, int hHint, boolean changed) {
		int	size;
		if (wHint == SWT.DEFAULT) {
			size = hHint;
		} else if (hHint == SWT.DEFAULT) {
			size = wHint;
		} else {
			size = Math.min(wHint, hHint);
		}
		if (size == SWT.DEFAULT) {
			size = 50;
		}
		return new Point(size, size);
	}
	
	private void drawClock(PaintEvent e) {
		e.gc.drawArc(e.x, e.y, e.width - 1, e.height -1, 0, 360);
		int seconds = LocalTime.now().getSecond();
		int arc = (15 - seconds) * 6 % 360;
		e.gc.setBackground(color);
		e.gc.fillArc(e.x, e.y, e.width - 1, e.height - 1, arc - 1, 2);
		
		e.gc.setBackground(e.display.getSystemColor(SWT.COLOR_BLACK));
		ZonedDateTime nowDateTime = ZonedDateTime.now(zone);
		int hour = nowDateTime.getHour();
		arc = (3 - hour) * 30 % 360;
		e.gc.fillArc(e.x, e.y, e.width - 1, e.height -1, arc - 5, 10);
	}
	
	@Override
	public void dispose() {
		if (color != null && !color.isDisposed()) {
			color.dispose();
		}
		super.dispose();
	}

}
