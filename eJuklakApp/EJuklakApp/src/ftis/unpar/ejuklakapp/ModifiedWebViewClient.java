package ftis.unpar.ejuklakapp;

import android.graphics.Bitmap;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ModifiedWebViewClient extends WebViewClient{
	private ProgressBar spinningProgressBar;
	private TextView loadingText;
	
	public ModifiedWebViewClient(ProgressBar spinningProgressBar, TextView loadingText){
		super();
		this.spinningProgressBar = spinningProgressBar;
		this.loadingText = loadingText;
	}
	
	public void onPageStarted (WebView view, String url, Bitmap favicon){
		spinningProgressBar.setVisibility(View.VISIBLE);
		loadingText.setVisibility(View.VISIBLE);
		
		String temp = url.substring(url.length() - 3);
		if(temp.equals("png") || temp.equals("jpg") || temp.equals("gif")){
			view.getSettings().setBuiltInZoomControls(true);
		}
		else{
			view.getSettings().setBuiltInZoomControls(false);
		}
		
	}
	
	public void onPageFinished (WebView view, String url){
		spinningProgressBar.setVisibility(View.GONE);
		loadingText.setVisibility(View.GONE);
	}
}
