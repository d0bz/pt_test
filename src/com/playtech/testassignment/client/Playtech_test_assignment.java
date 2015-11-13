package com.playtech.testassignment.client;

import java.util.ArrayList;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.TouchStartEvent;
import com.google.gwt.event.dom.client.TouchStartHandler;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RootPanel;

public class Playtech_test_assignment implements EntryPoint {

	private FlowPanel mainPanel = new FlowPanel();
	private FlowPanel reelPanel = new FlowPanel();
	private FlowPanel winLabel = new FlowPanel();

	private ArrayList<ReelColumn> reelColumnsArray = new ArrayList<ReelColumn>();

	private ArrayList<ImageResource> reelSymbols = new ArrayList<ImageResource>();
	public ArrayList<ImageResource> reelAnimations = new ArrayList<ImageResource>();

	private PushButton button_spin;
	private int numberOfReelColumns = 3;

	private final RandomGeneratorServiceAsync randomGeneratorService = GWT
			.create(RandomGeneratorService.class);

	public void onModuleLoad() {

		addReelSymbolsToArray();

		initBackground();
		initReel();
		initControls();
		initWinLabel();

		mainPanel.add(button_spin);
		mainPanel.add(winLabel);
		mainPanel.add(reelPanel);

		RootPanel.get("game_container").add(mainPanel);

	}

	/**
	 * Creates winlabel image and adds into winlabel FlowPanel element. Set
	 * winLabel FlowPanel stylename, visibility to false and position in
	 * application.
	 */
	private void initWinLabel() {
		Image win_label = new Image(Resources.INSTANCE.win_label());
		winLabel.setStyleName("winlabel");
		winLabel.setVisible(false);
		winLabel.add(win_label);
		reelPanel.add(winLabel);

		winLabel.getElement()
				.getStyle()
				.setMarginLeft((double) (-win_label.getWidth() / 2),
						Style.Unit.valueOf("PX"));

	}

	/**
	 * Creates background image
	 */

	private void initBackground() {
		Image background = new Image(Resources.INSTANCE.background());
		mainPanel.setPixelSize(background.getWidth(), background.getHeight());
		mainPanel.add(background);
		mainPanel.addStyleName("demoPanel");
	}

	/**
	 * Creates reelblock with reel background image and background shadow.
	 * Positioning reelblock to the center of application Sets reels width by
	 * divides 100% by number of reels Sets reel symbols for start with random
	 * symbol Creates ReelColumn object and adding it into reelColumnsArray,
	 */
	private void initReel() {
		reelPanel.addStyleName("reelBlock");
		Image reel_back = new Image(Resources.INSTANCE.reel_back());
		Image reel_shadow = new Image(Resources.INSTANCE.reel_shadow());

		reel_back.addStyleDependentName("reelBack");
		reel_shadow.addStyleDependentName("reelShadow");

		reelPanel.setPixelSize(reel_back.getWidth(), reel_back.getHeight());

		reelPanel
				.getElement()
				.getStyle()
				.setMarginTop((double) (-reel_back.getHeight() / 2),
						Style.Unit.valueOf("PX"));

		FlowPanel reelColumns = new FlowPanel();
		reelColumns.addStyleName("reelColumns");

		int widthPrecent = 100 / numberOfReelColumns;

		int rand = Random.nextInt(reelSymbols.size());

		for (int column = 0; column < numberOfReelColumns; column++) {
			ReelColumn rc = new ReelColumn(reelColumns, widthPrecent,
					reelAnimations);
			rc.setWidget(reelSymbols.get(rand));
			reelColumnsArray.add(rc);
		}

		reelPanel.add(reel_back);
		reelPanel.add(reel_shadow);
		reelPanel.add(reelColumns);
	}

