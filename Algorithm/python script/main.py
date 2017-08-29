# python 2.7


from bs4 import BeautifulSoup
import urllib
from IPython.display import Image, display
import sys
import cfscrape




def main():
	scraper = cfscrape.create_scraper()  # returns a CloudflareScraper instance
	# Or: scraper = cfscrape.CloudflareScraper()  # CloudflareScraper inherits from requests.Session
	# print scraper.get("https://www.fxp.co.il/forumdisplay.php?f=626").content
	# url = 'https://www.fxp.co.il/forumdisplay.php?f=626'
	# r = urllib.urlopen(url).read()

	topic = 0
	for i in range(100):
		soup = BeautifulSoup(scraper.get("https://www.fxp.co.il/forumdisplay.php?f=626&page="+str(i)).content,'lxml')
		sublinks = ['https://www.fxp.co.il/'+x['href'] for x in soup.find_all('a',class_='title')[:]]
		for link in sublinks:
			soup = BeautifulSoup(scraper.get(link).content, 'lxml')
			blocks = soup.find('blockquote',{"class":"postcontent restore"})
			try:
				blocks = blocks.script.decompose()
			except:
				pass
			if blocks != None:
				with open("topic"+str(topic)+"page"+str(i)+".txt","w") as f:
					f.write(blocks.text.strip().encode("UTF-8"))
					topic += 1;


if __name__ == '__main__':
	main()