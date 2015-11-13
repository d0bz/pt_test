package com.playtech.testassignment.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface Resources extends ClientBundle{
	
	public static final Resources INSTANCE =  GWT.create(Resources.class);
	
	@Source("com/playtech/testassignment/resources/GUI/background.png")
	ImageResource background();
	@Source("com/playtech/testassignment/resources/GUI/reel_back.png")
	ImageResource reel_back();
	@Source("com/playtech/testassignment/resources/GUI/reel_shadow.png")
	ImageResource reel_shadow();
	@Source("com/playtech/testassignment/resources/GUI/button_spin.png")
	ImageResource button_spin();
	@Source("com/playtech/testassignment/resources/GUI/you_win.png")
	ImageResource win_label();
	
	
	@Source("com/playtech/testassignment/resources/GUI/anim1.png")
	ImageResource anim1();
	@Source("com/playtech/testassignment/resources/GUI/anim2.png")
	ImageResource anim2();
	@Source("com/playtech/testassignment/resources/GUI/anim3.png")
	ImageResource anim3();
	@Source("com/playtech/testassignment/resources/GUI/anim4.png")
	ImageResource anim4();
	

	@Source("com/playtech/testassignment/resources/symbols/sym_0.png")
	ImageResource sym_0();
	@Source("com/playtech/testassignment/resources/symbols/sym_1.png")
	ImageResource sym_1();
	@Source("com/playtech/testassignment/resources/symbols/sym_2.png")
	ImageResource sym_2();
	@Source("com/playtech/testassignment/resources/symbols/sym_3.png")
	ImageResource sym_3();
	@Source("com/playtech/testassignment/resources/symbols/sym_4.png")
	ImageResource sym_4();
	@Source("com/playtech/testassignment/resources/symbols/sym_5.png")
	ImageResource sym_5();
	@Source("com/playtech/testassignment/resources/symbols/sym_6.png")
	ImageResource sym_6();
	@Source("com/playtech/testassignment/resources/symbols/sym_7.png")
	ImageResource sym_7();
	@Source("com/playtech/testassignment/resources/symbols/sym_8.png")
	ImageResource sym_8();
	@Source("com/playtech/testassignment/resources/symbols/sym_9.png")
	ImageResource sym_9();
	
	
	
	
	


	
}
