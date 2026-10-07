package com.wishlist.platform.service;

import com.wishlist.platform.dto.UrlMetadataResponse;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ScraperService {

    // FX rates relative to USD (1 unit of currency = X USD)
    private static final Map<String, Double> EXCHANGE_RATES_TO_USD = Map.of(
            "USD", 1.0,
            "EUR", 1.08,  // Euro to USD
            "GBP", 1.27,  // British Pound to USD
            "AZN", 0.59,  // Azerbaijani Manat to USD
            "TRY", 0.03   // Turkish Lira to USD
    );

    public UrlMetadataResponse extractMetadata(String url) {
        try {
            Document doc = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7)")
                    .timeout(5000)
                    .get();

            String title = getMetaTag(doc, "og:title", "twitter:title");
            if (title.isEmpty()) {
                title = doc.title();
            }

            String image = getMetaTag(doc, "og:image", "twitter:image");
            String brand = getMetaTag(doc, "og:site_name", "product:brand");

            // Extract price and currency metadata tags
            String rawPrice = getMetaTag(doc, "og:price:amount", "product:price:amount", "twitter:data1");
            String currencyTag = getMetaTag(doc, "og:price:currency", "product:price:currency");

            // If rawPrice is empty, attempt to fallback to visible text in meta description
            if (rawPrice.isEmpty()) {
                rawPrice = getMetaTag(doc, "og:description", "description");
            }

            // Convert raw price string to converted USD value string
            Double convertedPrice = parseAndConvertToUsd(rawPrice, currencyTag);
            String finalPrice = (convertedPrice != null) ? convertedPrice.toString() : "";

            return new UrlMetadataResponse(title, image, brand, finalPrice);
        } catch (Exception e) {
            return new UrlMetadataResponse("", "", "", "");
        }
    }

    private String getMetaTag(Document doc, String... properties) {
        for (String prop : properties) {
            Element tag = doc.selectFirst("meta[property='" + prop + "']");
            if (tag == null) {
                tag = doc.selectFirst("meta[name='" + prop + "']");
            }
            if (tag != null && tag.hasAttr("content")) {
                return tag.attr("content");
            }
        }
        return "";
    }

    private Double parseAndConvertToUsd(String rawPrice, String currencyTag) {
        if (rawPrice == null || rawPrice.isBlank()) {
            return null;
        }

        // 1. Detect currency code
        String currency = detectCurrency(rawPrice, currencyTag);

        // 2. Extract numeric amount
        Double amount = extractNumericAmount(rawPrice);
        if (amount == null) {
            return null;
        }

        // 3. Convert to USD
        Double rate = EXCHANGE_RATES_TO_USD.getOrDefault(currency, 1.0);
        double converted = amount * rate;

        return BigDecimal.valueOf(converted)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }

    private String detectCurrency(String text, String currencyTag) {
        if (currencyTag != null && !currencyTag.isBlank()) {
            String upperTag = currencyTag.toUpperCase().trim();
            if (EXCHANGE_RATES_TO_USD.containsKey(upperTag)) {
                return upperTag;
            }
        }

        String upperText = text.toUpperCase();
        if (upperText.contains("€") || upperText.contains("EUR")) return "EUR";
        if (upperText.contains("£") || upperText.contains("GBP")) return "GBP";
        if (upperText.contains("₼") || upperText.contains("AZN")) return "AZN";
        if (upperText.contains("₺") || upperText.contains("TRY")) return "TRY";

        return "USD"; // Default fallback
    }

    private Double extractNumericAmount(String text) {
        // matches numbers formatted with dots or commas (e.g. 1,299.99 or 95,00)
        Matcher matcher = Pattern.compile("([0-9]+[.,]?[0-9]*)").matcher(text.replace(" ", ""));
        if (matcher.find()) {
            String val = matcher.group(1);
            if (val.contains(",") && !val.contains(".")) {
                val = val.replace(",", ".");
            } else if (val.contains(",") && val.contains(".")) {
                val = val.replace(",", "");
            }
            try {
                return Double.parseDouble(val);
            } catch (NumberFormatException ignored) {}
        }
        return null;
    }
}