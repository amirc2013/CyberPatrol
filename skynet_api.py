
from bs4 import BeautifulSoup
import urllib
from IPython.display import Image, display
import sys
import cfscrape
from flask import Flask, request
from flask_restful import Resource, Api
from json import dumps
from flask.ext.jsonpify import jsonify
from sklearn import datasets
from sklearn import feature_extraction
from sklearn import preprocessing
from sklearn import svm
from sklearn import feature_selection
from sklearn.neighbors import KNeighborsClassifier
import os
import codecs
import dill as pickle
from sklearn import grid_search
from sklearn import cross_validation
from sklearn.cross_validation import train_test_split
from sklearn import metrics
import requests
import json

def getTextFromTopic(link):
    scraper = cfscrape.create_scraper()  # returns a CloudflareScraper instance
    soup = BeautifulSoup(scraper.get(link).content, 'lxml')
    blocks = soup.find('blockquote', {"class": "postcontent restore"})
    try:
        blocks = blocks.script.decompose()
    except:
        pass
    if blocks != None:
        return blocks.text.strip().encode("UTF-8")
    else:
        return ""


print("Importing data  from folder...")
# here we create a Bunch
# object ['target_names', 'data', 'target', 'DESCR', 'filenames']
raw_bunch = datasets.load_files('./ML/data', description=None, categories=None, load_content=True,
                                shuffle=True, encoding='utf-8', decode_error='replace')
print("Done!")
print("Processing text to data...")
print("     extracting features...")
vectorizer = feature_extraction.text.CountVectorizer(input=u'content', encoding=u'utf-8', decode_error=u'strict',
                                                     strip_accents=None, lowercase=True,
                                                     preprocessor=None, tokenizer=None,
                                                     # trying with enabled parameters
                                                     stop_words=None,  # we count stop words
                                                     token_pattern=r'(?u)\b\w\w+\b',
                                                     ngram_range=(1, 3),  # 1grams to 3grams
                                                     analyzer=u'word', max_df=1.0, min_df=1, max_features=None,
                                                     vocabulary=None, binary=False,
                                                     dtype='f')
raw_documents = raw_bunch['data']
document_feature_matrix = vectorizer.fit_transform(raw_documents)
feature_names = vectorizer.get_feature_names()
print("     Done!")
print("Done!")
print("Building a classifier...")
# print("     selecting features ...")
# var = feature_selection.VarianceThreshold(threshold=(0.2 * (1 - 0.2)))
# document_feature_matrix = var.fit_transform(document_feature_matrix)
# k_best = feature_selection.SelectKBest(k=10)
# document_feature_matrix = k_best.fit_transform(document_feature_matrix, raw_bunch['target'])
print("     training...")
clf = svm.SVC(C=0.01, kernel="linear")
# clf = KNeighborsClassifier(n_neighbors=2)
clf.fit(document_feature_matrix, raw_bunch['target'])

app = Flask(__name__)
api = Api(app)

# class checkWeb(Resource):
#     def get(self):
#          return '{"name":"GamesForChildren","url":"www.GamesForChildren.co.il","classifictclassification":"PEDOPHILE"}'

#     def post(self):
#         print "BUYA"
#         return "{}"
        # url = url['url']
        # content = getTextFromTopic(url)
        # pred = clf.predict(content)
        # #phe phe=0
        # if pred == 0:
        #     js_data = {}
        #     js_data["name"] = "TESTINGGG"
        #     js_data["url"] = url
        #     js_data["classifictclassification"] ="PEDOPHILE"

        #     r = requests.post('http://127.0.0.1:4567/create', data=json.dumps(js_data))
        #     pass
        #     #phe
        # else :
        #     r = requests.post('http://127.0.0.1:4567', data={'key': 'value'})
        #     pass
        # return r


app = Flask(__name__)
api = Api(app)

class sampleWeb(Resource):
    def post(self):
        url = request.data
        content = getTextFromTopic(url)
        pred = clf.predict(content)
        #phe phe=0
        if pred == 0:
            js_data = {}
            js_data["name"] = "TESTINGGG"
            js_data["url"] = url
            js_data["classifictclassification"] ="PEDOPHILE"

            r = requests.post('http://127.0.0.1:4567/create', data=json.dumps(js_data))
            return "PEDOPHILE"
        else :
            return "NONE"



api.add_resource(sampleWeb, '/sampleWeb') 

if __name__ == '__main__':
     app.run(port='5002')
     