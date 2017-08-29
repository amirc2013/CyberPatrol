from flask import Flask, request
from flask_restful import Resource, Api
from json import dumps
from flask.ext.jsonpify import jsonify

app = Flask(__name__)
api = Api(app)

class sampleWeb(Resource):
    def get(self):
       
        return '{"name":"GamesForChildren","url":"www.GamesForChildren.co.il","classifictclassification":"PEDOPHILE"}'

class checkWebsite(Resource):
    def get(self, url):
        #do your thing here...
        #return json of the website
        return '{"name":"GamesForChildren","url":"www.GamesForChildren.co.il","classifictclassification":"PEDOPHILE"}'
        

api.add_resource(checkWebsite, '/checkWebsite')
api.add_resource(sampleWeb, '/sampleWeb') 

if __name__ == '__main__':
     app.run(port='5003')
     