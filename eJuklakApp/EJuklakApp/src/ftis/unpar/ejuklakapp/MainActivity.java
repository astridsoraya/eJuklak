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
	private String lastHeader;
	
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
		webView.getSettings().setLoadWithOverviewMode(true);
		webView.getSettings().setUseWideViewPort(true);
		webView.getSettings().setLayoutAlgorithm(LayoutAlgorithm.NORMAL);
		webView.getSettings().setBuiltInZoomControls(true);
		
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
		String[] header = getResources().getStringArray(R.array.header_array);
		mTitle = "EJuklak";
		openBab(header[number - 1]);
	
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

	
	public void openBab(String header){
		if(lastState == null){
			webView.loadUrl(getResources().getString(R.string.home_html));
		}
		
		else{
			String temp = getResources().getString(R.string.home_html);
			String file = temp + "#" + header;
			webView.loadUrl(file);
		}
		menuOpened = false;
		lastState = "home";
		lastHeader = header;

	}
	
	public void openAbout(){
		menuOpened = true;
		webView.loadUrl(getResources().getString(R.string.about_html));
		
	}
	
	public void openHelp(){
		menuOpened = true;
		webView.loadUrl(getResources().getString(R.string.help_html));
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
				openBab(lastHeader);
			}
		}
		else{
			super.onBackPressed();
		}
		
	}

}
