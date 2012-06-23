package com.ridewide.changerecentapps;

public class DummyApp {
	  protected Integer icon;
	  protected String label;
	  protected String act;
	  protected Boolean check;
	  
	  public DummyApp(Integer icon, String label, String act, Boolean check) {
		    this.icon = icon;
		    this.label = label;
		    this.act = act;
		    this.check = check;
	  }
	  public Integer getIcon() {
	    return icon;
	  }
	  public String getLabel() {
	    return label;
	  }
	  public String getAct() {
	    return act;
	  }
	  public Boolean getCheck() {
	    return check;
	  }
	  public void setCheck(Boolean b) {
	    check = b;
	  }
		public static Integer[] appIcon = {
			R.drawable.acv,
			R.drawable.andoku,
			R.drawable.angry_birds,
			R.drawable.browser,
			R.drawable.calculator,
			R.drawable.calendar,
			R.drawable.camera,
			R.drawable.clock,
			R.drawable.contacts,
			R.drawable.dropbox,
			R.drawable.email,
			R.drawable.facebook,
			R.drawable.gallery,
			R.drawable.gmail,
			R.drawable.googleearth,
			R.drawable.lastfm,
			R.drawable.maps,
			R.drawable.market,
			R.drawable.mint,
			R.drawable.music,
			R.drawable.myspace,
			R.drawable.notes,
			R.drawable.opera,
			R.drawable.phone,
			R.drawable.podcast,
			R.drawable.skype,
			R.drawable.tunein,
			R.drawable.wapedia,
			R.drawable.xbmc,
			R.drawable.youtube
		};
		public static String[] labelName = {
			"Acv",
			"Andoku",
			"AngryBirds",
			"Browser",
			"Calculator",
			"Calendar",
			"Camera",
			"Clock",
			"Contacts",
			"Dropbox",
			"Email",
			"Facebook",
			"Gallery",
			"Gmail",
			"Googleearth",
			"Lastfm",
			"Maps",
			"Market",
			"Mint",
			"Music",
			"Myspace",
			"Notes",
			"Opera",
			"Phone",
			"Podcast",
			"Skype",
			"Tunein",
			"Wapedia",
			"Xbmc",
			"Youtube"
		};
		public static String[] activityName = {
			".AcvActivity",
			".AndokuActivity",
			".AngryBirdsActivity",
			".BrowserActivity",
			".CalculatorActivity",
			".CalendarActivity",
			".CameraActivity",
			".ClockActivity",
			".ContactsActivity",
			".DropboxActivity",
			".EmailActivity",
			".FacebookActivity",
			".GalleryActivity",
			".GmailActivity",
			".GoogleearthActivity",
			".LastfmActivity",
			".MapsActivity",
			".MarketActivity",
			".MintActivity",
			".MusicActivity",
			".MyspaceActivity",
			".NotesActivity",
			".OperaActivity",
			".PhoneActivity",
			".PodcastActivity",
			".SkypeActivity",
			".TuneinActivity",
			".WapediaActivity",
			".XbmcActivity",
			".YoutubeActivity"
		};
}
