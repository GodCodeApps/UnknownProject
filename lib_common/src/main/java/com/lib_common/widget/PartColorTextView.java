package com.lib_common.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Html;
import android.text.Spanned;
import android.util.AttributeSet;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Set;
import java.util.regex.PatternSyntaxException;

@SuppressLint("AppCompatCustomView")
public class PartColorTextView extends TextView {
    private final String SRM = "<font color=\"⊙\">%1$s</font>";//文字转换Hmtl的格式固定不要动
    private final String OF = "⊙";//替换颜色的字符


    public PartColorTextView(Context context) {
        super(context);
    }

    public PartColorTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PartColorTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * @author WangHan
     * 创建时间:2016/11/2 14:38
     * 方法名:setPartText
     * 描述: 給文字顏色設置
     * 参数:allText:全部文字 keyText:变色关键字 otherTextColor:默认文字颜色 keyTextColor:要变颜色的字的颜色必须是个Color(getRe.getColor.)这样获得
     * 返回值:
     * 特殊注释:该方法适合只有一个关键词的时候
     */
    public void setPartText(String allText, String keyText, int otherTextColor, int keyTextColor) {
        String NF = String.format("#%06X", 0xFFFFFF & keyTextColor);
        String replace = SRM.replace(OF, NF);
        String format = String.format(replace, keyText);
        try {
            String textStr = allText.replaceAll(keyText, format);
            Spanned spanned = Html.fromHtml(textStr);
            setTextColor(otherTextColor);
            setText(spanned);
        } catch (PatternSyntaxException e) {
            e.printStackTrace();
        }
    }

    /**
     * @author WangHan
     * 创建时间:2016/11/2 14:38
     * 方法名:setPartText
     * 描述: 給文字顏色設置
     * 参数:allText:全部文字 keyColorMap:用于一段话有多个,且颜色要求不同的集合,Key是关键字,Value是Color  keyTextColor:要变颜色的字的颜色必须是个Color(getRe.getColor.)这样获得
     * 返回值:
     * 特殊注释:该方法适合一段话有多个关键词的时候
     */
    public void setPartText(String allText, HashMap<String, Integer> keyColorMap, int otherTextColor) {
        Spanned spanned = null;
        String textStr = null;
        Set<String> keySet = keyColorMap.keySet();
        for (String s : keySet) {
            String NF = String.format("#%06X", 0xFFFFFF & keyColorMap.get(s));
            String replace = SRM.replace(OF, NF);
            String format = String.format(replace, s);
            try {
                if (textStr == null) {
                    textStr = allText.replaceAll(s, format);
                } else {
                    textStr = textStr.replaceAll(s, format);
                }
            } catch (PatternSyntaxException e) {
                e.printStackTrace();
            }
        }
        setTextColor(otherTextColor);
        spanned = Html.fromHtml(textStr);
        setText(spanned);
    }
    /**
     *@author WangHan
     *创建时间:2016/11/2 14:38
     *方法名:setPartText
     *描述: 給文字顏色設置
     *参数:allText:全部文字 keyColorMap:用于一段话有多个,且颜色要求不同的集合,Key是关键字,Value是Color  keyTextColor:要变颜色的字的颜色必须是个Color(getRe.getColor.)这样获得
     *返回值:
     *特殊注释:该方法适合一段话有多个关键词的时候
     */
    public void setPartText2(String allText, HashMap<String,Object> keyColorMap, int otherTextColor)
    {
        Spanned spanned = null;
        String textStr=null;
        Set<String> keySet = keyColorMap.keySet();
        for (String s : keySet) {
            String NF = String.format("#%06X", 0xFFFFFF & Integer.valueOf(keyColorMap.get(s).toString()));
            String replace = SRM.replace(OF, NF);
            String format = String.format(replace, s);
            try {
                if (textStr==null)
                {
                    textStr  = allText.replaceAll(s, format);
                }else
                {
                    textStr=textStr.replaceAll(s, format);
                }
            }catch (PatternSyntaxException e)
            {
                e.printStackTrace();
            }
        }
        setTextColor(otherTextColor);
        spanned= Html.fromHtml(textStr);
        setText(spanned);
    }
}