	/**
	 * Creates pushbutton element with custom image. Adds mouseDownHandler and
	 * TouchStartHandler for calling buttonEvent function
	 */
	private void initControls() {
		button_spin = new PushButton(
				new Image(Resources.INSTANCE.button_spin()));

		button_spin.addStyleDependentName("buttonSpin");

		button_spin.addMouseDownHandler(new MouseDownHandler() {

			@Override
			public void onMouseDown(MouseDownEvent event) {
				buttonEvent();
			}
		});

		button_spin.addTouchStartHandler(new TouchStartHandler() {

			@Override
			public void onTouchStart(TouchStartEvent event) {
				buttonEvent();
			}
		});
	}

	/**
	 * Called by spin button mousedown or touchstart event Sets spin button
	 * disabled Sets win label visibility to false Calls out getRandomNumbers
	 * function for spinning reels and generating random number
	 */
	private void buttonEvent() {
		button_spin.setEnabled(false);
		winLabel.setVisible(false);
		setSpinning(true);
		getRandomNumbers();
	}

	/**
	 * Sets reels to spin animation or stop animation by state
	 * 
	 * @param state
	 *            state of spinning animation
	 */
	private void setSpinning(boolean state) {

		for (int c = 0; c < reelColumnsArray.size(); c++) {
			reelColumnsArray.get(c).setSpinning(state);
		}

	}

	/**
	 * Calls out service connection
	 */
	private void getRandomNumbers() {

		generateRandomNumbers();

	}

	/**
	 * Adds reel symbols and reel animation images to array for further usage
	 */
	private void addReelSymbolsToArray() {
		reelSymbols.add(Resources.INSTANCE.sym_0());
		reelSymbols.add(Resources.INSTANCE.sym_1());
		reelSymbols.add(Resources.INSTANCE.sym_2());
		reelSymbols.add(Resources.INSTANCE.sym_3());
		reelSymbols.add(Resources.INSTANCE.sym_4());
		reelSymbols.add(Resources.INSTANCE.sym_5());
		reelSymbols.add(Resources.INSTANCE.sym_6());
		reelSymbols.add(Resources.INSTANCE.sym_7());
		reelSymbols.add(Resources.INSTANCE.sym_8());
		reelSymbols.add(Resources.INSTANCE.sym_9());

		reelAnimations.add(Resources.INSTANCE.anim1());
		reelAnimations.add(Resources.INSTANCE.anim2());
		reelAnimations.add(Resources.INSTANCE.anim3());
		reelAnimations.add(Resources.INSTANCE.anim4());
	}

	/**
	 * Calls out randomGeneratorService getNumbers communication. onFailure -
	 * set reels spinning to false, alert user for error onSuccess - call out
	 * randomGeneratorNumbers function with server returned numbers
	 */
	private void generateRandomNumbers() {

		int reelSymbolsLength = reelSymbols.size();

		randomGeneratorService.getNumbers(numberOfReelColumns,
				reelSymbolsLength, new AsyncCallback<int[]>() {

					public void onFailure(Throwable caught) {
						setSpinning(false);
						Window.alert("Error occurred");
					}

					@Override
					public void onSuccess(int[] numbers) {
						randomGeneratorNumbers(numbers);
					}
				});
	}

	/**
	 * Called after server returned random numbers for reels Creates timer,
	 * which will one by one stops reels spinning and adds symbol to reel. At
	 * first win field is set to true but if one of the symbols are not equal
	 * with previous, then win field will be false. If win field is true after
	 * adding all symbols to reels, then sets winLabel visibility true.
	 * 
	 * @param numbers
	 *            Random numbers generated in server
	 */
	private void randomGeneratorNumbers(final int[] numbers) {
		Timer refreshTimer;

		refreshTimer = new Timer() {
			int num = 0;
			int lastValue = numbers[0];
			boolean win = true;

			@Override
			public void run() {
				if (lastValue != numbers[num]) {
					win = false;
				} else {
					lastValue = numbers[num];
				}

				ReelColumn rc = reelColumnsArray.get(num);
				rc.setSpinning(false);
				rc.setWidget(reelSymbols.get(numbers[num]));
				num++;

				if (numbers.length == num) {
					this.cancel();
					button_spin.setEnabled(true);

					if (win) {
						winLabel.setVisible(true);
					}
				}
			}
		};
		refreshTimer.scheduleRepeating(1000);
	}

}
