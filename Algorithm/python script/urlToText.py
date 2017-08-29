from bs4 import BeautifulSoup
import urllib
from IPython.display import Image, display
import sys
import cfscrape

def getTextFromTopic(url):
	soup = BeautifulSoup(scraper.get(link).content, 'lxml')
	blocks = soup.find('blockquote',{"class":"postcontent restore"})
	try:
		blocks = blocks.script.decompose()
	except:
		pass
	if blocks != None:			
		return blocks.text.strip().encode("UTF-8")
	else :
		return ""



