package com.jtgtgames.dialog;

import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.jtgtgames.R;
import android.view.LayoutInflater;
import android.app.AlertDialog;

//JTGTGames © 2020
//Timothy 156
//JTGTDialog ver.1.6

public class JTGTDialog {
    private Dialog dialog;
    private Button ok;
    private Button cancel;
    private boolean negativeExist = false;
	private boolean pExist = false;
	private boolean iconExist = false;
    private JTGTDialogClickListener negativeListener;
    private JTGTDialogClickListener positiveListener;
    private TextView title;
    private TextView subtitle;
	private ImageView icon;
    private LinearLayout main;
	private LinearLayout lin;
	private LinearLayout ButMargin;
	private Context Context;
	private long AnimDuration = 1000;
	private Handler handler = new Handler();
	private ObjectAnimator _animate;
	private ObjectAnimator animate;
	private View dialogLayout;
	private LayoutInflater dialogLI;
	private Runnable delay = new Runnable() { @Override public void run()
		{
			if(main.getAlpha() == 0){
				dialog.dismiss();
			}
		}};
	
	
	//Main To Create
	public JTGTDialog(Context context){
		Context = context;
		MainSet();
		initSet();
		initEventSet();
        dialog.setCancelable(false);
        setTitle("Title");
        setSubtitle("Subtitle");
	}
	public JTGTDialog(Context context, String _title) {
        Context = context;
		MainSet();
		initSet();
		initEventSet();
        dialog.setCancelable(false);
        setTitle(_title);
        setSubtitle("Subtitle");
    }
	public JTGTDialog(Context context, String _title, String _subtitle) {
        Context = context;
		MainSet();
		initSet();
		initEventSet();
        dialog.setCancelable(false);
        setTitle(_title);
        setSubtitle(_subtitle);
    }
    public JTGTDialog(Context context, String _title, String _subtitle, boolean _cancelable) {
        Context = context;
		MainSet();
		initSet();
		initEventSet();
        dialog.setCancelable(_cancelable);
        setTitle(_title);
        setSubtitle(_subtitle);
    }
    public JTGTDialog(Context context, String _title, String _subtitle, boolean _cancelable, int _color) {
	    Context = context;
	    MainSet();
		initSet();
		initEventSet();
        dialog.setCancelable(_cancelable);
        setTitle(_title);
        setSubtitle(_subtitle);
	    changeColor(main.getBackground(), _color);
    }
	//Other
	public void setCancelable(boolean _cancelable){
		dialog.setCancelable(_cancelable);
	}
	public void setLayoutParams(LayoutParams _layout){
		main.setLayoutParams(_layout);
	}
    public void show() {
        if (!negativeExist) {
            cancel.setVisibility(View.GONE);
        }
		if (!iconExist){
			icon.setVisibility(View.GONE);
		}
		if (!pExist){
			ok.setVisibility(View.GONE);
		}
		if (!negativeExist & !pExist){
			lin.setVisibility(View.GONE);
		}
		try{handler.removeCallbacks(delay);animate.cancel(); }catch(Exception e){}
        animate = new ObjectAnimator();
		main.setAlpha(0);
		dialog.show();
		animate.setTarget(main);
		animate.setPropertyName("alpha");
		animate.setDuration(AnimDuration);
		animate.setFloatValues(0);
		animate.setFloatValues(0, 1);
		animate.start();
    }
    public void dismiss() {
		//dialog.setOnDismissListener(new DialogInterface.OnDismissListener() { @Override public void onDismiss(DialogInterface dialog){} });
		try{handler.removeCallbacks(delay);_animate.cancel(); }catch(Exception e){}
		_animate = new ObjectAnimator();
		_animate.setTarget(main);
		_animate.setPropertyName("alpha");
		_animate.setDuration(AnimDuration);
		_animate.setFloatValues(1);
		_animate.setFloatValues(1, 0);
		_animate.start();
		if(!(AnimDuration == 0)){
			handler.postDelayed(delay,(AnimDuration + 100));
		}else{
			dialog.show();
		}
     	
    }
	public void setButtonTextSize(long _size){
		ok.setTextSize(_size);
		cancel.setTextSize(_size);
	}
	public void setAnimDuration(long _duration){
		AnimDuration = _duration;
	}
	public void setBackgroundColor(int _color){
		changeColor(main.getBackground(), _color);
	}
	//Title/SubTitle Part
    public void setTitle(String _title) {
        title.setText(_title);
    }
    public void setSubtitle(String _subtitle) {
        subtitle.setText(_subtitle);
    }
	public void setTitleSize(long _size){
		title.setTextSize(_size);
	}
	public void setSubTitleSize(long _size){
		subtitle.setTextSize(_size);
	}
	public void setTitleColor(int _color){
		title.setTextColor(_color);
	}
	public void setSubTitleColor(int _color){
		subtitle.setTextColor(_color);
	}
	//Positive/Negative Part
	public void setPositiveButtonColor(int _color){
		//GradientDrawable shape = new GradientDrawable();
		//shape.setColor(_color);
		//shape.setCornerRadius(15);
		//RippleDrawable riple = new RippleDrawable(new ColorStateList(new int[][]{new int[]{}}, new int[]{Color.parseColor("#0641FF")}), shape, null);
		//ok.setBackgroundDrawable(riple);
		//ok.setBackgroundResource(R.drawable.jtgt_dialog_button_background);
		changeColor(ok.getBackground(), _color);
	}
	public void setNegativeButtonColor(int _color){
		//GradientDrawable shape = new GradientDrawable();
		//shape.setColor(_color);
		//shape.setCornerRadius(15);
		//RippleDrawable riple = new RippleDrawable(new ColorStateList(new int[][]{new int[]{}}, new int[]{Color.parseColor("#0641FF")}), shape, shape);
		//cancel.setBackgroundDrawable(riple);
		//cancel.setBackgroundResource(R.drawable.jtgt_dialog_button_background);
		changeColor(cancel.getBackground(), _color);
	}
    public void setPositive(String _posi, JTGTDialogClickListener _listener) {
        if (_listener != null){
			positiveListener = _listener;
			dismiss();
			pExist = true;
			setPositiveLabel(_posi);
		}
    }
    public void setNegative(String _nega, JTGTDialogClickListener _listener) {
        if (_listener != null) {
            negativeListener = _listener;
            dismiss();
            negativeExist = true;
            setNegativeLabel(_nega);
        }
    }
    private void setPositiveLabel(String _positive) {
        ok.setText(_positive);
    }
    private void setNegativeLabel(String _negative) {
        cancel.setText(_negative);
    }
	public void setPositiveButtonTextColor(int _color){
		ok.setTextColor(_color);
	}
	public void setNegativeButtonTextColor(int _color){
		cancel.setTextColor(_color);
	}
	public void setPositiveButtonLayoutParams(LayoutParams _PBSize){
		ok.setLayoutParams(_PBSize);
	}
	public void setNegativeButtonLayoutParams(LayoutParams _NBSize){
		cancel.setLayoutParams(_NBSize);
	}
	public void setButtonMargin(int margin){
		ButMargin.setLayoutParams(new LayoutParams(margin, ViewGroup.LayoutParams.MATCH_PARENT));
	}
	//Icon Part
	public void setIcon(int imgIcon){
		icon.setImageResource(imgIcon);
		iconExist = true;
	}	
	public void setIconLayoutParams(LayoutParams _layoutPrm){
		icon.setLayoutParams(_layoutPrm);
	}
	//Open Access Views
	public TextView getTitle(){
		return title;
	}
	public TextView getSubTitle(){
		return subtitle;
	}
	public ImageView getIcon(){
		return icon;
	}
	public Button getPositiveButton(){
		return ok;
	}
	public Button getNegativeButton(){
		return cancel;
	}
	//Private Void Part
	private void changeColor(Drawable drawable, int _color) {
        if (drawable instanceof ShapeDrawable) {
            ((ShapeDrawable) drawable).getPaint().setColor(_color);
        } else if (drawable instanceof GradientDrawable) {
            ((GradientDrawable) drawable).setColor(_color);
        } else if (drawable instanceof ColorDrawable) {
            ((ColorDrawable) drawable).setColor(_color);
        }
    }
    private void initSet() {
        title = ((TextView) dialogLayout.findViewById(R.id.title));
        subtitle = ((TextView) dialogLayout.findViewById(R.id.subtitle));
        ok = ((Button) dialogLayout.findViewById(R.id.ok));
        cancel = ((Button) dialogLayout.findViewById(R.id.cancel));
        main = ((LinearLayout) dialogLayout.findViewById(R.id.main));
		lin = ((LinearLayout) dialogLayout.findViewById(R.id.lin));
		icon = ((ImageView) dialogLayout.findViewById(R.id.icon));
		ButMargin = ((LinearLayout) dialogLayout.findViewById(R.id.ButMargin));
    }
    private void initEventSet() {
		if(ok !=null){
 	       ok.setOnClickListener(new OnClickListener() {
	            public void onClick(View view) {
  	              if (positiveListener != null) {
 	                   positiveListener.onClick();
   	             }
    	        }
  	      });
		}
		if(cancel !=null){
       	 cancel.setOnClickListener(new OnClickListener() {
    	        public void onClick(View view) {
    	            if (negativeListener != null) {
  	                  negativeListener.onClick();
 	               }
 	           }
 	       });
		}
    }
	private void MainSet(){
		dialog = new Dialog(Context, R.style.JTGTDialogTheme);
		dialogLI = LayoutInflater.from(Context);
		dialogLayout = dialogLI.inflate(R.layout.jtgt_dialog_default, null);
		dialog.setContentView(dialogLayout);
	}
}
