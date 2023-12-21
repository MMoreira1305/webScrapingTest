package com.project.webscraping;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Main {
    public static void main(String[] args) {
        final String url =
                "https://web.archive.org/web/20190104110157/http://shares.telegraph.co.uk/indices/?index=MCX";

        try{
            final Document document = (Document) Jsoup.connect(url).get();

            for(Element row : document.select("table.tablesorter.full tr")){
                if (row.select("td:nth-of-type(1)").text().equals("")){
                    continue;
                }else {
                    final String ticker = row.select("td:nth-of-type(1)").text();
                    final String name = row.select("td:nth-of-type(2)").text();
                    final String tempPrice = row.select("td.right:nth-of-type(3)")
                            .text().replace(",", "");
                    final double price = Double.parseDouble(tempPrice);
                    System.out.println(ticker + " " + name + " " + price);
                }
            };
            //System.out.println(document.outerHtml());
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}


