
package org.linfeng.news.wedget.city.pinyin;



import java.util.ArrayList;

import org.linfeng.news.wedget.city.pinyin.HanziToPinyin3.Token;

public class PinYin
{
    public static String getPinYin(String input)
    {
        ArrayList<Token> tokens = HanziToPinyin3.getInstance().get(input);
        StringBuilder sb = new StringBuilder();
        if (tokens != null && tokens.size() > 0)
        {
            for (Token token : tokens)
            {
                if (Token.PINYIN == token.type)
                {
                    sb.append(token.target);
                } else
                {
                    sb.append(token.source);
                }
            }
        }
        return sb.toString().toLowerCase();
    }
}
