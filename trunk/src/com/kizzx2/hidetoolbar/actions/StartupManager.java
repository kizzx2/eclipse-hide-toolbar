package com.kizzx2.hidetoolbar.actions;

import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.IStartup;
import org.eclipse.ui.IWorkbenchWindow;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Canvas;

public class StartupManager implements IStartup
{
	public StartupManager(){}
	
	public void earlyStartup()
	{
		Display.getDefault().asyncExec(new Runnable()
		{
			public void run()
			{
				final IWorkbenchWindow window = 
					PlatformUI.getWorkbench().getActiveWorkbenchWindow();
				final Shell mainShell = window.getShell();
				Control[] children = mainShell.getChildren();
				
				for ( Control child : children )
				{
					if ( child.isDisposed() || !child.isVisible() ) continue;
					if ( child.getClass().equals ( Canvas.class ) ) continue;
					if ( child.getClass().equals ( Composite.class ) ) continue;
					
					child.setVisible ( false );
				}
				
				mainShell.layout();
			}
		});
	}
}
