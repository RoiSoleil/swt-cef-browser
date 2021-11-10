package org.roisoleil.eclipse.cef.example;

import org.cef.*;
import org.cef.browser.*;
import org.eclipse.core.runtime.Platform;
import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.widgets.Composite;

public class SwtCefBrowser extends Composite {

  private final CefApp cefApp;
  private final CefClient cefClient;
  private final CefBrowser cefBrowser;

  private java.awt.Frame frame;

  public SwtCefBrowser(Composite parent, String url) {
    this(parent, SWT.None, url);
  }

  public SwtCefBrowser(Composite parent, int style, String url) {
    super(parent, SWT.EMBEDDED | style);
    cefApp = initCefApp();
    cefClient = initCefClient();
    cefBrowser = initCefBrowser(url);
    initCefBrowserWidget();
  }

  protected CefApp initCefApp() {
    String[] args = Platform.getApplicationArgs();
    CefApp.startup(args);
    if (CefApp.getState() != CefApp.CefAppState.INITIALIZED) {
      CefSettings settings = getCefSettings();
      return CefApp.getInstance(settings);
    } else {
      return CefApp.getInstance();
    }
  }

  protected CefSettings getCefSettings() {
    CefSettings settings = new CefSettings();
    settings.windowless_rendering_enabled = false;
    return settings;
  }

  protected CefClient initCefClient() {
    CefClient cefClient = cefApp.createClient();
    cefClient.addMessageRouter(CefMessageRouter.create());
    return cefClient;
  }

  protected CefBrowser initCefBrowser(String url) {
    return cefClient.createBrowser(url, isOSR(), isTransparent());
  }

  protected boolean isOSR() {
    return false;
  }

  protected boolean isTransparent() {
    return false;
  }

  protected void initCefBrowserWidget() {
    frame = SWT_AWT.new_Frame(this);
    frame.add(cefBrowser.getUIComponent());
  }

  public CefApp getCefApp() {
    return cefApp;
  }

  public CefClient getCefClient() {
    return cefClient;
  }

  public org.cef.browser.CefBrowser getCefBrowser() {
    return cefBrowser;
  }

  public java.awt.Frame getFrame() {
    return frame;
  }

}
