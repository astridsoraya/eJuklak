package ftis.unpar.ejuklakapp;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity implements
NavigationDrawerFragment.NavigationDrawerCallbacks {

	/**
	* Fragment managing the behaviors, interactions and presentation of the
	* navigation drawer.
	*/
	private NavigationDrawerFragment mNavigationDrawerFragment;
	
	
	/**
	* Used to store the last screen title. For use in
	* {@link #restoreActionBar()}.
	*/
	private CharSequence mTitle;
	
	private WebView webView;
	
	private boolean menuOpened = false;
	private String lastState;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();
		
		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));
		
		webView = (WebView) findViewById(R.id.webview);
		webView.setWebViewClient(new WebViewClient(){
		    
			@Override
		    public void onLoadResource(WebView view, String url){
		    	String urlLampiran = getResources().getString(R.string.picture_html);
		    	String[] namaGambar = getResources().getStringArray(R.array.picture_array);
		    	
	        	view.getSettings().setBuiltInZoomControls(false);
	        	view.getSettings().setLoadWithOverviewMode(false);
    			view.getSettings().setUseWideViewPort(false);
	        	
	        	
		    	for(int i = 0; i < namaGambar.length; i++){
		    		if(url.equals(urlLampiran+namaGambar[i])){
		    			view.getSettings().setLoadWithOverviewMode(true);
		    			view.getSettings().setUseWideViewPort(true);
		    			view.getSettings().setLayoutAlgorithm(LayoutAlgorithm.NORMAL);
			        	view.getSettings().setBuiltInZoomControls(true);
		                menuOpened = true;
		                i += namaGambar.length;
		    		}
		    	}

		    }
		});
		
		webView.loadUrl(getResources().getString(R.string.home_html));
	}
	
	@Override
	public void onNavigationDrawerItemSelected(int position) {
	// update the main content by replacing fragments
		FragmentManager fragmentManager = getSupportFragmentManager();
		fragmentManager
				.beginTransaction()
				.replace(R.id.container,
						PlaceholderFragment.newInstance(position + 1)).commit();
	}
	
	public void onSectionAttached(int number) {
		String[] sections = getResources().getStringArray(R.array.navigation_array);
		switch (number) {
		case 1:
			setTitle(R.string.app_name);
			mTitle = getTitle();
			openHome();
			break;
		case 2:
			mTitle = "EJuklak: " + sections[1];
			openBab("1");
			break;
		case 3:
			mTitle = "EJuklak: " + sections[1];
			openSubBab("1", getResources().getString(R.string.id_1_1));
			break;
		
		case 4:
			mTitle = "EJuklak: " + sections[3];
			openBab("2");
			break;
		
		case 5:
			mTitle = "EJuklak: " + sections[4];
			openBab("3");
			break;
			
		case 6:
			mTitle = "EJuklak: " + sections[5];
			openBab("4");
			break;

		case 7:
			mTitle = "EJuklak: " + sections[6];
			openLampiran();
			break;
		}
	
	}
	
	public void restoreActionBar() {
		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			// Only show items in the action bar relevant to this screen
			// if the drawer is not showing. Otherwise, let the drawer
			// decide what to show in the action bar.
			getMenuInflater().inflate(R.menu.main, menu);
			restoreActionBar();
			return true;
		}
		
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_about) {
			refreshTitle("EJuklak: About");
			openAbout();
			return true;
		}
		if (id == R.id.action_help) {
			refreshTitle("EJuklak: Help");
			openHelp();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void openHome(){
		menuOpened = false;
		lastState = "home";
		webView.loadUrl(getResources().getString(R.string.home_html));
	}
	
	public void openBab(String bab){
		menuOpened = false;
		lastState = bab;
		String file = "file:///android_asset/Bab" + bab + ".html";
		webView.loadUrl(file);
	}
	
	public void openSubBab(String bab, String id){
		menuOpened = false;
		lastState = bab;
		String file = "file:///android_asset/Bab" + bab + ".html#" + id;
		webView.loadUrl(file);
	}
	
	public void openAbout(){
		menuOpened = true;
		webView.loadUrl(getResources().getString(R.string.about_html));
		
	}
	
	public void openHelp(){
		menuOpened = true;
		webView.loadUrl(getResources().getString(R.string.help_html));
	}
	
	public void openLampiran(){
		menuOpened = false;
		lastState = "lampiran";
		String file = getResources().getString(R.string.lampiran_html);
		webView.loadUrl(file);
	}
	
	public void openPicture(){
		menuOpened = true;
	}
	
	public void refreshTitle(String title){
		setTitle(title);
		mTitle = getTitle();
		restoreActionBar();
	}
	
	@Override
	public void onBackPressed()
	{
		if(mNavigationDrawerFragment.getMDrawerLayout().isDrawerOpen(Gravity.LEFT)){
			mNavigationDrawerFragment.getMDrawerLayout().closeDrawer(Gravity.LEFT);
		}
		else if(menuOpened){
			if(lastState=="home"){
				refreshTitle(getResources().getString(R.string.app_name));
				openHome();
			}
			else{
				if(lastState.equals("lampiran")){
					refreshTitle("EJuklak: Lampiran");
					openLampiran();
				}
				else{
					refreshTitle("EJuklak: Bab " + lastState);
					openBab(lastState);
				}
				
			}
		}
		else{
			super.onBackPressed();
		}
		//mNavigationDrawerFragment.mDrawerLayout.isDrawerOpen(Gravity.LEFT);
		/*//super.onBackPressed();
		if(mNavigationDrawerFragment.isDrawerOpen(Gravity.LEFT))
		{
			mNavigationDrawerFragment.mDrawerLayout.setFocusableInTouchMode(false);
		}
		mNavigationDrawerFragment.mDrawerLayout.setFocusableInTouchMode(false);*/
//		restoreActionBar();
		
	}
	
	/**
	* A placeholder fragment containing a simple view.
	*/
	public static class PlaceholderFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
			private static final String ARG_SECTION_NUMBER = "section_number";
		
		/**
		 * Returns a new instance of this fragment for the given section number.
		 */
		public static PlaceholderFragment newInstance(int sectionNumber) {
			PlaceholderFragment fragment = new PlaceholderFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}
		
		public PlaceholderFragment() {
		}
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
		
		@Override
		public void onAttach(Activity activity) {
			super.onAttach(activity);
			((MainActivity) activity).onSectionAttached(getArguments().getInt(
					ARG_SECTION_NUMBER));
		}
	}

}
