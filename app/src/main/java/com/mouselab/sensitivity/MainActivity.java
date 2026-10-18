package com.mouselab.sensitivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(android.graphics.Color.rgb(13, 14, 19));
        getWindow().setNavigationBarColor(android.graphics.Color.rgb(13, 14, 19));
        getWindow().getDecorView().setSystemUiVisibility(0);
        webView = new WebView(this);
        webView.setFocusable(true);
        webView.setFocusableInTouchMode(true);
        webView.setBackgroundColor(android.graphics.Color.rgb(13, 14, 19));
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setAllowFileAccess(true);
        settings.setDatabaseEnabled(true);
        settings.setSupportZoom(false);
        settings.setBuiltInZoomControls(false);
        settings.setDisplayZoomControls(false);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient());
        setContentView(webView);
        webView.loadUrl("file:///android_asset/index.html");
        webView.requestFocus();
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (webView != null && event.getKeyCode() != KeyEvent.KEYCODE_BACK) {
            int code = event.getUnicodeChar();
            String key = code > 0 ? new String(Character.toChars(code)) : null;

            if (key != null && !key.isEmpty()) {
                key = key.toLowerCase(java.util.Locale.ROOT);
                String js = "window.nativeKeyEvent && window.nativeKeyEvent("
                    + org.json.JSONObject.quote(key) + ","
                    + (event.getAction() == KeyEvent.ACTION_DOWN ? "true" : "false")
                    + ")";
                webView.evaluateJavascript(js, null);
            }
            webView.requestFocus();
        }
        return super.dispatchKeyEvent(event);
    }

    @Override
    public boolean dispatchGenericMotionEvent(MotionEvent event) {
        if (webView != null && (event.getSource() & android.view.InputDevice.SOURCE_MOUSE) == android.view.InputDevice.SOURCE_MOUSE) {
            webView.dispatchGenericMotionEvent(event);
            return true;
        }
        return super.dispatchGenericMotionEvent(event);
    }

    @Override
    public void onBackPressed() {
        if (webView != null && webView.canGoBack()) webView.goBack();
        else super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        if (webView != null) {
            webView.loadUrl("about:blank");
            webView.destroy();
            webView = null;
        }
        super.onDestroy();
    }
}
