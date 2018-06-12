package com.harish.rssfeedapp;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class SportsActivity extends ListActivity {
    List sportsTitle,sportsLinks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_sports);
        new MyAyscclass().execute();

    }

    class MyAyscclass extends AsyncTask<Object,Void,ArrayAdapter>
    {

        @Override
        protected ArrayAdapter doInBackground(Object... objects) {
            sportsTitle=new ArrayList();
            sportsLinks=new ArrayList();
            try{

                URL Sportsurl = new URL("https://timesofindia.indiatimes.com/rssfeeds/4719148.cms");
                XmlPullParserFactory factory1 = XmlPullParserFactory.newInstance();
                factory1.setNamespaceAware(false);
                XmlPullParser xpp2 = factory1.newPullParser();
                xpp2.setInput(getInputStream(Sportsurl),"UTF-8");
                boolean inside =false;
                int event=xpp2.getEventType();
                while (event!=xpp2.END_DOCUMENT){
                    if (event == XmlPullParser.START_TAG)
                    {
                        if (xpp2.getName().equalsIgnoreCase("item"))
                        {
                            inside = true;
                        }
                        else if (xpp2.getName().equalsIgnoreCase("title"))
                        {
                            if (inside)
                                sportsTitle.add(xpp2.nextText());
                        }
                        else if (xpp2.getName().equalsIgnoreCase("link"))
                        {
                            if (inside)
                                sportsLinks.add(xpp2.nextText());
                        }
                    }
                    else if(event==XmlPullParser.END_TAG && xpp2.getName().equalsIgnoreCase("item"))
                    {
                        inside=false;
                    }
                    event= xpp2.next();
                }

            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
            }
            catch (XmlPullParserException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

            return null;
        }
        protected void onPostExecute(ArrayAdapter arrayAdapter) {
            arrayAdapter=new ArrayAdapter(SportsActivity.this,android.R.layout.simple_list_item_1,sportsTitle);
            setListAdapter(arrayAdapter);
            super.onPostExecute(arrayAdapter);
        }
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Uri uri = Uri.parse((sportsLinks.get(position)).toString());
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public InputStream getInputStream(URL sportsurl)
    {
        try{
            return sportsurl.openConnection().getInputStream();
        }
        catch (IOException e)
        {
            return null;
        }
    }
}
