# 🕷 Threadspider – A Multi-threaded Web Crawler

## 📖 Overview
Threadspider is a **single-node, multi-threaded web crawler** built in Java with Maven.  
It demonstrates efficient URL scheduling, politeness compliance, and modular design suitable for large-scale crawling systems.

This project was designed to simulate components similar to those used in industrial-grade crawlers such as Googlebot and Bingbot.

---

## 🕷 What is a Web Crawler?
A **web crawler** (also known as a spider or bot) is a program that automatically browses the internet, fetching and processing web pages in a systematic way.  
It follows links from one page to another, collecting data and discovering new URLs — much like a librarian scanning books and noting references to other books.

---

## 📌 Where Can It Be Used?
Web crawlers power many real-world systems and applications:

1. **Search Engines** – Indexing billions of pages for fast, relevant search results.
2. **Price Comparison Platforms** – Crawling e-commerce sites to compare product prices.
3. **News Aggregators** – Collecting and displaying articles from multiple publishers.
4. **SEO Analysis Tools** – Checking backlinks, rankings, and optimization opportunities.
5. **Data Mining & Research** – Gathering large datasets for analytics or ML models.
6. **Compliance & Monitoring** – Detecting broken links, outdated pages, or policy violations.

---

## 💡 Non-Technical Analogy
Imagine sending out a team of scouts into a city.
- Each scout visits a location (a web page).
- They take notes on what they find (content parsing).
- They also write down addresses of new locations to visit (link extraction).
- The process repeats until all interesting places are visited or time runs out.  
  Threadspider is those scouts — just faster, organized, and running 24/7.

---

## ⚙️ Features
- **Seed URL Loader** – Reads initial URLs from configuration.
- **URL Frontier** – Thread-safe priority queues with politeness mapping.
- **HTML Downloader** – Multi-threaded page fetcher with DNS resolution.
- **Content Parser** – Validates and parses HTML content.
- **Duplicate Content Detection** – Avoids re-processing identical data.
- **Link Extraction** – Gathers new URLs from parsed pages.
- **URL Filtering** – Removes unwanted or disallowed URLs.
- **Seen URL Tracker** – Prevents re-crawling of already visited URLs.

---

## 🛠 Tech Stack
- **Language**: Java 17+
- **Build Tool**: Maven
- **Concurrency**: Java ExecutorService, Thread-safe Collections
- **Libraries**:
    - [Jsoup](https://jsoup.org/) – HTML parsing
    - [SLF4J](http://www.slf4j.org/) – Logging