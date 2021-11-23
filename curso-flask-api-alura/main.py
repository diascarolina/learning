from flask import Flask, request, jsonify
from flask_basicauth import BasicAuth
from textblob import TextBlob
from sklearn.linear_model import LinearRegression
import pickle

colunas = ['tamanho', 'ano', 'garagem']
model = pickle.load(open('model.sav', 'rb'))

app = Flask('__name__')
app.config['BASIC_AUTH_USERNAME'] = 'caro'
app.config['BASIC_AUTH_PASSWORD'] = 'alura'

basic_auth = BasicAuth(app)

@app.route('/')
def home():
    return "Minha primeira API."

@app.route('/sentimento/<frase>')
@basic_auth.required
def sentimento(frase):
    tb = TextBlob(frase)
    pol = tb.sentiment.polarity
    return f"Polaridade do Texto: {pol}"

@app.route('/casa/', methods = ['POST'])
@basic_auth.required
def valor():
    dados = request.get_json()
    dados_input = [dados[col] for col in colunas]
    preco = model.predict([dados_input])
    return jsonify(preco = preco[0])

app.run(debug = True)