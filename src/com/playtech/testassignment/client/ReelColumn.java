package com.playtech.testassignment.client;

import java.util.ArrayList;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.dom.client.Style;

public class ReelColumn {

	FlowPanel reelColumn;
	ArrayList<ImageResource> reelAnimations;
	Timer refreshTimer;

	public ReelColumn(FlowPanel reelBlock, int widthPrecent,
			ArrayList<ImageResource> reelAnimations) {

		reelColumn = new FlowPanel();
		reelColumn.setWidth(widthPrecent + "%");
		reelBlock.add(reelColumn);
		this.reelAnimations = reelAnimations;
	}
	
	/**
	 * Sets reel widget by param ImageResource. Calculates image size
	 * and positioning image center of reel
	 * @param w ImageResource url
	 */
	public void setWidget(ImageResource w) {
		reelColumn.clear();
		Image img = new Image(w);
		img.getElement()
				.getStyle()
				.setMarginTop((double) (-img.getHeight() / 2),
						Style.Unit.valueOf("PX"));
		img.getElement()
				.getStyle()
				.setMarginLeft((double) (-img.getWidth() / 2),
						Style.Unit.valueOf("PX"));

		reelColumn.add(img);
	}

	/**
	 * Creates timer for reelanimation spinning
	 * @param state Declares spinning animation state 
	 */
	public void setSpinning(boolean state) {
		if (state) {
			refreshTimer = new Timer() {
				int count = 0;
				int total = reelAnimations.size();

				@Override
				public void run() {
					setWidget(reelAnimations.get(count));

					count = count < total - 1 ? count + 1 : 0;
				}
			};
			refreshTimer.scheduleRepeating(100);

		} else {
			if (refreshTimer != null) {
				refreshTimer.cancel();
			}
		}
	}

}
