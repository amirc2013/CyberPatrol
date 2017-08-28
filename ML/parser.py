from bs4 import BeautifulSoup
import urllib
from IPython.display import Image, display
#
import cfscrape
scraper = cfscrape.create_scraper()  # returns a CloudflareScraper instance
# Or: scraper = cfscrape.CloudflareScraper()  # CloudflareScraper inherits from requests.Session
# print scraper.get("https://www.fxp.co.il/forumdisplay.php?f=626").content
# url = 'https://www.fxp.co.il/forumdisplay.php?f=626'
# r = urllib.urlopen(url).read()
soup = BeautifulSoup(scraper.get("https://www.fxp.co.il/forumdisplay.php?f=626").content,'lxml')
sublinks = ['https://www.fxp.co.il/'+x['href'] for x in soup.find_all('a',class_='title')[:]]

for link in sublinks:
    soup = BeautifulSoup(scraper.get(link).content, 'lxml')
    soup.find_all('blockquore',recursive=True)
    print()

print()


