package ftis.unpar.ejuklakapp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity implements NavigationDrawerCallbacks {

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
	private ModifiedWebViewClient webViewClient;
	private ProgressBar spinningProgressBar;
	private TextView loadingText;
	
	private boolean menuOpened = false;
	private String lastState;
	private String lastHeader;
	
	private static ArrayList<String> id_anchors = new ArrayList<String>();
	private static ArrayList<String> title_anchors = new ArrayList<String>();
	private static ArrayList<String> heading_anchors = new ArrayList<String>();
	
	public static ArrayList<String> getIdAnchors(){
		return id_anchors;
	}
	
	public static ArrayList<String> getTitleAnchors(){
		return title_anchors;
	}
	
	public static ArrayList<String> getHeadingAnchors(){
		return heading_anchors;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mTitle = getTitle();

		id_anchors.clear();
		title_anchors.clear();
		heading_anchors.clear();
		
		// Set up the tags
		
		  File input = new File(getCacheDir()+"/"+getResources().getString(R.string.home_url));
		  try {
		    InputStream is = getAssets().open(getResources().getString(R.string.home_url));
		    int size = is.available();
		    byte[] buffer = new byte[size];
		    is.read(buffer);
		    is.close();


		    FileOutputStream fos = new FileOutputStream(input);
		    fos.write(buffer);
		    fos.close();
		  } 
		  catch (Exception e) { 
			  throw new RuntimeException(e); 
	      }
		  
		try {
			Document doc = Jsoup.parse(input, "UTF-8");
			Elements headers = doc.select("h1, h2, h3, h4, h5, h6");
			
			for(Element temp_anchor : headers){
				id_anchors.add(temp_anchor.attr("id"));
				title_anchors.add(temp_anchor.html());
				heading_anchors.add(temp_anchor.tagName());
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		
		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));
		
		webView = (WebView) findViewById(R.id.webview);
		webView.getSettings().setLoadWithOverviewMode(true);
		webView.getSettings().setUseWideViewPort(true);
		webView.getSettings().setLayoutAlgorithm(LayoutAlgorithm.NORMAL);
		webView.getSettings().setBuiltInZoomControls(true);
		
		spinningProgressBar = (ProgressBar) findViewById(R.id.progressBar1);
		loadingText = (TextView) findViewById(R.id.textView1);
		
		webViewClient = new ModifiedWebViewClient(spinningProgressBar, loadingText);
		webView.setWebViewClient(webViewClient);
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
		mTitle = "EJuklak";
		openBab(id_anchors.get(number - 1));
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
		else if(webView.getUrl().substring(0, 28).equals("file:///android_asset/images")){
			openBab(lastHeader);
		}
		else{
			super.onBackPressed();
		}
		
	}

}
