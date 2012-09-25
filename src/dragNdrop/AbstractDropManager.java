package dragNdrop;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.SwingUtilities;

import com.google.common.collect.ImmutableList;

public abstract class AbstractDropManager implements DropListener
{
	public AbstractDropManager()
	{
		m_components = ImmutableList.of();
	}

	protected JComponent isInTarget(Point point)
	{
		for (JComponent component : m_components)
		{
			Rectangle bounds = component.getBounds();
			Point location = component.getLocation();
			SwingUtilities.convertPointToScreen(location, component.getParent());
			bounds.x = location.x;
			bounds.y = location.y;
			if (bounds.contains(point))
				return component;
		}

		return null;
	}

	public void setComponentList(List<? extends JComponent> components)
	{
		m_components = components;
	}

	public abstract void dropped(DropEvent event);

	protected List<? extends JComponent> m_components;
}
