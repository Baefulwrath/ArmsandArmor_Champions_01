package containers;

import java.awt.Rectangle;

public final class nullContent extends Content{
	public nullContent() {
		super(0, 0, ContentType.DEFAULT);
	}

	@Override
	public void update() {
	}

	@Override
	public void mouseMoved(Rectangle mouse, int cx, int cy) {
	}
}
