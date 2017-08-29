import requests

r = requests.post("http://localhost:5002/sampleWeb", data="http://www.fxp.co.il/showthread.php?t=16437574")
print r.content
print r.status_code