package com.jtgtgames.dialog;

import android.content.Context;
import android.graphics.Color;
import android.widget.LinearLayout.LayoutParams;
import android.view.ViewGroup;
//JTGTGames © 2020
//Timothy 156
//JTGTDialogBuilder ver.1.4

public class JTGTDialogBuilder {
    private boolean cancelable = false;
    private Context context;
    private String posi = "Done";
    private String nega = "Cancel";
    private String subtitle = "The Number One SWTool";
    private String title = "SK Revolt Tools";
    private int icon;
	private int Bcolor = Color.parseColor("#18C3FB");
	private int titleColor = Color.parseColor("#FFFFFF");
	private int subtitleColor = Color.parseColor("#F2ED11");
	private int pColor = Color.parseColor("#28F800");
	private int nColor = Color.parseColor("#28F800");
	private int margin = 16;
	private LayoutParams iconLayout = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
	private LayoutParams nBut = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
	private LayoutParams pBut = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    private long titleSize = 18;
    private long subtitleSize = 15;
	private long butSize = 12;
    private boolean isHaveIcon = false;
    private JTGTDialogClickListener negativeListener;
    private JTGTDialogClickListener positiveListener;
	private JTGTDialog jtgtdialog;

	
    public JTGTDialogBuilder(Context ctx) {
        this.context = ctx;
    }
	public JTGTDialog dismiss(){
		jtgtdialog.dismiss();
		return jtgtdialog;
	}
    public JTGTDialogBuilder setTitle(String _title) {
        this.title = _title;
        return this;
    }
    public JTGTDialogBuilder setSubtitle(String _subtitle) {
        this.subtitle = _subtitle;
        return this;
    }
    public JTGTDialogBuilder setCancelable(boolean _cancelable) {
        this.cancelable = _cancelable;
        return this;
    }
    public JTGTDialogBuilder setIcon(int imgIcon){
    	this.icon = imgIcon;
    	this.isHaveIcon = true;
    	return this;
    }
    public JTGTDialogBuilder setNegativeListener(String _nega, JTGTDialogClickListener listener) {
        this.negativeListener = listener;
        this.nega = _nega;
        return this;
    }
    public JTGTDialogBuilder setPositiveListener(String _posi, JTGTDialogClickListener listener) {
        this.positiveListener = listener;
        this.posi = _posi;
        return this;
    }
	public JTGTDialogBuilder setTitleSize(long _size){
		this.titleSize = _size;
		return this;
	}
	public JTGTDialogBuilder setSubTitleSize(long _size){
		this.subtitleSize = _size;
		return this;
	}
	public JTGTDialogBuilder setButtonTextSize(long _size){
		this.butSize = _size;
		return this;
	}
	public JTGTDialogBuilder setTitleColor(int _color){
		this.titleColor = _color;
		return this;
	}
	public JTGTDialogBuilder setSubTitleColor(int _color){
		this.subtitleColor = _color;
		return this;
	}
	public JTGTDialogBuilder setPositiveButtonColor(int _color){
		this.pColor = _color;
		return this;
	}
	public JTGTDialogBuilder setNegativeButtonColor(int _color){
		this.nColor = _color;
		return this;
	}
	public JTGTDialogBuilder setIconParams(LayoutParams _lay){
		this.iconLayout = _lay;
		return this;
	}
	public JTGTDialogBuilder setPositiveButtonLayoutParams(LayoutParams _lay){
		this.pBut = _lay;
		return this;
	}
	public JTGTDialogBuilder setNegativeButtonLayoutParams(LayoutParams _lay){
		this.nBut = _lay;
		return this;
	}
	public JTGTDialogBuilder setBackgroundColor(int _color){
		this.Bcolor = _color;
		return this;
	}
	public JTGTDialogBuilder setButtonMargin(int _margin){
		margin = _margin;
		return this;
	}
    public JTGTDialog build() {
        jtgtdialog = new JTGTDialog(this.context, this.title, this.subtitle, this.cancelable, this.Bcolor);
        jtgtdialog.setNegative(this.nega, this.negativeListener);
        jtgtdialog.setPositive(this.posi, this.positiveListener);
        if (isHaveIcon){
        	jtgtdialog.setIcon(icon);
        }
		jtgtdialog.setTitleSize(titleSize);
		jtgtdialog.setSubTitleSize(subtitleSize);
		jtgtdialog.setButtonTextSize(butSize);
		jtgtdialog.setTitleColor(titleColor);
		jtgtdialog.setSubTitleColor(subtitleColor);
		jtgtdialog.setPositiveButtonTextColor(pColor);
		jtgtdialog.setNegativeButtonTextColor(nColor);
		jtgtdialog.setIconLayoutParams(iconLayout);
		jtgtdialog.setPositiveButtonLayoutParams(pBut);
		jtgtdialog.setNegativeButtonLayoutParams(nBut);
		jtgtdialog.setButtonMargin(margin);
        return jtgtdialog;
    }
}
