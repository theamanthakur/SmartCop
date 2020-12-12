package com.twg.smartcop;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpStatus;
import org.apache.http.client.utils.URLEncodedUtils;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.internal.http.HttpHeaders;

public class challanActivity extends AppCompatActivity {
    CheckBox checkBox1,checkBox2,checkBox3,checkBox4;
    Button btnProcess;
    TextView textName, txtDate;
    String ss;
    HashMap<String,String> mapp = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challan);
        txtDate = findViewById(R.id.date);
        textName = findViewById(R.id.name);
        Intent intent = getIntent();
        String num = intent.getStringExtra("vnum").toString();
//        Toast.makeText(getApplicationContext(),num,Toast.LENGTH_SHORT).show();
//        new RetrieveFeedTask().execute(num);
        textName.setText("BHARAT BHUSHAN DHINGRA");
        txtDate.setText("25-Sep-2018");
        addListenerOnButtonClick();

    }
    public void addListenerOnButtonClick(){
        checkBox1 = findViewById(R.id.checkbox1);
        checkBox2 = findViewById(R.id.checkbox2);
        checkBox3 = findViewById(R.id.checkbox3);
        checkBox4 = findViewById(R.id.checkbox4);
        btnProcess =  findViewById(R.id.btnPro);
        btnProcess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int totalamount=0;
                StringBuilder result=new StringBuilder();
                result.append("Selected Items:");
                if(checkBox1.isChecked()){
                    result.append("\nWithout Helmet 1000Rs");
                    totalamount+=1000;
                }
                if(checkBox2.isChecked()){
                    result.append("\nRed Light Jump 2000Rs");
                    totalamount+=2000;
                }
                if(checkBox3.isChecked()){
                    result.append("\nNo Driving License 3000Rs");
                    totalamount+=3000;
                }

                if(checkBox4.isChecked()){
                    result.append("\nOver Speeding 2000Rs");
                    totalamount+=2000;
                }
                result.append("\nTotal: "+totalamount+"Rs");
                //Displaying the message on the toast
                Toast.makeText(getApplicationContext(), result.toString(), Toast.LENGTH_LONG).show();
            }

        });
    }
