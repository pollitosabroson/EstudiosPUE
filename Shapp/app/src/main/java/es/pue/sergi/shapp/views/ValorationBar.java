package es.pue.sergi.shapp.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.TextView;

import es.pue.sergi.shapp.R;

/**
 * Created by daa on 13/02/2017.
 */

public class ValorationBar extends TextView {

    private int color2;
    private int color4;
    private int color6;
    private int color8;
    private int color10;

    Paint paintText;
    Paint paintRect;

    private float valoracion;

    public float getValoracion() {
        return valoracion;
    }

    public void setValoracion(float valoracion) {
        this.valoracion = valoracion;
        invalidate();
        requestLayout();
    }

    public ValorationBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a=context.obtainStyledAttributes(
                attrs,
                R.styleable.ValorationBarOptions,
                0,0);
        color2=a.getInt(R.styleable.ValorationBarOptions_color2,R.color.colorAccent);
        color4=a.getInt(R.styleable.ValorationBarOptions_color4,R.color.colorAccent);
        color6=a.getInt(R.styleable.ValorationBarOptions_color6,R.color.colorAccent);

        color8=a.getInt(R.styleable.ValorationBarOptions_color8,R.color.colorAccent);
        color10=a.getInt(R.styleable.ValorationBarOptions_color10,R.color.colorAccent);

        a.recycle();

        init();

    }

    private void init(){
        paintText=new Paint(Paint.ANTI_ALIAS_FLAG);
        paintText.setColor(getResources().getColor(R.color.colorPrimaryDark));
        paintText.setTextAlign(Paint.Align.CENTER);
        paintText.setTextSize(20);

        paintRect=new Paint(Paint.ANTI_ALIAS_FLAG);

    }

    @Override
    public void onDraw(Canvas canvas){
        float height=getMeasuredHeight();
        float width=getMeasuredWidth();

        int linecolor=color2;
        if(valoracion<=2) linecolor=color2;
        else if(valoracion<=4) linecolor=color4;
        else linecolor=color6;

        float linewidth=width*valoracion/10;
        paintRect.setColor(linecolor);
        paintRect.setStrokeWidth(linewidth);
        canvas.drawLine(0,height/2,linewidth,height/2,paintRect);

        float textSize=height*2/3;
        paintText.setTextSize(textSize);

        Rect rect=new Rect();
        String texto=String.format("%d",(int)valoracion);
        paintText.getTextBounds(texto,0,texto.length(),rect);

        float textYpos=height/2+rect.height()/2;

        float textXpos=linewidth/2;

        canvas.drawText(texto,textXpos,textYpos,paintText);

        canvas.save();
        super.onDraw(canvas);
    }

}