//    private static Map<String, String> getData(String str) {
//        Map<String, String> resultMap = new HashMap<>();
//        String str2 = "";
//        Matcher matcher = Pattern.compile("(\\w{2})(\\d{2})(\\D*?)(\\d{1,4})").matcher(str);
//        if (matcher.matches()) {
//            str2 = matcher.replaceFirst("$1$2$3-$4");
//        }
//        String[] split = str2.split("-");
//        String str3 = split[0];
//        str = split[1];
//        try {
//            Connection.Response execute = Jsoup.connect("https://parivahan.gov.in/rcdlstatus/?pur_cd=102")
//                    //.validateTLSCertificates(false)   // The site is secure now and it is expected to always validate cert
//                    .followRedirects(true)
//                    .ignoreHttpErrors(true)
//                    .method(Connection.Method.GET).execute();
//
//            if (execute.statusCode() <= HttpStatus.SC_INTERNAL_SERVER_ERROR) {
//                Map<String, String> cookies = execute.cookies();
//                Document parse = Jsoup.parse(execute.body());
//                Element first = parse.getElementsByAttributeValue("name", "javax.faces.ViewState").first();
//                if (first == null) {
//                    first = parse.getElementById("j_id1:javax.faces.ViewState:0");
//                }
//                String attr = first.attr("value");
//                String trim = Jsoup.parse(execute.body())
//                        .getElementsByAttributeValueStarting("id", "form_rcdl:j_idt")
//                        .select("button")
//                        .get(0).attr("id").trim();
//
//                str = Jsoup.connect("https://parivahan.gov.in/rcdlstatus/vahan/rcDlHome.xhtml")
//                        //.validateTLSCertificates(false)   // The site is secure now and it is expected to always validate cert
//                        .followRedirects(true)
//                        .method(Connection.Method.POST)
//                        .cookies(cookies)
//                        .referrer("https://parivahan.gov.in/rcdlstatus/?pur_cd=102")
//                        .header("Content-Type", URLEncodedUtils.CONTENT_TYPE)
//                        .header("Host", "parivahan.gov.in")
////                        .header(HttpHeaders.ACCEPT, "application/xml, text/xml, */*; q=0.01")
////                        .header(HttpHeaders.ACCEPT_LANGUAGE, "en-US,en;q=0.5")
////                        .header(HttpHeaders.ACCEPT_ENCODING, "gzip, deflate, br")
//                        .header("X-Requested-With", "XMLHttpRequest")
//                        .header("Faces-Request", "partial/ajax")
//                        .header("Origin", "https://parivahan.gov.in")
//                        .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.140 Safari/537.36")
//                        .data("javax.faces.partial.ajax", "true")
//                        .data("javax.faces.source", trim)
//                        .data("javax.faces.partial.execute", "@all")
//                        .data("javax.faces.partial.render", "form_rcdl:pnl_show form_rcdl:pg_show form_rcdl:rcdl_pnl")
//                        .data(trim, trim)
//                        .data("form_rcdl", "form_rcdl")
//                        .data("form_rcdl:tf_reg_no1", str3)
//                        .data("form_rcdl:tf_reg_no2", str)
//                        .data("javax.faces.ViewState", attr)
//                        .execute()
//                        .body();
//
//                if (str.contains("Registration No. does not exist!!!")) {
//                    resultMap.put(str, "No Record(s) Found");
//                } else {
//                    String htmlString = "<!DOCTYPE html><html><body>" +
//                            str.substring(str.indexOf("<table"), str.lastIndexOf("</table>")) +
//                            "</body></html>";
//                    Document parse2 = Jsoup.parse(htmlString);
//
//                    Element first2 = parse2.select("table").first();
//                    if (first2 != null) {
//                        Elements select = first2.select("tr");
//                        for (Element element : select) {
//                            Elements select2 = element.select("td");
//                            if (select2.size() == 2) {
//                                String key = select2.get(0).text().replace(":", "");
//                                resultMap.put(key, select2.get(1).text());
//                            } else if (select2.size() == 4) {
//                                String key = select2.get(0).text().replace(":", "");
//                                resultMap.put(key, select2.get(1).text());
//
//                                key = select2.get(2).text().replace(":", "");
//                                resultMap.put(key, select2.get(3).text());
//                            }
//                        }
//                    } else {
//                        resultMap.put(str, "No Record(s) Found");
//                    }
//                }
//            } else {
//                resultMap.put(str, "500 Server Error");
//            }
//        } catch (Exception unused) {
//            resultMap.put(str, "Server Error");
//        }
//        return resultMap;
//    }
    class RetrieveFeedTask extends AsyncTask<String, Void, HashMap<String,String>> {


        Document document;
        private Exception exception;
        HashMap<String,String> map = new HashMap<>();
        ProgressDialog p;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            p = new ProgressDialog(challanActivity.this);
            p.setMessage("Please wait...It is downloading");
            p.setIndeterminate(false);
            p.setCancelable(false);
            p.show();
        }
        @Override
        protected HashMap<String,String> doInBackground(String... urls) {
            try{
                String number1 = urls[0];
                String url = "http://shrouded-falls-48764.herokuapp.com/vehicle-info/"+number1;
                //document = Jsoup.connect(url).get();

                String json = Jsoup.connect(url).header("api-key","a7bdab022e5a4895aab3c30aeb9f3385").ignoreContentType(true).execute().body();
                //textView.setText(json);
                JSONObject obj = new JSONObject(json);
                String jj = obj.get("Owner Name").toString();
                String date = obj.get("Reg Date").toString();
                map.put("name",jj);
                map.put("date",date);
                System.out.println("data is :"+jj);
            }catch (Exception e){
                e.printStackTrace();
            }
            return map;
        }
        @Override
        protected void onPostExecute(HashMap<String,String> getMap) {
            super.onPostExecute(getMap);
            p.cancel();
            String ff = getMap.get("name").toString() +getMap.get("date");
            Toast.makeText(getApplicationContext(),ff,Toast.LENGTH_LONG).show();
//            String dt = getMap.get("date");
            textName.setText(ff);
//            txtDate.setText(dt);
            // txt.setText(result);
            // You might want to change "executed" for the returned string
            // passed into onPostExecute(), but that is up to you
        }
    }


}